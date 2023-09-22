package com.blow.server.api.dto.post.response;

import com.blow.server.api.entity.Post;
import com.blow.server.api.entity.User;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PostDetailResponseDTO(
        String nickname,
        Long postId,
        LocalDateTime duedate,
        String title,
        Boolean borrow,
        int status,
        String content)
{
    public static PostDetailResponseDTO of(
            Post post, User user)
    {
        return PostDetailResponseDTO.builder()
                .nickname(user.getNickname())
                .postId(post.getId())
                .duedate(post.getDuedate())
                .title(post.getTitle())
                .borrow(post.isBorrow())
                .status(post.getStatus())
                .content(post.getContent())
                .build();
    }
}

