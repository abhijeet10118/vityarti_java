@echo off
echo Compiling Student Grade Management System...
echo.

if not exist "out" mkdir out

javac -d out -sourcepath src/main/java src/main/java/com/grademanagement/**/*.java

if %errorlevel% equ 0 (
    echo.
    echo Compilation successful!
    echo.
    echo To run the application, use:
    echo   java -cp out com.grademanagement.Main
) else (
    echo.
    echo Compilation failed!
    pause
)

