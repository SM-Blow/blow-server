package com.blow.server.api.service.event;


import com.blow.server.api.common.exception.EventException;
import com.blow.server.api.common.message.ExceptionMessage;
import com.blow.server.api.dto.event.EventMyApplyResponseDTO;
import com.blow.server.api.dto.event.request.CreateEventRequestDTO;
import com.blow.server.api.dto.event.response.EventDetailResponseDTO;
import com.blow.server.api.dto.event.response.EventListResponseDTO;
import com.blow.server.api.entity.Event;
import com.blow.server.api.entity.EventApply;
import com.blow.server.api.repository.EventApplyRepository;
import com.blow.server.api.repository.EventRepository;
import com.blow.server.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final EventApplyRepository eventApplyRepository;

    @Override
    @Transactional
    public void createEvent(CreateEventRequestDTO request) {
        val event = Event.builder()
                .title(request.title())
                .dueDate(request.dueDate())
                .host(request.host())
                .content(request.content())
                .acceptCount(request.acceptCount())
                .build();
        eventRepository.save(event);
    }

    @Override
    public List<EventListResponseDTO> getEvents() {
        val eventList = eventRepository.findAllByStatus(true);

        if (eventList.size() == 0) {
            return null;
        }

        return eventList.stream()
                .map((event) -> EventListResponseDTO.of(event)).toList();
    }


    @Override
    public EventDetailResponseDTO getEventDetail(Long eventId) {
        val event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionMessage.NOT_FOUND_EVENT.getMessage()));

        if (!event.isStatus()) {
            throw new EventException(ExceptionMessage.ALREADY_END_EVENT.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return EventDetailResponseDTO.of(event);
    }

    @Transactional
    @Override
    public void applyEvent(Long userId, Long eventId) {

        val user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionMessage.NOT_FOUND_USER.getMessage()));

        val event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionMessage.NOT_FOUND_EVENT.getMessage()));

        Optional<EventApply> existApply = validateAlreadyApply(userId, eventId); // 이미 신청했던 유저인지 확인

        isClosedEvent(event); // 이벤트 종료 됐는지 확인

        isFullApply(event); // 정원 초과 됐는지 확인

        if (existApply.isEmpty()) {
            EventApply createApply = EventApply.builder()
                                .user(user)
                                .event(event)
                                .build();
            eventApplyRepository.save(createApply);

        } else{
            existApply.get().setStatus(true);
        }
        event.currentApplyCountUp();
    }

    @Override
    public List<EventMyApplyResponseDTO> getMyApplyEventList(Long userId) {

        val userHasApply = userRepository.findWithEventApplyListById(userId);

        if (userHasApply.isPresent()){
            return userHasApply.get().getEventApplyList()
                    .stream()
                    .map(apply -> EventMyApplyResponseDTO.of(apply.getEvent()))
                    .collect(Collectors.toList());
        }

        return null;
    }


    private void isClosedEvent(Event event) {
        if (!event.isStatus()){
            throw new EventException(ExceptionMessage.ALREADY_END_EVENT.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // 이미 신청했는지 확인
    private Optional<EventApply> validateAlreadyApply(Long userId, Long eventId) {
        val existApply = eventApplyRepository.findByUser_idAndEvent_id(userId, eventId);

        if (existApply.isEmpty()) {
            return Optional.empty();
        }

        if (existApply.get().isStatus()) {
            throw new EventException(ExceptionMessage.ALREADY_APPLY_USER.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }

        return existApply;
    }

    // 행사 정원 초과됐는지 확인
    private void isFullApply(Event event) {
        val currentApplyCount = event.getCurrentApplyCount();
        val maxApplyCount = event.getAcceptCount();

        if (currentApplyCount+1 > maxApplyCount) {
            event.setEventStatus(false);
            throw new EventException(ExceptionMessage.FULL_EVENT_USER.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

}
