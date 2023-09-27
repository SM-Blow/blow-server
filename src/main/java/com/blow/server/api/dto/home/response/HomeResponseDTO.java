package com.blow.server.api.dto.home.response;

import com.blow.server.api.entity.User;
import lombok.Builder;

@Builder
public record HomeResponseDTO(
        String nickName,
        int seed
) {
    public static HomeResponseDTO of(User user){
        return HomeResponseDTO.builder()
                .nickName(user.getNickname())
                .seed(user.getSeed())
                .build();
    }
}
