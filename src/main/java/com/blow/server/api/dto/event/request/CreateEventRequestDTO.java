package com.blow.server.api.dto.event.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


public record CreateEventRequestDTO (
        @NotBlank
        @NotNull
        String title,
        @NotBlank
        @NotNull
        String host,
        @NotBlank
        @NotNull
        String content,
        @NotNull
        Long acceptCount,
        @NotNull
        LocalDateTime dueDate
){
}
