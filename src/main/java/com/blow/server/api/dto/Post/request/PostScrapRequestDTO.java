package com.blow.server.api.dto.Post.request;

public record PostScrapRequestDTO(
        Long targetPostId,
        boolean currentScrapStatus) {
}
