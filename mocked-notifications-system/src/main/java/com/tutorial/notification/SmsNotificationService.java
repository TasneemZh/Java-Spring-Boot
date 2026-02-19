package com.tutorial.notification;

import org.springframework.stereotype.Component;

@Component
public class SmsNotificationService implements NotificationService {
    public void sendNotification(String message) {
        System.out.println("SmsNotificationService: Sending SMS with message: ...");
    }
}

