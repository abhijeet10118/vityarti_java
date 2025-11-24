@echo off
echo Running Student Grade Management System...
echo.

if not exist "out" (
    echo Compiling first...
    call compile.bat
    echo.
)

java -cp out com.grademanagement.Main

pause

