package com.blow.server.api.controller;

import com.blow.server.api.common.ApiResponse;
import com.blow.server.api.common.message.ResponseMessage;
import com.blow.server.api.dto.post.request.*;
import com.blow.server.api.entity.BlowUserDetails;
import com.blow.server.api.service.Post.PostService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
            @AuthenticationPrincipal BlowUserDetails blowUserDetails,
            @Valid @RequestBody PostCreateRequestDTO request)
    {
        postService.createPost(blowUserDetails.getId(), request);
        return ResponseEntity.ok(ApiResponse.success(ResponseMessage.SUCCESS_CREATE_WORK.getMessage()));
    }

    @DeleteMapping("")
    public ResponseEntity<ApiResponse> deletePost(
            @AuthenticationPrincipal BlowUserDetails blowUserDetails,
            @Valid @RequestBody PostDeleteRequestDTO request)
    {
        postService.deletePost(blowUserDetails.getId(), request);
        return ResponseEntity.ok(ApiResponse.success(ResponseMessage.SUCCESS_DELETE_WORK.getMessage()));
    }

    @PatchMapping("/edit")
    public ResponseEntity<ApiResponse> updatePost(
            @AuthenticationPrincipal BlowUserDetails blowUserDetails,
            @Valid @RequestBody PostEditRequestDTO request)
    {
        postService.updatePost(blowUserDetails.getId(), request);
        return ResponseEntity.ok(ApiResponse.success(ResponseMessage.SUCCESS_UPDATE_POST.getMessage()));
    }

    @PostMapping("/scrap")
    public ResponseEntity<ApiResponse> postScrap(
            @AuthenticationPrincipal BlowUserDetails blowUserDetails,
            @RequestBody PostScrapRequestDTO request)
    {
        val response = postService.scrapPost(blowUserDetails.getId(), request);
        return ResponseEntity.ok(ApiResponse.success(ResponseMessage.SUCCESS_SCRAP_POST.getMessage(), response));
    }

    @GetMapping("/scraps")
    public ResponseEntity<ApiResponse> getPostScraps(
            @AuthenticationPrincipal BlowUserDetails blowUserDetails
    )
    {
        val response = postService.getPostScraps(blowUserDetails.getId());
        return ResponseEntity.ok(ApiResponse.success(ResponseMessage.SUCCESS_GET_POST_SCRAP_LIST.getMessage(), response));
    }


    @PatchMapping("/status")
    public ResponseEntity<ApiResponse> updateStatus(
            @AuthenticationPrincipal BlowUserDetails blowUserDetails,
            @Valid @RequestBody PostEditStatusRequestDTO request)
    {
        postService.updateStatus(blowUserDetails.getId(), request);
        return ResponseEntity.ok(ApiResponse.success(ResponseMessage.SUCCESS_UPDATE_STATUS.getMessage()));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse> searchPost(
            @RequestParam String keyword)
    {
        val response = postService.searchPost(keyword);
        return ResponseEntity.ok(ApiResponse.success(ResponseMessage.SUCCESS_SEARCH_POST.getMessage(), response));
    }
}
