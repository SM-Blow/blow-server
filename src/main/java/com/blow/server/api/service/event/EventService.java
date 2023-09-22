package com.blow.server.api.service.event;

import com.blow.server.api.dto.event.request.CreateEventRequestDTO;
import com.blow.server.api.dto.event.response.EventDetailResponseDTO;
import com.blow.server.api.dto.event.response.EventListResponseDTO;

import java.util.List;

public interface EventService {
    void createEvent(CreateEventRequestDTO request);
    List<EventListResponseDTO> getEvents();

    EventDetailResponseDTO getEventDetail(Long eventId);
}
