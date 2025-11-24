# Quick Start Guide

## Prerequisites

- Java JDK 8 or higher installed
- Command line access (Terminal/PowerShell/CMD)

## Quick Setup

### Windows

1. Open Command Prompt or PowerShell in the project directory
2. Run: `compile.bat`
3. Run: `run.bat`

### Linux/Mac

1. Open Terminal in the project directory
2. Make scripts executable: `chmod +x compile.sh run.sh`
3. Run: `./compile.sh`
4. Run: `./run.sh`

### Manual Compilation

```bash
# Create output directory
mkdir out

# Compile
javac -d out -sourcepath src/main/java src/main/java/com/grademanagement/**/*.java

# Run
java -cp out com.grademanagement.Main
```

## Default Login Credentials

**Admin:**

- Username: `admin`
- Password: `admin123`

## First Steps

1. **Login as Admin** to explore admin features
2. **Register a new Student** to test student registration
3. **Register a new Teacher** to test teacher registration
4. **Add a Subject** (as Admin)
5. **Add Grades** (as Teacher)
6. **View Grades** (as Student)
7. **Generate Reports** (as Admin)

## Testing the System

### Test User Registration

1. Choose option 2 (Register as Student)
2. Fill in all required fields
3. Try registering with invalid email (should show error)
4. Try registering with short password (should show error)

### Test Grade Management

1. Login as Teacher or Admin
2. Add a subject first
3. Add grades for students
4. Try adding grade with marks > 100 (should show error)
5. Update an existing grade
6. View all subjects

### Test Reporting

1. Login as Student
2. View your grades
3. Generate transcript
4. Login as Admin
5. Generate department report
6. View top performing students

## Project Structure

```
java project/
├── src/main/java/          # Source code
├── src/test/java/          # Unit tests
├── data/                   # Data files (created at runtime)
├── logs/                   # Log files (created at runtime)
├── docs/                   # Documentation
├── README.md               # Main documentation
├── statement.md            # Project statement
└── compile.bat/run.bat     # Build scripts
```

## Troubleshooting

### Compilation Errors

- Ensure Java JDK is installed: `java -version`
- Check that you're in the project root directory
- Verify all source files are present

### Runtime Errors

- Ensure `out` directory exists after compilation
- Check that data files are being created in `data/` directory
- Check `logs/system.log` for error details

### Package Errors in IDE

- Set `src/main/java` as source root in your IDE
- Refresh/reimport the project
- The code is correct - these are IDE configuration issues

## Next Steps

1. Read `README.md` for complete documentation
2. Review `statement.md` for project requirements
3. Check `docs/` for architecture and design diagrams
4. Run unit tests to verify functionality
5. Explore the code to understand the implementation
