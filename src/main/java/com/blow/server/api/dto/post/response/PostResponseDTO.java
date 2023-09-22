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
        String title,
        Boolean borrow,
        Integer status,
        LocalDateTime duedate
){
    public static PostResponseVO of(Post post){
        return PostResponseVO.builder()
                .title(post.getTitle())
                .borrow(post.isBorrow())
                .status(post.getStatus())
                .duedate(post.getDuedate())
                .build();
    }
}
