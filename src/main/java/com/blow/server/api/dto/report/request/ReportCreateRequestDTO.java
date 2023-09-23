package com.blow.server.api.dto.report.request;

import javax.validation.constraints.NotBlank;

public record ReportCreateRequestDTO(
        Long targetUserId,
        @NotBlank(message = "신고 내용을 입력해 주세요.")
        String content
) {
}
