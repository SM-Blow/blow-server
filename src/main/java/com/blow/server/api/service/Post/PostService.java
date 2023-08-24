package com.blow.server.api.service.Post;

import com.blow.server.api.dto.Post.request.PostCreateRequestDTO;

public interface PostService {
    void createPost(Long userId, PostCreateRequestDTO request);
}
