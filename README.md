# OmniTask

A desktop todo-list app built with Java + JavaFX. Stores tasks per user. No database, no server, no nonsense.

---

## What it does

- Register / Login with BCrypt-hashed passwords
- Password hint recovery system
- Create, view, and delete tasks per user
- Live clock. Data persists in flat files.

## Stack

- Java 25, JavaFX 26, Maven
- BCrypt via `jbcrypt`
- File-based storage (no SQLite yet — it's coming)

## Run it

```bash
git clone https://github.com/FaizuddinSafa-24/Todo-list-beta
cd todo-list-beta
mvn clean javafx:run
```

Requires JDK 25+ and JavaFX 26 on your machine.

## Structure

```
src/main/
├── java/com/omnitask/
│   ├── App.java
│   ├── UserManager.java       # auth + file I/O
│   ├── TaskManager.java       # task CRUD
│   ├── LoginController.java
│   ├── RegisterController.java
│   ├── TaskViewController.java
│   └── ForgotPasswordController.java
└── resources/fxml/
    ├── Login.fxml
    ├── Register.fxml
    ├── TaskView.fxml
    └── ForgotPassword.fxml
```

## Data storage

```
Login/tasks_<username>.txt     → username|hashedPass|hint|favAnswer
Tasks/tasks_<username>.txt     → title|text|dueDate|done
```

## Status

Work in progress. Built for learning JavaFX OOP architecture — not production.  
PRs and forks welcome if you want to add SQLite, themes, or due-date sorting.

## License

MIT
