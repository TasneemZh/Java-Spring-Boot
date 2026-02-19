package com.tutorial.notification;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class EmailNotificationService implements NotificationService {
    public void sendNotification(String message) {
        System.out.println("EmailNotificationService: Sending email with message: ...");
    }
}
