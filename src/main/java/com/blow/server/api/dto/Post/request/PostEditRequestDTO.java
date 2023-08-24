package com.blow.server.api.dto.Post.request;

import lombok.NonNull;

import java.time.LocalDateTime;

public record PostEditRequestDTO(
        @NonNull
        Long userId,
        @NonNull
        Long postId,
        @NonNull
        String title,
        @NonNull
        String content,
        @NonNull
        String Category,
        @NonNull
        LocalDateTime duedate) {

}