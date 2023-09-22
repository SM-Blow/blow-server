package com.blow.server.api.service.user;

import com.blow.server.api.common.message.ExceptionMessage;
import com.blow.server.api.dto.user.UserMyPageResponseDTO;
import com.blow.server.api.entity.PostScrap;
import com.blow.server.api.entity.User;
import com.blow.server.api.entity.superclass.TimeStamped;
import com.blow.server.api.repository.PostRepository;
import com.blow.server.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Comparator;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Override
    public UserMyPageResponseDTO getMyPage(Long userId){
        val user = findUser(userId);
        val postList = postRepository.findAllByUserIdOrderByCreatedAtDesc(userId);
        val postScraps = user.getPostScraps();
        postScraps.sort(Comparator.comparing(TimeStamped::getCreatedAt).reversed());
        val ScrapList = postScraps.stream()
                .map(PostScrap::getPost)
                .toList();

        return UserMyPageResponseDTO.of(user, postList, ScrapList);
    }


    private User findUser(Long userId){
        return userRepository.findById(userId)
                .orElseThrow(()->new EntityNotFoundException(ExceptionMessage.NOT_FOUND_USER.getMessage()));
    }
}
