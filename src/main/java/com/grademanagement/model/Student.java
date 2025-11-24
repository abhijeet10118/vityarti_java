package com.grademanagement.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Student class extending User functionality
 */
public class Student extends User {
    private String studentId;
    private String fullName;
    private String department;
    private int year;
    private List<Grade> grades;

    public Student() {
        super();
        this.grades = new ArrayList<>();
        setRole("STUDENT");
    }

    public Student(String userId, String username, String password, String email,
            String studentId, String fullName, String department, int year) {
        super(userId, username, password, email, "STUDENT");
        this.studentId = studentId;
        this.fullName = fullName;
        this.department = department;
        this.year = year;
        this.grades = new ArrayList<>();
    }

    // Getters and Setters
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    public void addGrade(Grade grade) {
        this.grades.add(grade);
    }

    public double calculateGPA() {
        if (grades.isEmpty()) {
            return 0.0;
        }

        double totalPoints = 0.0;
        int totalCredits = 0;

        for (Grade grade : grades) {
            totalPoints += grade.getGradePoints() * grade.getSubject().getCredits();
            totalCredits += grade.getSubject().getCredits();
        }

        return totalCredits > 0 ? totalPoints / totalCredits : 0.0;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", fullName='" + fullName + '\'' +
                ", department='" + department + '\'' +
                ", year=" + year +
                ", username='" + getUsername() + '\'' +
                ", email='" + getEmail() + '\'' +
                '}';
    }
}
