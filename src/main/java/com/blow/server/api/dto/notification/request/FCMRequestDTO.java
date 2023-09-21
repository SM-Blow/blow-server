package com.blow.server.api.dto.notification.request;

import com.google.firebase.messaging.Notification;

public record FCMRequestDTO (String title, String body){
    public Notification toNotification() {
        return Notification.builder()
                .setTitle(title)
                .setBody(body)
                .build();
    }
}
