# 👔 Job Board System

> **A Spring Boot backend for managing job listings, featuring advanced filtering, pagination, and PostgreSQL persistence.**

---

## 📖 Overview

This project demonstrates how to build a scalable REST API using a **Layered Architecture** (`Controller` ➡️ `Service` ➡️ `Repository`).

It goes beyond basic CRUD by implementing real-world features like **Pagination**, **DTO mappings** (to hide database entities), and **Centralized Error Handling**.

**Key Features:**
*   📝 **CRUD Operations:** Create, Read, Update, and Delete job posts.
*   📄 **Pagination:** Efficiently handle large lists of data.
*   🔍 **Smart Filtering:** Filter by Job Title or Salary Range.
*   🔎 **Global Search:** Keyword search across titles, descriptions, and locations.
*   🛡️ **Error Handling:** Clean JSON error messages instead of stack traces.

---

## 🛠️ Tech Stack

Built with the latest tools:

*   ☕ **Java 25**
*   🍃 **Spring Boot 4.0.3**
*   🐘 **PostgreSQL** (Production-grade Database)
*   🌶️ **Lombok** (Boilerplate reduction)
*   📜 **OpenAPI / Swagger UI** (Interactive Documentation)
*   📦 **Maven Wrapper** (No manual install needed)

---

## 🧠 Spring Concepts & Architecture

### Core Components
*   **`@RestControllerAdvice`**: A global safety net that catches errors everywhere and formats them nicely for the API client.
*   **`@RequiredArgsConstructor`**: Uses **Lombok** to generate constructors, enabling clean **Dependency Injection** without `@Autowired` on fields.
*   **`DTO Pattern`**: We use Java **Records** (`JobPostRequest`, `JobPostResponse`) to ensure we never expose our internal Database Entities directly to the public API.

### The Logic Flow
1.  🚀 **Request:** Client hits `JobPostController`.
2.  ⚙️ **Business Logic:** Controller passes data to `JobPostService`.
3.  💾 **Data Access:** Service calls `JobPostRepository`.
4.  🔄 **Mapping:** Entities are converted to DTOs.
5.  ✅ **Response:** JSON is returned to the client (or an Error JSON if something failed).

---

## 🔌 API Endpoints

**Base URL:** `http://localhost:8080/api/jobs`

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| **POST** | `/` | Create a new job post. |
| **GET** | `/` | List all jobs (supports pagination). |
| **GET** | `/{id}` | Get a specific job by ID. |
| **PUT** | `/{id}` | Update an existing job. |
| **DELETE** | `/{id}` | Delete a job. |
| **GET** | `/search` | Keyword search (Title, Description, Company, Location). |

### 🎛️ Query Parameters (Filtering)
You can filter the `GET /api/jobs` endpoint using these parameters:
*   `?title=Engineer` (Filter by title)
*   `?minSalary=50000&maxSalary=100000` (Filter by range)
*   `?page=0&size=10&sort=title,asc` (Pagination & Sorting)

---

## ✅ Best Practices Implemented

*   **Layered Architecture:** Strict separation between Web, Logic, and Data layers.
*   **Immutable DTOs:** Using Java `records` for safer data transfer.
*   **Constructor Injection:** Ensures dependencies are not null and makes testing easier.
*   **Standardized Responses:** Using `ResponseEntity` to control HTTP status codes explicitly.

---

## 🚀 How To Run

### 1️⃣ Prerequisites
*   **JDK 25** installed.
*   **PostgreSQL** installed and running.
*   **Maven** (optional, wrapper provided).

### 2️⃣ Database Setup
Open your SQL tool (pgAdmin, DBeaver, or terminal) and run:

```sql
CREATE DATABASE job_board_db;
```

### 3️⃣ Configuration
Check `src/main/resources/application.properties`. Update the username/password to match your local PostgreSQL setup:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/job_board_db
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password
```

### 4️⃣ Start the Application

**💻 On Windows:**
```powershell
.\mvnw.cmd spring-boot:run
```

**🍎 / 🐧 On macOS or Linux:**
```bash
./mvnw spring-boot:run
```

### 5️⃣ Verify & Explore
Once the app is running, open your browser to view the **Interactive Documentation**:

👉 **[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)**

---

## 📂 Project Structure

```text
src/main/java/com/tutorial/jobapi
 ├── 🎮 controller   // API Layer (Endpoints)
 ├── ⚙️ service      // Business Logic
 ├── 💾 repository   // Database Access
 ├── 📦 entity       // Database Tables
 ├── 📨 dto          // Data Transfer Objects
 └── ⚠️ exception    // Global Error Handling
```
