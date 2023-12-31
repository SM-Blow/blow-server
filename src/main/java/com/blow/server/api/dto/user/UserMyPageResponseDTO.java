package com.blow.server.api.dto.user;


import com.blow.server.api.entity.Post;
import com.blow.server.api.entity.PostScrap;
import com.blow.server.api.entity.User;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record UserMyPageResponseDTO(
        Long userId,
        String nickname,
        int seed,
        List<UserMyPostResponseVO> posts,
        List<UserMyScrapResponseVO> scraps)
{
    public static UserMyPageResponseDTO of (User user, List<Post> posts, List<Post> scraps) {
        return UserMyPageResponseDTO.builder()
                .userId(user.getId())
                .nickname(user.getNickname())
                .seed(user.getSeed())
                .posts(posts.stream().map(UserMyPostResponseVO::of).toList())
                .scraps(scraps.stream().map(UserMyScrapResponseVO::of).toList())
                .build();
    }
}


@Builder
record UserMyPostResponseVO(
        Long postId,
        String title,
        boolean borrow,
        int status,
        String category,
        LocalDateTime duedate)
{
    public static UserMyPostResponseVO of (Post post){
        return UserMyPostResponseVO.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .borrow(post.isBorrow())
                .status(post.getStatus())
                .category(post.getCategory())
                .duedate(post.getDuedate())
                .build();
    }
}

@Builder
record UserMyScrapResponseVO(
        Long postId,
        String title,
        Boolean borrow, // todo:boolean으로 바꿀 것
        Integer status, //todo:int로 바꿀 것
        String category,
        LocalDateTime duedate,
        boolean isScraped
){
    public static UserMyScrapResponseVO of(Post post){
        return UserMyScrapResponseVO.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .borrow(post.isBorrow())
                .status(post.getStatus())
                .category(post.getCategory())
                .duedate(post.getDuedate())
                .isScraped(true)
                .build();
    }
}