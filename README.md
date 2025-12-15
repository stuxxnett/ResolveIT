# 🚀 ResolveIT – Grievance & Complaint Management System

ResolveIT is a web-based grievance and complaint management system designed to simplify the process of filing, tracking, and resolving complaints in a transparent and efficient manner.  
This project is being developed as part of an **internship milestone-based workflow**.

---

## 📌 Project Overview

Traditional grievance systems often suffer from lack of transparency, delayed responses, and poor tracking.  
ResolveIT aims to solve these issues by providing:

- A centralized digital platform for complaint management
- Role-based access for users and administrators
- Real-time complaint status tracking

---

## 🛠️ Technology Stack

### Frontend
- HTML
- Tailwind CSS
- JavaScript (Fetch API)

### Backend
- Spring Boot
- RESTful APIs
- JWT-based Authentication
- BCrypt Password Encryption

### Database
- MySQL

### Tools & Platforms
- IntelliJ IDEA
- Git & GitHub
- Postman

---

## ✨ Features Implemented

### 👤 User Features
- User registration (signup)
- Secure login using JWT
- User dashboard displaying:
    - Total complaints
    - Pending complaints (highlighted in red)
    - Resolved complaints (highlighted in green)
- Submit new complaints
- Track complaint resolution status

### 🛡️ Admin Features
- Admin login
- Admin dashboard with complaint statistics
- View all complaints
- Resolve complaints

---

## 🔐 Authentication & Security

- JWT (JSON Web Token) based authentication
- Token generated on successful login
- Token stored on frontend and sent via Authorization header
- Passwords securely encrypted using BCrypt
- Role information embedded in JWT token

⚠️ **Note:**  
Backend role-based authorization enforcement is partially implemented and will be completed in upcoming milestones.

---

## 🗂️ Project Structure

```text
resolveit/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── resolveit/
│   │   │           ├── controller/
│   │   │           ├── service/
│   │   │           ├── repository/
│   │   │           ├── model/
│   │   │           ├── dto/
│   │   │           ├── security/
│   │   │           └── config/
│   │   └── resources/
│   │       ├── static/
│   │       └── application.properties
│   └── test/
│
├── pom.xml
└── README.md
```


---

## 📊 Current Project Status (Milestone 2)

### ✅ Completed
- Frontend UI and dashboards
- User & Admin login
- JWT authentication
- MySQL database integration
- Complaint submission and resolution
- Signup functionality

### 🔄 In Progress
- Backend role-based authorization
- API security hardening

---

## 🚀 Future Enhancements

- Image and video uploads with complaints
- Department-wise complaint assignment
- Email notifications
- Complete backend role-based access control
- Cloud deployment

---

## 🧑‍💻 Developer

**Utkarsh Sharma**  
Internship Project  
GitHub: [https://github.com/stuxxnett](https://github.com/stuxxnett)

---

## 📄 License

This project is developed for educational and internship purposes.
