# OmniTask

A desktop todo-list app. Java 25 + JavaFX 26. File-based storage. No database.

---

## What's inside

- Register / Login with BCrypt-hashed passwords
- Password hint recovery
- Create, view, delete tasks per user
- Live clock on the dashboard
- Data persists in flat files

---

## Run it

```bash
git clone https://github.com/FaizuddinSafa-24/Todo-list-beta
cd Todo-list-beta
mvn clean javafx:run
```

Needs JDK 25+ and JavaFX 26.

---

## Structure

```
src/main/java/com/omnitask/
├── App.java
├── user/
│   ├── AbstractUser.java
│   ├── User.java
│   ├── UserItem.java
│   └── UserManager.java
├── task/
│   ├── AbstrackTask.java
│   ├── Task.java
│   ├── TaskItem.java
│   └── TaskManager.java
├── controller/
│   ├── LoginController.java
│   ├── RegisterController.java
│   ├── TaskViewController.java
│   └── ForgotPasswordController.java
└── module-info.java

src/main/resources/fxml/
├── Login.fxml
├── Register.fxml
├── TaskView.fxml
└── ForgotPassword.fxml
```

`user/` handles auth. `task/` handles task CRUD. `controller/` handles UI. Abstract base classes with concrete implementations and JavaFX item wrappers.

---

## Data

```
Login/users_<username>.txt  → username|hashedPass|hint|favAnswer
Tasks/tasks_<username>.txt  → title|text|dueDate|done
```

---

## Status

Learning project. Not production-ready. Built to practice JavaFX OOP patterns.

Fork it if you want. SQLite, dark mode, due-date sorting — all fair game.

---

## License

MIT
