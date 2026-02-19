package com.tutorial.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MockedNotificationsSystem {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(MockedNotificationsSystem.class, args);
        UserService userService = context.getBean(UserService.class);

        userService.serviceName = "Main User Service";
        userService.setDefaultMessage("Welcome to Spring Beans!");

        userService.processUser();
    }

}
