package com.blow.server.api.controller;

import com.blow.server.api.common.ApiResponse;
import com.blow.server.api.common.message.ResponseMessage;
import com.blow.server.api.entity.BlowUserDetails;
import com.blow.server.api.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/my")
    private ResponseEntity<ApiResponse> getMyPage(
            @AuthenticationPrincipal BlowUserDetails userDetails)
    {
        val reponse = userService.getMyPage(userDetails.getId());

        return ResponseEntity.ok(ApiResponse.success(ResponseMessage.SUCCESS_GET_MYPAGE.getMessage(), reponse));
    }
}
