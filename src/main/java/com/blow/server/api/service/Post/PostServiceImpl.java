package com.blow.server.api.service.Post;


import com.blow.server.api.common.message.ExceptionMessage;
import com.blow.server.api.dto.Post.request.PostCreateRequestDTO;
import com.blow.server.api.dto.Post.request.PostDeleteRequestDTO;
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

    @Override
    @Transactional
    public void deletePost(Long userId, PostDeleteRequestDTO request){
        val postId = request.postId();
        val user = userRepository.getUserById(userId)
                .orElseThrow(()-> new EntityNotFoundException(ExceptionMessage.NOT_FOUND_USER.getMessage()));

        val post = postRepository.getPostById(postId)
                .orElseThrow(()-> new EntityNotFoundException(ExceptionMessage.NOT_FOUND_POST.getMessage()));

        if(!isOwner(post,user.getId())){
            throw new EntityNotFoundException(ExceptionMessage.NOT_POST_OWNER.getMessage());
        }

        postRepository.deleteById(postId);
    }

    private boolean isOwner(Post post, Long userId){
        if (!post.isOwner(userId)){
            return false;
        }
        return true;
    }
}
