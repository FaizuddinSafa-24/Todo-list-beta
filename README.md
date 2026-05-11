---

# OmniTask — JavaFX Desktop Todo App

A clean, file-based desktop todo-list application built with **Java 25 + JavaFX 26**. User authentication with BCrypt, per-user task management, zero external dependencies at runtime.

> No database. No server. No bloat. Just Java.

---

## ✨ Features

- **User Auth** — Register, login, password recovery via hint system
- **BCrypt Hashing** — Passwords never stored in plain text
- **Per-User Tasks** — Each user gets their own task list
- **Task CRUD** — Create, view, delete tasks with due dates
- **Live Clock** — Real-time clock on the task dashboard
- **File-Based Storage** — Everything persists in flat files, no DB setup needed
- **Clean OOP Architecture** — Abstract base classes, separation of concerns

---

## 🛠 Tech Stack

| Tech | Version |
|------|---------|
| Java | 25 |
| JavaFX | 26 |
| Maven | 25 |
| jbcrypt | 0.4 |

---

## 🚀 Quick Start

```bash
git clone https://github.com/FaizuddinSafa-24/Todo-list-beta
cd Todo-list-beta
mvn clean javafx:run
```

**Requirements:** JDK 25+, JavaFX 26 SDK

---

## 📁 Project Structure

```
src/main/
├── java/com/omnitask/
│   ├── App.java                          # Entry point
│   ├── user/                             # Authentication module
│   │   ├── AbstractUser.java             # Base user model
│   │   ├── User.java                     # Concrete user
│   │   ├── UserItem.java                 # JavaFX list item wrapper
│   │   └── UserManager.java              # Auth logic, BCrypt, file I/O
│   ├── task/                             # Task management module
│   │   ├── AbstractTask.java             # Base task model
│   │   ├── Task.java                     # Concrete task
│   │   ├── TaskItem.java                 # JavaFX list item wrapper
│   │   └── TaskManager.java              # Task CRUD + file persistence
│   ├── controller/                       # JavaFX controllers
│   │   ├── LoginController.java
│   │   ├── RegisterController.java
│   │   ├── TaskViewController.java
│   │   └── ForgotPasswordController.java
│   └── module-info.java
└── resources/fxml/
    ├── Login.fxml
    ├── Register.fxml
    ├── TaskView.fxml
    └── ForgotPassword.fxml
```

**Architecture:** Abstract → Concrete → Item wrapper → Manager. `user/` owns auth. `task/` owns task logic. `controller/` owns UI. Clean separation.

---

## 💾 Data Format

```
Login/users_<username>.txt    → username|hashedPass|hint|favAnswer
Tasks/tasks_<username>.txt    → title|text|dueDate|done
```

---

## 🗺 Roadmap

- [ ] SQLite migration (replace flat files)
- [ ] Due-date sorting
- [ ] Task edit / update
- [ ] Dark mode theme
- [ ] Unit tests

---

## 🤝 Contributing

This project was built to learn JavaFX OOP patterns. It's not production-ready.
Fork it, break it, improve it. PRs welcome — especially for the roadmap items above.

---

## 📄 License

MIT

---

## ⭐ Star this repo if it helped you learn JavaFX

---
