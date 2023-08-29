package com.blow.server.api.dto.Post.request;


import lombok.NonNull;

import java.time.LocalDateTime;

public record PostCreateRequestDTO(
        @NonNull
        String title,
        @NonNull
        Boolean borrow,
        @NonNull
        String content,
        @NonNull
        String category,
        @NonNull
        String photoUrl,
        @NonNull
        LocalDateTime duedate) {
}
