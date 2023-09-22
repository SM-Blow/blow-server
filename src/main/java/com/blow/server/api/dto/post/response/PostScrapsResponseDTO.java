package com.blow.server.api.dto.post.response;

import com.blow.server.api.entity.Post;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record PostScrapsResponseDTO(
        List<PostScrapsResponseVO> postscrapsList)
{
    public static PostScrapsResponseDTO of(List<Post> postList){
        return PostScrapsResponseDTO.builder()
                .postscrapsList(postList.stream().map(PostScrapsResponseVO::of).toList())
                .build();
    }
}

@Builder
record PostScrapsResponseVO(
        String title,
        Boolean borrow,
        Integer status,
        LocalDateTime duedate,
        boolean isScraped
){
    public static PostScrapsResponseVO of(Post post){
        return PostScrapsResponseVO.builder()
                .title(post.getTitle())
                .borrow(post.isBorrow())
                .status(post.getStatus())
                .duedate(post.getDuedate())
                .isScraped(true)
                .build();
    }
}

