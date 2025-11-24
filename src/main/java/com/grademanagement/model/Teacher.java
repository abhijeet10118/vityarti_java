package com.grademanagement.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Teacher class extending User functionality
 */
public class Teacher extends User {
    private String teacherId;
    private String fullName;
    private String department;
    private List<String> subjectsTaught;

    public Teacher() {
        super();
        this.subjectsTaught = new ArrayList<>();
        setRole("TEACHER");
    }

    public Teacher(String userId, String username, String password, String email,
            String teacherId, String fullName, String department) {
        super(userId, username, password, email, "TEACHER");
        this.teacherId = teacherId;
        this.fullName = fullName;
        this.department = department;
        this.subjectsTaught = new ArrayList<>();
    }

    // Getters and Setters
    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
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

    public List<String> getSubjectsTaught() {
        return subjectsTaught;
    }

    public void setSubjectsTaught(List<String> subjectsTaught) {
        this.subjectsTaught = subjectsTaught;
    }

    public void addSubject(String subject) {
        if (!subjectsTaught.contains(subject)) {
            subjectsTaught.add(subject);
        }
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId='" + teacherId + '\'' +
                ", fullName='" + fullName + '\'' +
                ", department='" + department + '\'' +
                ", subjectsTaught=" + subjectsTaught +
                ", username='" + getUsername() + '\'' +
                '}';
    }
}
