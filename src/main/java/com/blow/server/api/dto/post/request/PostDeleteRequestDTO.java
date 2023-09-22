package com.blow.server.api.dto.post.request;

import lombok.NonNull;

public record PostDeleteRequestDTO(
        @NonNull
        Long postId) {
}
