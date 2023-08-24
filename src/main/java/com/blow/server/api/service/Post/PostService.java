package com.blow.server.api.service.Post;

import com.blow.server.api.dto.Post.request.PostCreateRequestDTO;
import com.blow.server.api.dto.Post.request.PostDeleteRequestDTO;
public interface PostService {
    void createPost(Long userId, PostCreateRequestDTO request);
    void deletePost(Long userId, PostDeleteRequestDTO request);
}
