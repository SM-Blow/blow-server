package com.blow.server.api.controller;

import com.blow.server.api.common.ApiResponse;
import com.blow.server.api.common.message.ResponseMessage;
import com.blow.server.api.service.s3.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/s3")
public class S3Controller {
    private final S3Service s3Service;

    @GetMapping("/image")
    public ResponseEntity<ApiResponse> getPresignedUrl(
            @RequestParam String filename)
    {
        val response = s3Service.getPresignedUrl(filename);
        return ResponseEntity.ok(ApiResponse.success(ResponseMessage.SUCCESS_GET_PRESIGNED_URLS.getMessage(), response));
    }
}
