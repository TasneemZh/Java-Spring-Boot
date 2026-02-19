# 🔔 Mocked Notifications System

> **A focused Spring Boot learning project designed to master Dependency Injection, Service Wiring, and Bean Priorities.**

---

## 📖 Overview

This project simulates a backend user-processing flow that triggers notifications. Its primary goal is to demonstrate **Dependency Injection (DI)** in action.

You will see how to define a generic interface (`NotificationService`), create multiple implementations (Email & SMS), and inject the correct one into a `UserService` using Spring's smart wiring.

**Key Feature:**
By using the `@Primary` annotation, we control which notification channel (Email vs. SMS) is active by default, without changing the code in `UserService`.

---

## 🛠️ Tech Stack

Built with the latest standards:

*   ☕ **Java 25**
*   🍃 **Spring Boot 4.0.2**
*   📦 **Maven Wrapper** (No manual Maven installation needed)

---

## 🧠 Spring Concepts Covered

This demo allows you to practice the core building blocks of Spring:

*   **`@SpringBootApplication`** — The engine that bootstraps the app and scans for components.
*   **`@Component`** — Marks classes as managed "Beans" so Spring handles their lifecycle.
*   **Constructor Injection** — The modern, preferred way to inject dependencies (cleaner and safer than field injection).
*   **`@Primary`** — The tie-breaker! Tells Spring: *"If you find multiple versions of this interface, use this one."*
*   **`ApplicationContext`** — The container that holds all your beans.

---

## 🔄 Application Flow

Here is the lifecycle of the application when you run it:

1.  🚀 **Startup:** The application launches via `MockedNotificationsSystem`.
2.  🏭 **Bean Creation:** Spring scans and creates beans for:
    *   `UserService`
    *   `EmailNotificationService` (Marked as `@Primary`)
    *   `SmsNotificationService`
3.  🔌 **Wiring:** Spring injects the **Email** service into `UserService` (because it is Primary).
4.  ⚙️ **Execution:** The main method calls `processUser()`.
5.  📧 **Delivery:** `UserService` delegates the message delivery to the injected implementation (Email).

---

## ✅ Best Practices

This project isn't just a demo; it follows professional architectural patterns:

*   **Interface-Based Design:** We rely on `NotificationService` (abstraction), not concrete classes, ensuring **loose coupling**.
*   **Inversion of Control (IoC):** We let Spring manage object creation rather than using `new EmailNotificationService()` manually.
*   **Immutability:** Dependencies in `UserService` are `final` and set via constructor, making the service robust and easier to test.
*   **Separation of Concerns:**
    *   `UserService`: Focuses on business rules (orchestration).
    *   `NotificationService`: Focuses on delivery mechanisms (infrastructure).

---

## 📂 Project Structure

```text
src/main/java/com/tutorial/notification
 ├── 🚀 MockedNotificationsSystem.java   // Main Entry Point
 ├── ⚙️ UserService.java                 // Business Logic (Consumer)
 ├── 🔌 NotificationService.java         // The Interface
 ├── 📧 EmailNotificationService.java    // Impl 1 (@Primary)
 └── 📱 SmsNotificationService.java      // Impl 2
```

---

## 🚀 How To Run

### 📋 Prerequisites
*   **JDK 25** installed.
*   **Internet Access** (required for the first run to download dependencies).

### Option 1: Run via Maven Wrapper (Recommended)

**💻 On Windows (PowerShell):**
```powershell
.\mvnw.cmd spring-boot:run
```

**🍎 / 🐧 On macOS or Linux:**
```bash
./mvnw spring-boot:run
```

### Option 2: Build & Run JAR

If you want to package the app as a standalone file:

1.  **Build:**
    ```powershell
    .\mvnw.cmd clean package
    ```
2.  **Run:**
    ```powershell
    java -jar target\notification-0.0.1-SNAPSHOT.jar
    ```

---

## 🖥️ Expected Output

When the application runs successfully, your console will show the flow of control passing from the User Service to the Notification Service:

```text
UserService: Processing user...
EmailNotificationService: Sending email with message: [Default Message]
```

*(Note: Since `EmailNotificationService` is marked `@Primary`, the SMS service remains idle.)*