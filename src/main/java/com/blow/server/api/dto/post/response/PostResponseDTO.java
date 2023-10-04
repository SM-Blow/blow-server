package com.blow.server.api.dto.post.response;

import com.blow.server.api.entity.Post;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record PostResponseDTO(
        List<PostResponseVO> postList)
{
    public static PostResponseDTO of(List<Post> postList){
        return PostResponseDTO.builder()
                .postList(postList.stream().map(PostResponseVO::of).toList())
                .build();
    }
}

@Builder
record PostResponseVO(
        Long postId,
        String title,
        Boolean borrow,
        Integer status,
        String category,
        LocalDateTime duedate
){
    public static PostResponseVO of(Post post){
        return PostResponseVO.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .borrow(post.isBorrow())
                .status(post.getStatus())
                .category(post.getCategory())
                .duedate(post.getDuedate())
                .build();
    }
}
