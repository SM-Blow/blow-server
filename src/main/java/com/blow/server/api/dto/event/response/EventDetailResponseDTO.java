package com.blow.server.api.dto.event.response;

import com.blow.server.api.entity.Event;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record EventDetailResponseDTO (
        String host,
        LocalDateTime dueDate,
        String title,
        Long acceptCount,
        Long currentApplyCount,
        String content
){

    public static EventDetailResponseDTO of (Event event) {
        return EventDetailResponseDTO.builder()
                .host(event.getHost())
                .dueDate(event.getDueDate())
                .title(event.getTitle())
                .acceptCount(event.getAcceptCount())
                .currentApplyCount(event.getCurrentApplyCount())
                .content(event.getContent())
                .build();
    }
}
