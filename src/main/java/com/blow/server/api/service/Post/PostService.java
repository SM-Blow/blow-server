package com.blow.server.api.service.Post;

import com.blow.server.api.dto.Post.request.*;
import com.blow.server.api.dto.Post.response.*;

public interface PostService {
    PostResponseDTO getPosts();
    PostResponseDTO getPostsByCategory(String category);
    PostDetailResponseDTO getPostDetail(Long postId);
    PostSearchResponseDTO searchPost(String keyword);
    PostScrapResponseDTO scrapPost(Long userId, PostScrapRequestDTO request);
    PostScrapsResponseDTO getPostScraps(Long userId);
    void createPost(Long userId, PostCreateRequestDTO request);
    void deletePost(Long userId, PostDeleteRequestDTO request);
    void updatePost(Long userId, PostEditRequestDTO request);
    void updateStatus(Long userId, PostEditStatusRequestDTO request);
}
