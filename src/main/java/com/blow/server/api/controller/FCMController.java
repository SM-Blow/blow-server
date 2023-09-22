package com.blow.server.api.controller;

import com.blow.server.api.common.ApiResponse;
import com.blow.server.api.common.message.ResponseMessage;
import com.blow.server.api.dto.notification.request.FCMRequestDTO;
import com.blow.server.api.entity.BlowUserDetails;
import com.blow.server.api.service.notification.FCMService;
import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notification")
public class FCMController {

    private final FCMService fcmService;

    @PostMapping("")
    public ResponseEntity<ApiResponse> createNotification(@AuthenticationPrincipal BlowUserDetails blowUserDetails, @RequestBody FCMRequestDTO request) throws FirebaseMessagingException {
        val userId = blowUserDetails.getId();
        fcmService.createNotification(userId, request);
        return ResponseEntity.ok(ApiResponse.success(ResponseMessage.SUCCESS_SEND_NOTIFICATION.getMessage()));
    }

}
