package com.blow.server.api.controller;

import com.blow.server.api.common.ApiResponse;
import com.blow.server.api.common.message.ResponseMessage;
import com.blow.server.api.dto.Post.request.PostCreateRequestDTO;
import com.blow.server.api.dto.Post.request.PostDeleteRequestDTO;
import com.blow.server.api.dto.Post.request.PostEditRequestDTO;
import com.blow.server.api.dto.Post.request.PostEditStatusRequestDTO;
import com.blow.server.api.service.Post.PostService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
public class PostController {
    private final PostService postService;

    @GetMapping("")
    public ResponseEntity<ApiResponse> getPosts()
    {
        val response = postService.getPosts();
        return ResponseEntity.ok(ApiResponse.success(ResponseMessage.SUCCESS_GET_POSTS.getMessage(), response));
    }

    @GetMapping("/filter")
    public ResponseEntity<ApiResponse> getPostsByCategory(
            @RequestParam String category)
    {
        val response = postService.getPostsByCategory(category);
        return ResponseEntity.ok(ApiResponse.success(ResponseMessage.SUCCESS_GET_POSTS_BY_CATEGORY.getMessage(), response));
    }

    @GetMapping("/detail")
    public ResponseEntity<ApiResponse> getPostDetail(
            @RequestParam Long postId)
    {
        val response = postService.getPostDetail(postId);
        return ResponseEntity.ok(ApiResponse.success(ResponseMessage.SUCCESS_GET_POST_DETAIL.getMessage(), response));
    }

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

    @PatchMapping("/edit")
    public ResponseEntity<ApiResponse> updatePost(
            @Valid @RequestBody PostEditRequestDTO request)
    {
        postService.updatePost(request.userId(), request);
        return ResponseEntity.ok(ApiResponse.success(ResponseMessage.SUCCESS_UPDATE_POST.getMessage()));
    }

    @PatchMapping("/status")
    public ResponseEntity<ApiResponse> updateStatus(
            @Valid @RequestBody PostEditStatusRequestDTO request)
    {
        postService.updateStatus(request);
        return ResponseEntity.ok(ApiResponse.success(ResponseMessage.SUCCESS_UPDATE_STATUS.getMessage()));
    }
}
