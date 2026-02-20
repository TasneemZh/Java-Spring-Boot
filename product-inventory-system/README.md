# 📦 Product Inventory System

> **A robust REST API built with Spring Boot to manage product data using MySQL and Spring Data JPA.**

---

## 📖 Overview

This project is a backend service that exposes **HTTP endpoints** to manage a product inventory. It demonstrates a professional, layered architecture connecting a Spring Boot application to a real **MySQL database**.

**What can this API do?**
*   ➕ **Create** new products.
*   📜 **List** all available products.
*   🔍 **Filter** products by price (e.g., "Show me everything over $500").
*   🆔 **Fetch** specific products by ID.
*   🔎 **Search** for products by their exact name.

---

## 🛠️ Tech Stack

*   ☕ **Java 25**
*   🍃 **Spring Boot 4.0.3**
*   🌐 **Spring Web** (RESTful Services)
*   🗄️ **Spring Data JPA** (Database Abstraction)
*   🐬 **MySQL Connector** (v9.6.0)
*   📦 **Maven Wrapper** (No manual Maven install needed)

---

## 🧠 Spring Concepts & Architecture

This project follows the industry-standard **Layered Architecture**:

### 1. The Controller Layer (`@RestController`)
*   **Role:** The "Front Desk". It accepts HTTP requests and sends back JSON responses.
*   **Key Annotations:**
    *   `@RequestMapping`, `@GetMapping`, `@PostMapping` — Define the URL routes.
    *   `@RequestBody` — Converts incoming JSON into Java objects.
    *   `@PathVariable` — Extracts IDs from the URL (e.g., `/products/1`).

### 2. The Service Layer (`@Service`)
*   **Role:** The "Manager". It contains the business logic.
*   **Key Annotation:** `@Transactional` — Ensures that database operations are safe (commit or rollback together).

### 3. The Data Layer (`@Repository`)
*   **Role:** The "Librarian". It talks directly to the database.
*   **Key Features:**
    *   **Derived Queries:** `findByName(...)` — Spring automatically writes the SQL for you based on the method name!
    *   **Custom JPQL:** `@Query(...)` — Used for the `findExpensiveProducts` method to define custom logic.

### 4. Dependency Injection
*   **Pattern:** **Constructor Injection**.
*   **Why?** It ensures that the Controller cannot exist without the Service, and the Service cannot exist without the Repository. It makes the code **immutable** and **test-friendly**.

---

## 🔌 API Endpoints

Here is how you interact with the system.

### 1. Create a Product
Add a new item to the inventory.
*   **URL:** `POST /products`
*   **Body:**
    ```json
    {
      "name": "Gaming Laptop",
      "price": 1299.99
    }
    ```

### 2. Get All Products
Retrieve the full list.
*   **URL:** `GET /products`

### 3. Get Expensive Products
Filter items that cost **more than** a specific price.
*   **URL:** `GET /products?price=500`
*   **Logic:** Uses a custom JPQL query (`findExpensiveProducts`).

### 4. Get Product by ID
Find a single product using its unique database ID.
*   **URL:** `GET /products/{id}`
*   **Example:** `/products/1`

### 5. Search by Name
Find products that match an exact name.
*   **URL:** `POST /products/search`
*   **Body:**
    ```json
    {
      "productName": "Gaming Laptop"
    }
    ```

---

## ⚙️ Configuration & Setup

Since this project uses a real database, you must configure it before running.

### 1️⃣ Database Prerequisites
Ensure you have **MySQL** running locally on port `3306`. Create the database:

```sql
CREATE DATABASE inventory;

USE inventory;

CREATE TABLE product (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    price DOUBLE NOT NULL
);
```

### 2️⃣ Application Properties
Open `src/main/resources/application.properties` and ensure your credentials match your local MySQL setup:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/inventory
spring.datasource.username=[username]  <-- CHANGE THIS TO YOUR DB USERNAME
spring.datasource.password=[password]  <-- CHANGE THIS TO YOUR DB PASSWORD
spring.jpa.hibernate.ddl-auto=update
```

> **Note:** `ddl-auto=update` will automatically create the `product` table for you when the app starts.

---

## 🚀 How to Run

### Option 1: Windows (PowerShell)
```powershell
.\mvnw.cmd spring-boot:run
```

### Option 2: macOS / Linux
```bash
./mvnw spring-boot:run
```

Once started, the API is available at: **`http://localhost:8080`**

---

## 🧪 Quick Testing (PowerShell)

You can copy and paste these commands directly into PowerShell to test your API.

**1. Create a Product:**
```powershell
Invoke-RestMethod -Method Post -Uri http://localhost:8080/products -ContentType "application/json" -Body '{"name":"Phone","price":699.99}'
```

**2. List All Products:**
```powershell
Invoke-RestMethod -Method Get -Uri http://localhost:8080/products
```

**3. Find Expensive Items (Price > 500):**
```powershell
Invoke-RestMethod -Method Get -Uri "http://localhost:8080/products?price=500"
```

**4. Search by Name:**
```powershell
Invoke-RestMethod -Method Post -Uri http://localhost:8080/products/search -ContentType "application/json" -Body '{"productName":"Phone"}'
```