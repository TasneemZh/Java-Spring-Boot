package com.tutorial.notification;

import org.springframework.stereotype.Component;

@Component
public class UserService {

    private final NotificationService notificationService;

    public UserService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public String serviceName;
    private String defaultMessage;

    public String getDefaultMessage() {
        return defaultMessage;
    }

    public void setDefaultMessage(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    public void processUser() {
        System.out.println("UserService: Processing user...");
        notificationService.sendNotification(defaultMessage);
    }


}
