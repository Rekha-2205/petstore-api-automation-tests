# Architecture Diagram – Petstore API Automation

## High-Level Architecture
                ┌──────────────────────────────┐
                │        Feature Files         │
                │    (BDD Test Scenarios)      │
                └──────────────┬───────────────┘
                               │
                               ▼
                ┌──────────────────────────────┐
                │       Step Definitions       │
                │   (Test Logic + Assertions)  │
                └──────────────┬───────────────┘
                               │
                               ▼
                ┌──────────────────────────────┐
                │         Client Layer         │
                │    (Reusable API Methods)    │
                │   PetClient / StoreClient    │
                │         UserClient           │
                └──────────────┬───────────────┘
                               │
                               ▼
                ┌──────────────────────────────┐
                │         REST Assured         │
                │    (HTTP Request Handling)   │
                └──────────────┬───────────────┘
                               │
                               ▼
                ┌──────────────────────────────┐
                │      Petstore API Server     │
                │  (Swagger Backend Services)  │
                └──────────────────────────────┘

---

## Project Structure (Layered View)


petstore-api-automation
│
├── src/main/java
│   ├── base
│   │   └── BaseTest.java
│   │
│   ├── clients
│   │   ├── PetClient.java
│   │   ├── StoreClient.java
│   │   └── UserClient.java
│   │
│   └── models
│       └── User.java
│
├── src/test/java
│   ├── steps
│   │   ├── TC1, TC2, TC3, TC4 step files
│   │
│   └── runner
│       ├── TestRunner_TC1.java
│       ├── TestRunner_TC2.java
│       ├── TestRunner_TC3.java
│       └── TestRunner_TC4.java
│
├── src/test/resources
│   └── features
│       ├── Feature files (BDD scenarios)
│
├── docs
│   └── Documentation files (TC1–TC4)
│
└── target
    └── surefire-reports (Execution Reports)


---

## Execution Flow

1. Feature file triggers scenario
2. Step Definition executes logic
3. Client layer sends API request
4. REST Assured handles HTTP communication
5. Response returned and validated
6. Report generated in `target/surefire-reports`

---

## Key Design Principles

* Separation of concerns (Feature -> Steps -> Client)
* Reusability of API methods
* Clean layered architecture
* Maintainable and scalable design