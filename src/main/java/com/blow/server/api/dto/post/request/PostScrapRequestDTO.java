package com.blow.server.api.dto.post.request;

public record PostScrapRequestDTO(
        Long targetPostId,
        boolean currentScrapStatus) {
}
