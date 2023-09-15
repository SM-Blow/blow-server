package com.blow.server.api.dto.auth.request;

public record LoginRequestDTO (
        String email,
        String password,
        String fcmDeviceToken
){
}
