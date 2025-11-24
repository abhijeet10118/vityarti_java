#!/bin/bash

echo "Compiling Student Grade Management System..."
echo

mkdir -p out

javac -d out -sourcepath src/main/java src/main/java/com/grademanagement/**/*.java

if [ $? -eq 0 ]; then
    echo
    echo "Compilation successful!"
    echo
    echo "To run the application, use:"
    echo "  java -cp out com.grademanagement.Main"
else
    echo
    echo "Compilation failed!"
fi

