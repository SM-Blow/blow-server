package com.blow.server.api.service.Post;


import com.blow.server.api.common.message.ExceptionMessage;
import com.blow.server.api.dto.Post.request.PostCreateRequestDTO;
import com.blow.server.api.entity.Post;
import com.blow.server.api.repository.PostRepository;
import com.blow.server.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService{
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void createPost(Long userId, PostCreateRequestDTO request){
        val user = userRepository.getUserById(userId)
                .orElseThrow(() -> new EntityNotFoundException(
                        ExceptionMessage.NOT_FOUND_USER.getMessage()));

        postRepository.save(Post.builder()
                .title(request.title())
                .content(request.content())
                .category(request.Category())
                .users(user)
                .duedate(request.duedate())
                .build());
    }
}
