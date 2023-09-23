package com.blow.server.api.dto.event.request;

import javax.validation.constraints.Min;
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
        @NotNull(message = "허용인원 수를 명시해 주세요.")
        @Min(value = 1, message = "허용 가능한 인원은 1 이상이어야 합니다.")
        Long acceptCount,
        @NotNull
        LocalDateTime dueDate
){
}
