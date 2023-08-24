package com.blow.server.api.dto.Post.request;

import lombok.NonNull;

public record PostDeleteRequestDTO(
        @NonNull
        Long userId,
        @NonNull
        Long postId) {
}
