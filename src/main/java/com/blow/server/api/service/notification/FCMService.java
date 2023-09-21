package com.blow.server.api.service.notification;

import com.blow.server.api.dto.notification.request.FCMRequestDTO;
import com.google.firebase.messaging.FirebaseMessagingException;

public interface FCMService {
    void createNotification(Long userId, FCMRequestDTO request) throws FirebaseMessagingException;
}
