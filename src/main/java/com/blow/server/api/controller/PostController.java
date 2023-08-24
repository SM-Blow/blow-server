package com.blow.server.api.controller;

import com.blow.server.api.common.ApiResponse;
import com.blow.server.api.common.message.ResponseMessage;
import com.blow.server.api.dto.Post.request.PostCreateRequestDTO;
import com.blow.server.api.dto.Post.request.PostDeleteRequestDTO;
import com.blow.server.api.service.Post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    @PostMapping("")
    public ResponseEntity<ApiResponse> createPost(
            @Valid @RequestBody PostCreateRequestDTO request)
    {
        postService.createPost(request.userId(), request);
        return ResponseEntity.ok(ApiResponse.success(ResponseMessage.SUCCESS_CREATE_WORK.getMessage()));
    }

    @DeleteMapping("")
    public ResponseEntity<ApiResponse> deleteWork(
            @Valid @RequestBody PostDeleteRequestDTO request)
    {
        postService.deletePost(request.userId(), request);
        return ResponseEntity.ok(ApiResponse.success(ResponseMessage.SUCCESS_DELETE_WORK.getMessage()));
    }
}
