package com.blow.server.api.dto.event.response;

import com.blow.server.api.entity.Event;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record EventListResponseDTO (Long eventId,
                                   String title,
                                   LocalDateTime dueDate,
                                   String host) {

    public static EventListResponseDTO of(Event event) {
        return EventListResponseDTO.builder()
                .eventId(event.getId())
                .title(event.getTitle())
                .dueDate(event.getDueDate())
                .host(event.getHost())
                .build();
    }
}
