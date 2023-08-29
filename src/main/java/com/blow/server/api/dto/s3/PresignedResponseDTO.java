package com.blow.server.api.dto.s3;

import lombok.Builder;

@Builder
public record PresignedResponseDTO(String presignedUrl) {

    public static PresignedResponseDTO of(String PresignedUrl){
        return PresignedResponseDTO.builder()
                .presignedUrl(PresignedUrl)
                .build();
    }
}
