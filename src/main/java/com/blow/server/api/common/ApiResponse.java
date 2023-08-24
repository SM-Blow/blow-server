package com.blow.server.api.common;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access= AccessLevel.PRIVATE)
public record ApiResponse(Boolean success, String message, Object data) {
    public static ApiResponse success(String message){
        return ApiResponse.builder()
                .success(true)
                .message(message)
                .data(null)
                .build();
    }
}
