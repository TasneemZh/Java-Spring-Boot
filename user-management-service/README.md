# 🍃 User Management Service

> **A clean, lightweight Spring Boot project demonstrating best practices for reading and updating data using `JdbcTemplate` and H2.**

---

## 📖 Overview

Welcome! This project is a focused demonstration of how to implement a **Data Access Layer** using modern Spring Boot and standard JDBC. Instead of using a heavy ORM like Hibernate, this project focuses on the speed and simplicity of SQL control via `JdbcTemplate`.

**What happens when you run it?**
1.  🚀 The app boots up.
2.  💾 An in-memory database initializes with sample data.
3.  ⚙️ A script runs to fetch users, update a specific user's status, and verify changes.
4.  🏁 The results are printed directly to your console.

---

## 🛠️ Tech Stack

This project is built on the cutting edge:

*   ☕ **Java 25**
*   🍃 **Spring Boot 4.0.2**
*   🗄️ **Spring JDBC** (`JdbcTemplate`)
*   💾 **H2 Database** (In-Memory)
*   📦 **Maven Wrapper** (No manual Maven installation needed)

---

## 🧠 Spring Concepts & Architecture

We utilize a robust set of Spring features to demonstrate professional coding patterns:

### **Core & Bootstrapping**
*   `@SpringBootApplication` — The entry point.
*   `@Bean` + `CommandLineRunner` — Executes the demo logic immediately after startup.

### **Stereotypes & Components**
*   `@Service` — Holds business logic (`UserService`).
*   `@Repository` — Manages data access (`UserRepository`).
*   `@Component` — Utility/helper beans (`DbOperationLogger`).

### **Advanced Scopes & Wiring**
*   **Prototype Scope:** `@Scope("prototype")` is used on the logger to ensure a new instance is created when requested.
*   **Dependency Injection:** We use **Constructor Injection** for repositories (best practice for immutability) and Field/Setter Autowiring where appropriate.

### **Data Initialization**
*   `schema.sql` — Automatically creates the DB structure.
*   `data.sql` — Seeds the DB with initial rows.

---

## 🔄 Application Flow

Here is the step-by-step lifecycle of the application:

1.  **Start:** Spring Context initializes.
2.  **DB Setup:** H2 in-memory instance is spun up.
3.  **Schema:** `users` table is created via SQL.
4.  **Seed:** 3 sample users are inserted.
5.  **Execution:** The `CommandLineRunner` triggers:
    *   👀 **Read:** Fetches and prints all users.
    *   ✏️ **Update:** Changes User `id=2` to **Active**.
    *   ✅ **Verify:** Prints User `id=2` to confirm the update.
    *   📋 **Final Check:** Prints the full list again.

---

## ✅ Best Practices

This isn't just code that works; it's code that scales.

*   **Clean Layering:** Strict separation of concerns (`Model` ➡️ `Repository` ➡️ `Service`).
*   **Safe SQL:** Uses **Parameterized Queries** (`?`) to prevent SQL injection.
*   **Spring Native:** Leverages `JdbcTemplate` for exception translation and resource management.
*   **Immutability:** Uses `final` dependencies with constructor injection in the Repository layer.
*   **Version Control Ready:** Database schema and data are defined in code (`.sql` files), not manual setup.

---

## 📂 Project Structure

```text
src/main/java/com/tutorial/jdbc
 ├── 📄 JdbcApplication.java         // Main Entry Point
 ├── 📂 model
 │    └── 📄 User.java               // POJO Domain Object
 ├── 📂 repository
 │    └── 📄 UserRepository.java     // Data Access Layer
 ├── 📂 service
 │    └── 📄 UserService.java        // Business Logic
 └── 📂 util
      └── 📄 DbOperationLogger.java  // Utility Bean

src/main/resources
 ├── ⚙️ application.properties       // Config
 ├── 📄 schema.sql                   // Table Creation
 └── 📄 data.sql                     // Sample Data
```

---

## 🚀 How To Run

### 📋 Prerequisites
*   **JDK 25** installed.
*   **Internet Access** (required for the first run to download dependencies).

### ⚡ Running the App

Open your terminal or command prompt in the project root folder.

**💻 On Windows:**
```powershell
.\mvnw.cmd spring-boot:run
```

**🍎 / 🐧 On macOS or Linux:**
```bash
./mvnw spring-boot:run
```

> **Result:** Watch the console! You will see the logs showing the user fetch and update operations in real-time.

---

## ℹ️ Notes

*   **Ephemeral Data:** Since we use H2 (In-Memory), the database is wiped clean every time you stop the application.
*   **Console App:** This is a startup runner demo, not a REST API. It is designed to run once and exit or stay idle.