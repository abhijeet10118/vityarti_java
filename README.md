# Student Grade Management System

A simple Java-based console application for managing students, subjects, and grades.
The system supports Admin, Teacher, and Student roles and provides basic reporting features like transcripts, top performers, and class statistics.

---

## 📌 Table of Contents

* [Overview](#overview)
* [Features](#features)
* [Prerequisites](#prerequisites)
* [Installation & Setup](#installation--setup)
* [Running the Project](#running-the-project)
* [Default Login](#default-login)
* [Usage Guide](#usage-guide)
* [Project Structure](#project-structure)
* [Troubleshooting](#troubleshooting)
* [Testing](#testing)
* [Documentation](#documentation)
* [Tech Stack](#tech-stack)
* [Notes](#notes)
* [Quick Start](#quick-start)

---

## 🎯 Overview

This project is a console-based Student Grade Management System built using Java.
It handles user accounts, subjects, grading, and basic academic reports. All data is stored in simple text files, making the system easy to run without external dependencies.

---

## ✨ Features

### 🔐 User Management

* Register Student, Teacher, and Admin accounts
* Login system with role-based access
* Basic profile storage

### 📝 Grade & Subject Management

* Add, edit, and remove subjects
* Add and update grades
* GPA and letter-grade calculation
* Input validation

### 📊 Reports

* Student transcripts
* Class performance statistics
* Department-wise reports
* Top performing students

---

## 📦 Prerequisites

* **Java JDK 8 or higher**
  Check installation:

  ```
  java -version
  javac -version
  ```
* Command Line (CMD/PowerShell/Terminal)
* IDE or text editor (optional): IntelliJ, Eclipse, VS Code, etc.

---

## 🚀 Installation & Setup

### 1️⃣ Verify Java

```
java -version
javac -version
```

### 2️⃣ Move to the Project Folder

Windows:

```
cd "C:\Users\abhij\OneDrive\Desktop\java project"
```

Mac/Linux:

```
cd "/path/to/java project"
```

### 3️⃣ Check Folder Structure

```
java project/
 ├── src/main/java/com/grademanagement/
 ├── README.md
 ├── compile.bat / compile.sh
 ├── run.bat / run.sh
```

---

## ▶️ Running the Project

### 💻 Method 1: Using Provided Scripts

#### Windows:

```
compile.bat
run.bat
```

#### Mac/Linux:

```
chmod +x compile.sh run.sh
./compile.sh
./run.sh
```

---

### 💻 Method 2: Manual Compilation

Create an output folder:
Windows CMD:

```
if not exist "out" mkdir out
```

Mac/Linux:

```
mkdir -p out
```

Compile:

```
javac -d out -sourcepath src/main/java src/main/java/com/grademanagement/**/*.java
```

Run:

```
java -cp out com.grademanagement.Main
```

---

## 🔐 Default Login

When running the project for the first time, the system creates a default admin:

* **Username:** admin
* **Password:** admin123

If login fails:

* Delete `data/users.txt`
* Run again

---

## 📖 Usage Guide

### 👨‍💼 Admin

* Add subjects
* View all users
* Generate department and performance reports

### 👨‍🏫 Teacher

* Add grades
* Update grades
* View subjects and statistics

### 👨‍🎓 Student

* View grades
* Generate transcripts
* Check GPA

---

## 📁 Project Structure

```
java project/
 ├── src/
 │   └── main/java/com/grademanagement/
 │       ├── Main.java
 │       ├── model/
 │       ├── service/
 │       ├── repository/
 │       ├── controller/
 │       ├── util/
 │       └── exception/
 │
 ├── data/        # Generated at runtime
 ├── logs/        # System logs
 ├── out/         # Compiled classes
 ├── docs/        # Extra documentation
 ├── compile.bat / compile.sh
 ├── run.bat / run.sh
 └── README.md
```

---

## 🔧 Troubleshooting

### “java: command not found”

Install JDK and add it to PATH.

### “Package does not exist”

Compile from the project root and ensure your `src/main/java` path is correct.

### “Invalid username or password”

Delete `data/users.txt` and re-run.

### IDE showing red errors

Mark `src/main/java` as *Sources Root* (IDE issue only).

---

## 🧪 Testing

If using JUnit:

```
javac -d out -sourcepath src/main/java:src/test/java -cp junit.jar src/test/java/**/*.java
java -cp out:junit.jar org.junit.runner.JUnitCore com.grademanagement.service.UserServiceTest
```

Manual Testing Checklist:

* [ ] Register users
* [ ] Login (all roles)
* [ ] Add subjects
* [ ] Add grades
* [ ] View reports
* [ ] Transcript generation
* [ ] Invalid input tests

---

## 📚 Documentation

Inside the `docs/` folder:

* `architecture.md` – system design
* `diagrams.md` – UML diagrams
* `IDE_SETUP.md` – IDE configuration
* `QUICKSTART.md` – setup shortcuts

---

## 🛠️ Tech Stack

* Java (Core + OOP)
* File I/O for data storage
* JUnit 5 (optional for tests)
* MVC-style structure (Controller, Service, Repository)

---

## 📝 Notes

* No external DB required
* Logs stored at `logs/system.log`
* Data stored in text files in `data/`
* Console-based interface

---

## 🚀 Quick Start

```
compile.bat
run.bat

# Login
Username: admin
Password: admin123
```

---

Happy Coding! 🎓
