package com.blow.server.api.service.s3;

import com.blow.server.api.common.message.ExceptionMessage;
import com.blow.server.api.config.S3Config;
import com.blow.server.api.dto.s3.PresignedResponseDTO;
import lombok.RequiredArgsConstructor;

import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3ServiceImpl implements S3Service{
    private final S3Config s3Config;
    private final String BucketPath = "post/";
    private final S3Presigner s3Presigner;
    private final ArrayList<String> imageFileExtension =
            new ArrayList<>(List.of("jpg","jpeg","png","JPG","JPEG","PNG"));


    LocalDateTime currentTime = LocalDateTime.now();

    @Override
    public PresignedResponseDTO getPresignedUrl(String fileName){
        return createPresignedUrl(fileName);
    }

    private PresignedResponseDTO createPresignedUrl(String fileName) {
        val keyName = BucketPath + UUID.randomUUID() + fileName;
        val splitFileName = fileName.split("\\.");
        val extension = splitFileName[splitFileName.length-1];
        val contentType = "image/" + extension;

        if (!imageFileExtension.contains(extension)) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_EXTENSION.getMessage());
        }

        val objectRequest = PutObjectRequest.builder()
                .bucket(s3Config.getBucketName())
                .key(keyName)
                .contentType(contentType)
                .build();

        val presignRequest = PutObjectPresignRequest.builder()
                .signatureDuration(Duration.ofMinutes(10))
                .putObjectRequest(objectRequest)
                .build();

        val presignedRequest = s3Presigner.presignPutObject(presignRequest);
        val signedUrl = presignedRequest.url().toString();

        return PresignedResponseDTO.of(signedUrl);
    }


//    public PresignedResponseDTO createPresignedUrl(String fileName){
//        val keyName = BucketPath + currentTime + fileName;
//        val splitFileName = fileName.split("\\.");
//        val extension = splitFileName[splitFileName.length-1];
//        val contentType = "image/" + extension;
//
//
//        if (!imageFileExtension.contains(extension)){
//            throw new IllegalArgumentException(ExceptionMessage.INVALID_EXTENSION.getMessage());
//        }
//
//
//        val objectRequest = PutObjectRequest.builder()
//                .bucket(s3Config.getBucketName())
//                .key(keyName)
//                .contentType(contentType)
//                .build();
//
//        val presignRequest = PutObjectPresignRequest.builder()
//                .signatureDuration(Duration.ofMinutes(10))
//                .putObjectRequest(objectRequest)
//                .build();
//
//        val presignedRequest = s3Presigner.presignPutObject(presignRequest);
//        val signedUrl = presignedRequest.url().toString();
//
//        return PresignedResponseDTO.of(signedUrl);
//    }
}
