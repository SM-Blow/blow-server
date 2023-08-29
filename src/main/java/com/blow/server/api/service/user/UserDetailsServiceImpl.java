package com.blow.server.api.service.user;


import com.blow.server.api.common.message.ExceptionMessage;
import com.blow.server.api.entity.BlowUserDetails;
import com.blow.server.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) {
        val user = userRepository.findById(Long.parseLong(userId))
                .orElseThrow(() -> new EntityNotFoundException(ExceptionMessage.NOT_FOUND_USER.getMessage()));

        return BlowUserDetails.builder()
                .id(user.getId())
                .username(user.getNickname())
                .build();
    }
}