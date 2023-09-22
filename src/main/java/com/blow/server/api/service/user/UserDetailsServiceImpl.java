package com.blow.server.api.service.user;


import com.blow.server.api.common.exception.TokenException;
import com.blow.server.api.common.message.ExceptionMessage;
import com.blow.server.api.entity.BlowUserDetails;
import com.blow.server.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
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
                .orElseThrow(() -> new TokenException(ExceptionMessage.NOT_FOUND_USER_WITH_TOKEN.getMessage(), HttpStatus.NOT_FOUND));

        return BlowUserDetails.builder()
                .id(user.getId())
                .username(user.getNickname())
                .build();
    }
}