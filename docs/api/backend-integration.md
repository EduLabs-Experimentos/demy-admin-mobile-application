# Backend Integration – Demy Android App

This document describes how the Android mobile application integrates with the **Demy Backend API**.

---

## 1. Overview
- The app communicates with the backend via **RESTful API**.
- All requests are made over **HTTPS**.
- Authentication is handled using **JWT tokens** provided by the backend.

---

## 2. Base URL
```
https://api.demy.upc.edu/v1
```

---

## 3. Authentication

### Login
```
POST /auth/login
Request:
{
    "email": "user`example.com",
    "password": "123456"
}

Response (200):
{
    "token": "jwt-token-value",
    "expiresIn": 3600
}

Response (401):
{
    "error": "Invalid credentials"
}
```

### Token Usage
- The `token` must be included in all subsequent requests:  
  `Authorization: Bearer <token>`

---

## 4. Example Endpoint – User Profile
```
GET /users/me
Headers:
Authorization: Bearer <token>

Response (200):
{
    "id": "u12345",
    "name": "Andrea",
    "email": "u202224135`upc.edu.pe",
    "role": "admin"
}

Response (403):
{
    "error": "Forbidden"
}
```

---

## 5. Error Handling
- The app should handle errors gracefully:
    - **401 Unauthorized** → prompt user to log in again.
    - **403 Forbidden** → show “access denied” message.
    - **500 Internal Server Error** → show generic error with retry option.

---

## 6. Notes
- API endpoints may evolve; always check the latest **OpenAPI specification** in the backend repository.
- Sensitive data (tokens, passwords) must **never** be logged or exposed in screenshots.  
