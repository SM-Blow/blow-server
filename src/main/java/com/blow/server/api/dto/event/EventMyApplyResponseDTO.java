package com.blow.server.api.dto.event;

import com.blow.server.api.entity.Event;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record EventMyApplyResponseDTO(
        Long eventId,
        String title,
        LocalDateTime dueDate,
        String host
) {
    public static EventMyApplyResponseDTO of(Event event) {
        return EventMyApplyResponseDTO.builder()
                .eventId(event.getId())
                .title(event.getTitle())
                .dueDate(event.getDueDate())
                .host(event.getHost())
                .build();
    }
}
