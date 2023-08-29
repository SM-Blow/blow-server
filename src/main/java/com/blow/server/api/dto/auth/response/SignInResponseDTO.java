package com.blow.server.api.dto.auth.response;

import com.blow.server.api.entity.User;
import lombok.Builder;

@Builder
public record SignInResponseDTO (
        Long id,
        String userName,
        String accessToken
){
    public static SignInResponseDTO of(User user, String accessToken) {
        return SignInResponseDTO.builder()
                .id(user.getId())
                .userName(user.getNickname())
                .accessToken(accessToken)
                .build();
    }
}
