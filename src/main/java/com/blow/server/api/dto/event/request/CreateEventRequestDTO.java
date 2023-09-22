package com.blow.server.api.dto.event.request;

import java.time.LocalDateTime;


public record CreateEventRequestDTO (
        String title,
        String host,
        String content,
        Long acceptCount,
        LocalDateTime dueDate
){
}
