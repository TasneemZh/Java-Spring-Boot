package com.tutorial.jdbc.util;

import java.util.UUID;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class DbOperationLogger {
    private UUID instanceId;

    public DbOperationLogger() {
        this.instanceId = UUID.randomUUID();
        System.out.println("\nNew DbOperationLogger created with ID: " + instanceId);
    }

    public void log(String operation) {
        System.out.println("\nLogger " + instanceId + ": Executing operation -> " + operation);
    }
}
