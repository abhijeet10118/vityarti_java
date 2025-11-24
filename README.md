# Student Grade Management System

## 📋 Table of Contents

- [Project Overview](#project-overview)
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Installation & Setup](#installation--setup)
- [How to Run](#how-to-run)
- [Default Login Credentials](#default-login-credentials)
- [Usage Guide](#usage-guide)
- [Project Structure](#project-structure)
- [Troubleshooting](#troubleshooting)
- [Testing](#testing)
- [Documentation](#documentation)

## 🎯 Project Overview

The Student Grade Management System is a comprehensive Java application designed to manage student grades, subjects, and generate various reports for educational institutions. The system supports multiple user roles (Admin, Teacher, Student) with role-based access control and provides a complete workflow for grade management.

## ✨ Features

### Functional Modules

1. **User Management Module**

   - User registration (Student, Teacher, Admin)
   - User authentication and login
   - User profile management
   - Role-based access control

2. **Grade Management Module**

   - Add, update, and delete grades
   - Subject management (CRUD operations)
   - Automatic grade calculation (letter grades and GPA)
   - Grade validation and error handling

3. **Reporting & Analytics Module**
   - Student transcript generation
   - Class statistics reports
   - Department performance reports
   - Top performing students list

## 📦 Prerequisites

Before running this project, ensure you have the following installed:

- **Java Development Kit (JDK) 8 or higher**

  - Check installation: `java -version` and `javac -version`
  - Download from: [Oracle JDK](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://openjdk.org/)

- **Command Line Interface**

  - Windows: Command Prompt or PowerShell
  - Mac/Linux: Terminal

- **Text Editor or IDE (Optional)**
  - IntelliJ IDEA, Eclipse, VS Code, or any text editor

## 🚀 Installation & Setup

### Step 1: Verify Java Installation

Open your terminal/command prompt and run:

```bash
java -version
javac -version
```

You should see output like:

```
java version "17.0.x" or higher
javac version "17.0.x" or higher
```

If you see an error, install Java JDK first.

### Step 2: Navigate to Project Directory

```bash
# Windows
cd "C:\Users\abhij\OneDrive\Desktop\java project"

# Mac/Linux
cd "/path/to/java project"
```

### Step 3: Verify Project Structure

Make sure you have the following structure:

```
java project/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── grademanagement/
├── README.md
├── compile.bat (Windows)
├── compile.sh (Mac/Linux)
├── run.bat (Windows)
└── run.sh (Mac/Linux)
```

## ▶️ How to Run

### Method 1: Using Batch Scripts (Recommended for Windows)

#### Windows:

```bash
# Compile the project
compile.bat

# Run the application
run.bat
```

#### Mac/Linux:

```bash
# Make scripts executable (first time only)
chmod +x compile.sh run.sh

# Compile the project
./compile.sh

# Run the application
./run.sh
```

### Method 2: Manual Compilation and Execution

#### Step 1: Create Output Directory

**Windows (PowerShell):**

```powershell
if (-not (Test-Path "out")) { New-Item -ItemType Directory -Path "out" }
```

**Windows (CMD):**

```cmd
if not exist "out" mkdir out
```

**Mac/Linux:**

```bash
mkdir -p out
```

#### Step 2: Compile All Java Files

**Windows (PowerShell or CMD):**

```bash
javac -d out -sourcepath src/main/java src/main/java/com/grademanagement/Main.java src/main/java/com/grademanagement/model/*.java src/main/java/com/grademanagement/service/*.java src/main/java/com/grademanagement/repository/*.java src/main/java/com/grademanagement/controller/*.java src/main/java/com/grademanagement/util/*.java src/main/java/com/grademanagement/exception/*.java
```

**Mac/Linux:**

```bash
javac -d out -sourcepath src/main/java src/main/java/com/grademanagement/**/*.java
```

**Alternative (if above doesn't work):**

```bash
# Compile all files individually
javac -d out -sourcepath src/main/java src/main/java/com/grademanagement/*.java
javac -d out -sourcepath src/main/java src/main/java/com/grademanagement/model/*.java
javac -d out -sourcepath src/main/java src/main/java/com/grademanagement/service/*.java
javac -d out -sourcepath src/main/java src/main/java/com/grademanagement/repository/*.java
javac -d out -sourcepath src/main/java src/main/java/com/grademanagement/controller/*.java
javac -d out -sourcepath src/main/java src/main/java/com/grademanagement/util/*.java
javac -d out -sourcepath src/main/java src/main/java/com/grademanagement/exception/*.java
```

#### Step 3: Run the Application

```bash
java -cp out com.grademanagement.Main
```

### Method 3: One-Line Command (Windows PowerShell)

```powershell
cd "C:\Users\abhij\OneDrive\Desktop\java project"; if (-not (Test-Path "out")) { New-Item -ItemType Directory -Path "out" }; javac -d out -sourcepath src/main/java src/main/java/com/grademanagement/Main.java src/main/java/com/grademanagement/model/*.java src/main/java/com/grademanagement/service/*.java src/main/java/com/grademanagement/repository/*.java src/main/java/com/grademanagement/controller/*.java src/main/java/com/grademanagement/util/*.java src/main/java/com/grademanagement/exception/*.java; java -cp out com.grademanagement.Main
```

## 🔐 Default Login Credentials

When you first run the application, a default admin user is automatically created:

- **Username:** `admin`
- **Password:** `admin123`

**Note:** If you see "Invalid username or password" on first run, the admin user might not have been created yet. Try:

1. Exit the application
2. Delete the `data/users.txt` file (if it exists)
3. Run the application again

## 📖 Usage Guide

### First Time Setup

1. **Run the application** using one of the methods above
2. **Login as Admin** using default credentials
3. **Add Subjects** (Admin menu → Add Subject)
4. **Register Students and Teachers** (Login menu → Register)
5. **Add Grades** (Login as Teacher → Add Grade)
6. **View Reports** (Login as Student/Admin → Generate Reports)

### User Roles and Capabilities

#### 👨‍💼 Admin

- Add subjects
- View all users
- View all subjects
- Generate department reports
- View top performing students

#### 👨‍🏫 Teacher

- Add grades for students
- Update existing grades
- View all subjects
- Generate class statistics

#### 👨‍🎓 Student

- View personal grades
- Generate academic transcript
- View GPA

### Example Workflow

1. **Login as Admin:**

   ```
   Select option: 1
   Username: admin
   Password: admin123
   ```

2. **Add a Subject:**

   ```
   Select option: 1 (Add Subject)
   Subject ID: SUB001
   Subject Name: Java Programming
   Department: Computer Science
   Credits: 3
   Teacher ID: TCH001
   ```

3. **Register a Student:**

   - Logout first
   - Select option: 2 (Register as Student)
   - Fill in all required fields

4. **Login as Teacher and Add Grade:**

   ```
   Select option: 1 (Add Grade)
   Grade ID: GRD001
   Student ID: STU001
   Subject ID: SUB001
   Marks: 85.5
   ```

5. **Login as Student and View Grades:**
   ```
   Select option: 1 (View My Grades)
   ```

## 📁 Project Structure

```
java project/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── grademanagement/
│   │               ├── Main.java                 # Main application entry point
│   │               ├── model/                     # Data models
│   │               │   ├── User.java
│   │               │   ├── Student.java
│   │               │   ├── Teacher.java
│   │               │   ├── Admin.java
│   │               │   ├── Grade.java
│   │               │   └── Subject.java
│   │               ├── service/                   # Business logic
│   │               │   ├── UserService.java
│   │               │   ├── GradeService.java
│   │               │   └── ReportService.java
│   │               ├── repository/                # Data access
│   │               │   ├── UserRepository.java
│   │               │   └── GradeRepository.java
│   │               ├── controller/               # Request handling
│   │               │   ├── UserController.java
│   │               │   ├── GradeController.java
│   │               │   └── ReportController.java
│   │               ├── util/                      # Utilities
│   │               │   ├── Validator.java
│   │               │   ├── Logger.java
│   │               │   └── FileHandler.java
│   │               └── exception/                # Custom exceptions
│   │                   ├── InvalidInputException.java
│   │                   ├── UserNotFoundException.java
│   │                   └── GradeNotFoundException.java
│   └── test/
│       └── java/                                  # Unit tests
│           └── com/
│               └── grademanagement/
│                   ├── service/
│                   │   ├── UserServiceTest.java
│                   │   └── GradeServiceTest.java
│                   └── util/
│                       └── ValidatorTest.java
├── data/                                          # Data files (created at runtime)
│   ├── users.txt
│   ├── grades.txt
│   └── subjects.txt
├── logs/                                          # Log files (created at runtime)
│   └── system.log
├── out/                                           # Compiled classes (created after compilation)
├── docs/                                          # Documentation
│   ├── architecture.md
│   └── diagrams.md
├── README.md                                      # This file
├── statement.md                                   # Project statement
├── QUICKSTART.md                                  # Quick start guide
├── IDE_SETUP.md                                   # IDE configuration guide
├── compile.bat                                    # Windows compile script
├── compile.sh                                     # Mac/Linux compile script
├── run.bat                                        # Windows run script
└── run.sh                                         # Mac/Linux run script
```

## 🔧 Troubleshooting

### Issue: "java: command not found" or "javac: command not found"

**Solution:**

- Install Java JDK (not just JRE)
- Add Java to your system PATH
- Restart your terminal after installation

### Issue: "The system cannot find the path specified"

**Solution:**

- Make sure you're in the correct project directory
- Check that all source files exist in `src/main/java/com/grademanagement/`
- Create the `out` directory manually if needed

### Issue: "Package does not exist" or "Cannot find symbol"

**Solution:**

- Make sure you're compiling from the project root directory
- Verify the `-sourcepath` parameter points to `src/main/java`
- Try compiling all files together (not individually)

### Issue: "Invalid username or password" on first login

**Solution:**

1. Delete `data/users.txt` file (if exists)
2. Run the application again - admin user will be created automatically
3. Try login with: username `admin`, password `admin123`

### Issue: Red lines in IDE (package declaration errors)

**Solution:**

- This is an IDE configuration issue, not a code error
- See `IDE_SETUP.md` for IDE-specific fixes
- The code will compile and run fine from command line
- Mark `src/main/java` as Sources Root in your IDE

### Issue: "Failed to write to log file"

**Solution:**

- This has been fixed in the latest version
- The `logs` directory is now created automatically
- Recompile the project if you see this error

### Issue: Compilation succeeds but runtime fails

**Solution:**

- Check that `out` directory contains compiled `.class` files
- Verify classpath: `java -cp out com.grademanagement.Main`
- Make sure all dependencies are compiled

### Issue: Application runs but menu doesn't appear

**Solution:**

- Check that you're running from the project root directory
- Verify data files are being created in `data/` directory
- Check `logs/system.log` for error messages

## 🧪 Testing

### Run Unit Tests

If you have JUnit set up:

```bash
# Compile test files
javac -d out -sourcepath src/main/java:src/test/java -cp "path/to/junit.jar" src/test/java/com/grademanagement/**/*.java

# Run tests
java -cp "out:path/to/junit.jar" org.junit.runner.JUnitCore com.grademanagement.service.UserServiceTest
```

### Manual Testing Checklist

- [ ] User registration (Student, Teacher)
- [ ] User login (all roles)
- [ ] Add subject (Admin)
- [ ] Add grade (Teacher)
- [ ] View grades (Student)
- [ ] Generate transcript (Student)
- [ ] Generate reports (Admin)
- [ ] Input validation (try invalid inputs)
- [ ] Error handling (try wrong credentials)

## 📚 Documentation

Additional documentation files:

- **`statement.md`** - Problem statement, scope, and requirements
- **`QUICKSTART.md`** - Quick start guide
- **`IDE_SETUP.md`** - How to fix IDE configuration issues
- **`docs/architecture.md`** - System architecture documentation
- **`docs/diagrams.md`** - UML diagrams and design documentation

## 🛠️ Technologies Used

- **Java** - Core programming language (Java 8+)
- **JUnit 5** - Unit testing framework (for tests)
- **File I/O** - For data persistence
- **Object-Oriented Programming** - Classes, inheritance, polymorphism
- **Design Patterns** - Repository pattern, Service layer, MVC architecture

## 📝 Notes

- Data is stored in text files (`data/` directory)
- Logs are written to `logs/system.log`
- All directories are created automatically when needed
- The application is console-based (no GUI)
- Default admin user is created on first run

## 🚀 Quick Start (TL;DR)

```bash
# Windows
compile.bat
run.bat

# Mac/Linux
chmod +x compile.sh run.sh
./compile.sh
./run.sh

# Login
Username: admin
Password: admin123
```

## 📞 Support

If you encounter any issues:

1. Check the **Troubleshooting** section above
2. Review the **logs/system.log** file for errors
3. Verify Java installation: `java -version`
4. Ensure you're in the correct directory
5. Try deleting `data/` and `logs/` directories and running again

## 📄 License

This project is created for educational purposes.

---

**Happy Coding! 🎓**
#   v i t y a r t i _ j a v a  
 