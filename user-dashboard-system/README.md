# 👤 User Dashboard System

> **A classic Spring Boot MVC project demonstrating user registration, session management, and server-side rendering with JSP.**

---

## 📖 Overview

This application is a hands-on example of how **Spring MVC** works under the hood. It moves away from REST APIs to demonstrate traditional **Server-Side Rendering (SSR)** where the server generates HTML pages (JSPs) directly.

**What can you do in this app?**
1.  📝 **Register** a new user with validation checks.
2.  👋 **Join** as a "Guest" (simplified flow).
3.  🔐 **Access** a protected Dashboard (requires a session).
4.  🔍 **Search** for other users by email.

---

## 🛠️ Tech Stack

Built with the latest and greatest:

*   ☕ **Java 25**
*   🍃 **Spring Boot 4.0.3**
*   🌐 **Spring MVC** (Web layer)
*   ✅ **Jakarta Validation** (Input safety)
*   📄 **JSP & JSTL** (View technology)
*   🐈 **Apache Tomcat** (Embedded server)
*   📦 **Maven Wrapper** (Build tool)

---

## 🏗️ Project Structure

Here is how the application is wired together:

```text
src/main/java/com/tutorial
 ├── 🚀 MvcApplication.java          // Main Entry Point
 ├── 📂 controller
 │    └── 🎮 UserController.java     // Handles HTTP Requests & Routes
 ├── 📂 service
 │    └── ⚙️ UserService.java        // Business Logic (Register/Find)
 ├── 📂 model
 │    └── 📦 User.java               // Data Object (Name, Email)
 └── 📂 util                         // (Optional utilities)

src/main/webapp/WEB-INF/jsp
 ├── 📝 register.jsp                 // Sign-up form
 ├── 🎉 success.jsp                  // Confirmation page
 └── 📊 dashboard.jsp                // User area + Search
```

---

## 🧠 Key Spring MVC Concepts

This project demonstrates the core "Model-View-Controller" pattern:

### 1. The Controller (`@Controller`)
Instead of returning JSON (like `@RestController`), these methods return **Strings** that match the names of JSP files (e.g., `return "dashboard"`).

### 2. Data Binding (`@ModelAttribute`)
Spring automatically takes form data sent by the browser and fills the `User` Java object for you.

### 3. Validation (`@Valid` + `BindingResult`)
We use annotations to protect data integrity:
*   `@NotBlank` — Ensures fields aren't empty.
*   `@Email` — Ensures the format is valid.
*   **Error Handling:** If validation fails, the controller detects errors in `BindingResult` and sends the user back to the form with helpful messages.

### 4. Session Scope (`HttpSession`)
We don't use a database for login sessions. Instead, we store the logged-in user's email directly in the HTTP Session. This allows the server to "remember" you as you navigate between pages.

---

## 🔄 Application Flow

1.  **Start:** User visits `GET /register`.
2.  **Input:** User fills out the form.
3.  **Submit (`POST /register`):**
    *   ❌ **Invalid?** Show errors on the same page.
    *   ✅ **Valid?** Save user to memory, set session, and show `success.jsp`.
4.  **Dashboard (`GET /dashboard`):**
    *   Checks if a user is in the session.
    *   🚫 **No Session?** Redirect back to Register.
    *   ✅ **Has Session?** Show the dashboard.
5.  **Search (`GET /search`):**
    *   Takes a query parameter (`?keyword=...`).
    *   Looks up the user in the `ConcurrentHashMap`.
    *   Displays the result on the dashboard.

---

## ✅ Best Practices Implementation

*   **Separation of Concerns:** The Controller handles the web, the Service handles the logic, and the Model holds the data.
*   **Dependency Injection:** The `UserService` is injected via the Controller's constructor (the modern standard).
*   **Thread Safety:** Since we use in-memory storage, we use `ConcurrentHashMap` to prevent data corruption if two users register at the exact same time.
*   **Input Sanitization:** Inputs are trimmed and validated before processing.
*   **Defensive Routing:** Protected pages (like Dashboard) check for a valid session before rendering.

---

## ⚠️ Current Limitations
*(Since this is a learning prototype)*
*   **Ephemeral Data:** Uses in-memory storage. If you restart the app, all users are lost.
*   **Exact Match Search:** You must type the exact email to find a user.
*   **Security:** No Spring Security (passwords are not handled/encrypted).

---

## 🚀 How to Run

### 📋 Prerequisites
*   **JDK 25** installed.
*   **Internet Access** (for initial Maven dependency download).

### 1️⃣ Start the Application
Open your terminal/PowerShell in the project folder.

**Windows:**
```powershell
.\mvnw.cmd spring-boot:run
```

**Mac/Linux:**
```bash
./mvnw spring-boot:run
```

### 2️⃣ Access the App
Open your browser and navigate to:
👉 **[http://localhost:8080/register](http://localhost:8080/register)**

### 3️⃣ Troubleshooting Ports
Is port `8080` busy? Run this command to start on port `8081` instead:
```powershell
.\mvnw.cmd spring-boot:run "-Dspring-boot.run.arguments=--server.port=8081"
```

---

## 🔨 Build Command
If you want to compile the project to check for errors without running it:
```powershell
.\mvnw.cmd clean compile
```