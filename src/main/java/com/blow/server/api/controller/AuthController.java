package com.blow.server.api.controller;

import com.blow.server.api.common.ApiResponse;
import com.blow.server.api.common.message.ResponseMessage;
import com.blow.server.api.dto.auth.request.LoginRequestDTO;
import com.blow.server.api.dto.auth.request.SignInRequestDTO;
import com.blow.server.api.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<ApiResponse> singIn(
            @Valid @RequestBody SignInRequestDTO request)
    {
        val response = authService.signIn(request);
        return ResponseEntity.ok(ApiResponse.success(ResponseMessage.SUCCESS_SIGNIN_USER.getMessage(), response));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(
            @Valid @RequestBody LoginRequestDTO request)
    {
        val response = authService.login(request);
        return ResponseEntity.ok(ApiResponse.success(ResponseMessage.SUCCESS_SIGNIN_USER.getMessage(), response));
    }
}
