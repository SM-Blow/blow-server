package com.blow.server.api.service.notification;

import com.blow.server.api.common.exception.FcmException;
import com.blow.server.api.common.message.ExceptionMessage;
import com.blow.server.api.dto.notification.request.FCMRequestDTO;
import com.blow.server.api.repository.UserRepository;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
@Service
public class FCMServiceImpl implements FCMService {

    private final FirebaseMessaging firebaseMessaging;
    private final UserRepository userRepository;

    public void createNotification(Long userId, FCMRequestDTO request) {
        try {
            val user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException());
            val fcmToken = user.getFCMToken();

            if (fcmToken == null) {
                throw new FcmException(ExceptionMessage.EMPTY_NOTIFICATION_TOKEN.getMessage(), HttpStatus.BAD_GATEWAY);
            }

            Message message = Message.builder()
                    .setToken(fcmToken)
                    .setNotification(request.toNotification())
                    .build();

            firebaseMessaging.send(message);
        } catch (FirebaseMessagingException e) {
            throw new FcmException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
