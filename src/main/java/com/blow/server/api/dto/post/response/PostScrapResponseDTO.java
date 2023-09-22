package com.blow.server.api.dto.post.response;

import lombok.Builder;

@Builder
public record PostScrapResponseDTO(
        Long targetPostId,
        boolean currentScrapStatus
) {
    public static PostScrapResponseDTO of(
            Long targetPostId,
            boolean currentScrapStatus
    ){
        return PostScrapResponseDTO.builder()
                .targetPostId(targetPostId)
                .currentScrapStatus(currentScrapStatus)
                .build();
    }
}
