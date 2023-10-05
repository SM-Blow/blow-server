package com.blow.server.api.dto.post.response;

import com.blow.server.api.entity.Post;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record PostSearchResponseDTO(
    List<PostSearchResponseVO> postList
) {
    public static PostSearchResponseDTO of(List<Post> postList){
        return PostSearchResponseDTO.builder()
                .postList(postList.stream().map(PostSearchResponseVO::of).toList())
                .build();
    }
}

@Builder
record PostSearchResponseVO(
        Long postId,
        String title,
        Boolean borrow,
        Integer status,
        String category,
        LocalDateTime duedate)
{
    public static PostSearchResponseVO of(Post post){
        return PostSearchResponseVO.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .borrow(post.isBorrow())
                .status(post.getStatus())
                .category(post.getCategory())
                .duedate(post.getDuedate())
                .build();
    }
}