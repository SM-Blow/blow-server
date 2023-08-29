package com.blow.server.api.service.s3;

import com.blow.server.api.dto.s3.PresignedResponseDTO;

public interface S3Service {
    PresignedResponseDTO getPresignedUrl(String fileName);
}
