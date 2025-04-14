package org.example.medical_record.notification.service;

public interface NotificationService {


    void sendNotification(String email, String subject, String body);
}
