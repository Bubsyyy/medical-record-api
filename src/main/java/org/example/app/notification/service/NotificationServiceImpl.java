package org.example.app.notification.service;

import lombok.extern.slf4j.Slf4j;
import org.example.app.notification.client.NotificationClient;
import org.example.app.web.dto.NotificationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationClient notificationClient;

    @Autowired
    public NotificationServiceImpl(NotificationClient notificationClient) {
        this.notificationClient = notificationClient;
    }


    @Override
    public void sendNotification(String email, String subject, String body) {

        NotificationRequest notificationRequest = NotificationRequest.builder()
                .userEmail(email)
                .body(body)
                .subject(subject)
                .build();


        // Service to Service
        ResponseEntity<Void> httpResponse;
        try {
            httpResponse = notificationClient.sendNotification(notificationRequest);
            if (!httpResponse.getStatusCode().is2xxSuccessful()) {
                log.error("[Feign call to notification-svc failed] Can't send email to user with email = [%s]]".formatted(email));
            }
        } catch (Exception e) {
            log.warn("Can't send email to user with email = [%s] due to 500 Internal Server Error.".formatted(email));
        }

    }
}
