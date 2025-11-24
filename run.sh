#!/bin/bash

echo "Running Student Grade Management System..."
echo

if [ ! -d "out" ]; then
    echo "Compiling first..."
    bash compile.sh
    echo
fi

java -cp out com.grademanagement.Main

