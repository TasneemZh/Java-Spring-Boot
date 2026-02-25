# 🔐 Secure Login Portal

A clean **Spring Boot web application** demonstrating secure, role-based authentication using:

* 🗄️ MySQL-backed user storage
* 🔐 Spring Security
* 🖥️ JSP views
* 🧂 A pinch of AOP logging

Built for learning. Structured like a real application. Not a spaghetti demo.

---

## 📌 Overview

This project implements:

* 🧾 Form-based login (`/login`)
* 🔒 BCrypt password hashing
* 🧑‍💼 Role-based access control (`USER` & `ADMIN`)
* 🚪 Secure logout with CSRF protection
* 🌱 Automatic seed user on startup

> OAuth2 (Google Sign-In) is intentionally postponed for now. Discipline is rare. Good.

---

## 🧰 Tech Stack

* ☕ Java 25
* 🚀 Spring Boot 4.0.3
* 🌐 Spring Web MVC
* 🛡️ Spring Security
* 🗃️ Spring Data JPA
* 🐬 MySQL
* 📄 JSP (Tomcat Jasper + JSTL)
* 🪶 Lombok
* 📦 Maven

---

## 🧠 Spring Concepts Used

This project demonstrates practical use of:

* `@SpringBootApplication` → Application bootstrap
* `@Controller` + `@GetMapping` → MVC routing
* `@ModelAttribute` → Shared view model data
* `@Entity` + `JpaRepository` → Persistence layer
* `UserDetailsService` + `UserDetails` → Custom authentication source
* `SecurityFilterChain` → Security configuration
* `DaoAuthenticationProvider` → DB-backed authentication
* `PasswordEncoder` (BCrypt) → Secure password verification
* `CommandLineRunner` → Startup data seeding
* `@Aspect` (`@Before`) → Service-level logging

This is proper layered architecture. Not controller-chaos.

---

## 🔐 Authentication & Authorization Flow

Here’s what happens when someone logs in:

1. 🧑 User submits credentials at `/login`
2. 🛡️ Spring Security delegates to `DaoAuthenticationProvider`
3. 🔍 `CustomUserDetailsService` loads user from MySQL
4. 🔑 `BCryptPasswordEncoder` verifies the password
5. ✅ On success → redirect to `/home`

### 🔒 Authorization Rules

* `/login` → Public
* `/admin/**` → Requires `ROLE_ADMIN`
* Any other route → Requires authentication

Configured via:

```java
hasRole("ADMIN")
```

Clean. Centralized. Predictable.

---

## 🌐 Application Routes

| Method | Route              | Description        |
| ------ | ------------------ | ------------------ |
| GET    | `/login`           | Login page         |
| POST   | `/login`           | Authenticate       |
| GET    | `/home`            | Authenticated home |
| GET    | `/admin/dashboard` | Admin-only page    |
| POST   | `/logout`          | Logout + redirect  |

---

## 🌱 Seed User (Auto-Created on Startup)

If `testuser` does not exist, the app creates:

* 👤 Username: `testuser`
* 🔑 Password: `Test@1234`
* 🏷️ Role: `ROLE_USER`

The login page displays these credentials for quick testing.

Idempotent seeding. No duplicates. No chaos.

---

## 🛡️ Security Best Practices Applied

* 🔐 Passwords stored as BCrypt hashes
* 🧠 Role checks centralized in configuration
* 🧾 CSRF tokens included in login/logout forms
* 🧩 Security separated from controllers
* 🌱 Safe startup seeding
* 🗂️ Clean separation of layers
* 📁 JSP files placed under `/WEB-INF` (no direct access)

You avoided about 80% of beginner mistakes. Impressive.

---

## 🏗️ Project Structure

```
src/main/java/com/tutorial/portal
│
├── config       → Security configuration + seed logic
├── controller   → MVC controllers
├── service      → User lookup service
├── security     → Custom UserDetails
├── entity       → JPA entity (AppUser)
├── repository   → JPA repository
├── aspect       → Logging aspect
│
src/main/webapp/WEB-INF/jsp
    ├── login.jsp
    ├── home.jsp
    └── admin.jsp
│
src/main/resources
    └── application.properties
```

Readable. Maintainable. Adult-level structure.

---

# ▶️ How to Run

## 1️⃣ Prerequisites

* ☕ Java 25
* 🐬 MySQL running locally
* 📦 Maven (or Maven Wrapper)

---

## 2️⃣ Configure Database

Edit:

```
src/main/resources/application.properties
```

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/portal_db?createDatabaseIfNotExist=true
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password
```

Ensure:

```properties
spring.jpa.hibernate.ddl-auto=update
```

This auto-creates/updates the `app_user` table.

---

## 3️⃣ Start the Application

### 🪟 Windows

```powershell
.\mvnw.cmd spring-boot:run
```

### 🍎 macOS / 🐧 Linux

```bash
./mvnw spring-boot:run
```

---

## 4️⃣ Access in Browser

* 🔐 Login → `http://localhost:8080/login`
* 🏠 Home → `http://localhost:8080/home`
* 🧑‍💼 Admin → `http://localhost:8080/admin/dashboard`

---

## 5️⃣ Test Login

Use:

* Username: `testuser`
* Password: `Test@1234`

To test admin access, create a user with `ROLE_ADMIN`.

---

## 🧑‍💼 Manual Admin Insert (Optional)

If you prefer SQL over clicking around:

```sql
INSERT INTO app_user (username, password, role)
VALUES ('admin', '$2a$10$GDZee6FE8.FfCQGMJf5Dq.JGsi9i8OlTiQKcjYgl6Y/KOW9zs.Zqm', 'ROLE_ADMIN');
```

Password for the above hash:

```
ChangeMe123!
```

---

## 🎯 Current Scope

### ✅ Implemented

* Username/password authentication
* Role-based authorization
* Seed user
* Secure logout
* JSP view rendering

### ⏳ Deferred

* OAuth2 Google Sign-In
