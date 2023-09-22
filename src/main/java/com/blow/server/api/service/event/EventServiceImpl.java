package com.blow.server.api.service.event;


import com.blow.server.api.common.exception.EventException;
import com.blow.server.api.common.message.ExceptionMessage;
import com.blow.server.api.dto.event.request.CreateEventRequestDTO;
import com.blow.server.api.dto.event.response.EventDetailResponseDTO;
import com.blow.server.api.dto.event.response.EventListResponseDTO;
import com.blow.server.api.entity.Event;
import com.blow.server.api.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityNotFoundException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class EventServiceImpl implements EventService{
    private final EventRepository eventRepository;

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
        System.out.println(event.getId());

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

        if (!event.isStatus()){
            throw new EventException(ExceptionMessage.ALREADY_END_EVENT.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return EventDetailResponseDTO.of(event);
    }


}
