# 🚀 ReactiveGroupsMicroservice

A reactive microservice built using **Kotlin** and **Spring WebFlux**, enabling full management of groups and user-group relations.  
The project follows a **non-blocking reactive architecture** and uses **MongoDB Reactive Repositories**.

---

## 📌 Features

- ➕ Create new groups with validation
- 🔍 Get group by ID
- 📜 List all groups with pagination (SSE streaming supported)
- 🔁 Update group information
- ❌ Delete all groups and related user associations
- 👥 Add users to groups
- 👤 Retrieve all users in a group
- 📂 Retrieve all groups of a user
- 🧪 Custom error handling with `BadRequestException`
- 🐳 Fully containerized using Docker & Docker Compose

---

## 🏗 Architecture

```text
┌────────────────────────────────────┐
│          REST API Layer           │
│         GroupController.kt        │
└─────────────────────┬─────────────┘
                      │
┌─────────────────────┴─────────────┐
│          Service Layer            │
│   GroupService & GroupServiceImp  │
└─────────────────────┬─────────────┘
                      │
┌─────────────────────┴─────────────┐
│        Persistence Layer          │
│ GroupCrud & GroupUserRelationCrud │
│  (ReactiveMongoRepository)        │
└─────────────────────┬─────────────┘
                      │
           🗃 MongoDB Reactive DB
```

---

## 🛠 Tech Stack

| Technology | Purpose |
|------------|----------|
| Kotlin | Programming language |
| Spring Boot + WebFlux | Reactive REST API |
| MongoDB Reactive | Data persistence |
| Project Reactor (`Mono`, `Flux`) | Reactive programming |
| Gradle (Kotlin DSL) | Build tool |
| Docker & Docker Compose | Deployment |
| Custom Exceptions | Error handling |

---

## ▶ How to Run

### 🔧 Local Run

```bash
./gradlew bootRun
```

---

### 🐳 Run with Docker

```bash
./gradlew clean build -x test
docker build -t reactive-groups-service .
docker-compose up
```

> MongoDB and the microservice start automatically using `compose.yaml`.

---

## 📂 Important Files

| File | Description |
|------|-------------|
| `GroupController.kt` | REST API endpoints |
| `GroupService.kt` | Service interface |
| `GroupServiceImp.kt` | Business logic |
| `Converter.kt` | Entity ↔ DTO (Boundary) mapping |
| `GroupCrud.kt` | Group repository |
| `GroupUserRelationCrud.kt` | User-group relation repository |
| `GroupEntity.kt` | Group MongoDB entity |
| `GroupUserRelationEntity.kt` | Relation entity |
| `BadRequestException.kt` | Custom validation exception |
| `ReactiveGroupsMicroserviceApplication.kt` | Application entry point |

---

## 📡 API Endpoints

### 📌 Groups

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/groups` | Create a new group |
| `GET` | `/groups/{groupId}` | Get group by ID |
| `GET` | `/groups` | Retrieve all groups (SSE streaming) |
| `PUT` | `/groups/{groupId}` | Update group details |
| `DELETE` | `/groups` | Delete all groups |

---

### 👥 Group-User Relations

| Method | Endpoint | Description |
|--------|----------|-------------|
| `PUT` | `/groups/{groupId}/users` | Add user to group |
| `GET` | `/groups/{groupId}/users` | List all users in a group |
| `GET` | `/groups/{email}/groups` | List all groups for a user |
| `DELETE` | `/groups/{groupId}/users` | Remove all users from group |

---

## 📝 Example – Create Group

### Request

```json
{
  "name": "DevOps Team",
  "description": "Core development group"
}
```

### Response

```json
{
  "id": "65af13c1d8c3a1b",
  "name": "DevOps Team",
  "creationDate": "27-11-2024",
  "description": "Core development group"
}
```

---

## ❗ Error Handling

A `400 Bad Request` is returned when invalid input is provided.

Example:

```kotlin
throw BadRequestException("Name must not be null")
```

---

## 🔍 Notes & Best Practices

- All endpoints are built using **reactive programming**
- Outputs are streamed using `MediaType.TEXT_EVENT_STREAM_VALUE` where relevant
- DTOs (`Boundary`) are completely separated from database models (`Entity`)
- Business rules are handled inside `GroupServiceImp.kt`
- Date format: `dd-MM-yyyy`

---

## 📦 Requirements

| Requirement | Version |
|-------------|----------|
| JDK | 21+ |
| Gradle | 8+ |
| MongoDB | 4.4+ |
| Docker (optional) | Latest |

---

## 👤 Author

Developed as part of an advanced **Reactive Microservices** course project.

---

## 🏁 Final Notes

✔ Fully reactive  
✔ Pagination + event streaming  
✔ Clean architecture  
✔ Docker-ready  
✔ Easy to extend and integrate

---

