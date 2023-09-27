package com.blow.server.api.dto.post.request;

import lombok.NonNull;

public record PostEditStatusRequestDTO(
        @NonNull
        Long postId,
        @NonNull
        Integer status
) {
}
