# How to Run the Project - Terminal Commands

## Step-by-Step Instructions

### Step 1: Open Terminal/Command Prompt

- **Windows**: Press `Win + R`, type `cmd` or `powershell`, press Enter
- **Mac/Linux**: Open Terminal

### Step 2: Navigate to Project Directory

```bash
cd "C:\Users\abhij\OneDrive\Desktop\java project"
```

### Step 3: Create Output Directory

```bash
# Windows (PowerShell)
if (-not (Test-Path "out")) { New-Item -ItemType Directory -Path "out" }

# Windows (CMD)
if not exist "out" mkdir out

# Mac/Linux
mkdir -p out
```

### Step 4: Compile All Java Files

```bash
# Windows (PowerShell or CMD)
javac -d out -sourcepath src/main/java src/main/java/com/grademanagement/**/*.java

# If the above doesn't work, compile individually:
javac -d out -sourcepath src/main/java src/main/java/com/grademanagement/Main.java src/main/java/com/grademanagement/model/*.java src/main/java/com/grademanagement/service/*.java src/main/java/com/grademanagement/repository/*.java src/main/java/com/grademanagement/controller/*.java src/main/java/com/grademanagement/util/*.java src/main/java/com/grademanagement/exception/*.java

# Mac/Linux
javac -d out -sourcepath src/main/java src/main/java/com/grademanagement/**/*.java
```

### Step 5: Run the Application

```bash
java -cp out com.grademanagement.Main
```

## Alternative: Use the Batch Scripts (Windows)

### Option 1: Compile and Run Separately

```bash
# Compile
compile.bat

# Run
run.bat
```

### Option 2: One-Line Compile and Run

```bash
compile.bat && run.bat
```

## Complete Command Sequence (Copy-Paste Ready)

### For Windows PowerShell:

```powershell
cd "C:\Users\abhij\OneDrive\Desktop\java project"
if (-not (Test-Path "out")) { New-Item -ItemType Directory -Path "out" }
javac -d out -sourcepath src/main/java src/main/java/com/grademanagement/Main.java src/main/java/com/grademanagement/model/*.java src/main/java/com/grademanagement/service/*.java src/main/java/com/grademanagement/repository/*.java src/main/java/com/grademanagement/controller/*.java src/main/java/com/grademanagement/util/*.java src/main/java/com/grademanagement/exception/*.java
java -cp out com.grademanagement.Main
```

### For Windows CMD:

```cmd
cd "C:\Users\abhij\OneDrive\Desktop\java project"
if not exist "out" mkdir out
javac -d out -sourcepath src/main/java src/main/java/com/grademanagement/Main.java src/main/java/com/grademanagement/model/*.java src/main/java/com/grademanagement/service/*.java src/main/java/com/grademanagement/repository/*.java src/main/java/com/grademanagement/controller/*.java src/main/java/com/grademanagement/util/*.java src/main/java/com/grademanagement/exception/*.java
java -cp out com.grademanagement.Main
```

### For Mac/Linux:

```bash
cd "/path/to/java project"
mkdir -p out
javac -d out -sourcepath src/main/java src/main/java/com/grademanagement/**/*.java
java -cp out com.grademanagement.Main
```

## Quick Test Commands

### Check Java Installation:

```bash
java -version
javac -version
```

### Verify Files Exist:

```bash
# Windows
dir src\main\java\com\grademanagement\Main.java

# Mac/Linux
ls src/main/java/com/grademanagement/Main.java
```

### Check Compilation Output:

```bash
# Windows
dir out\com\grademanagement

# Mac/Linux
ls out/com/grademanagement
```

## Troubleshooting

### If compilation fails:

1. Make sure Java JDK is installed: `java -version`
2. Check you're in the correct directory
3. Verify all source files exist
4. Try compiling files one by one to identify the issue

### If runtime fails:

1. Make sure compilation was successful
2. Check that `out` directory contains compiled `.class` files
3. Verify the classpath: `java -cp out com.grademanagement.Main`

## Default Login Credentials

Once the application runs:

- **Admin Username**: `admin`
- **Admin Password**: `admin123`
