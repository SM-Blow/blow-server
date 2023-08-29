package com.blow.server.api.dto.auth.response;

import com.blow.server.api.entity.User;
import lombok.Builder;

@Builder
public record LoginResponseDTO (
        Long id,
        String userName,
        String accessToken
){
    public static LoginResponseDTO of(User user, String accessToken) {
        return LoginResponseDTO.builder()
                .id(user.getId())
                .userName(user.getNickname())
                .accessToken(accessToken)
                .build();
    }
}

