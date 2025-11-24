package com.grademanagement.model;

/**
 * Subject class representing a course/subject
 */
public class Subject {
    private String subjectId;
    private String subjectName;
    private String department;
    private int credits;
    private String teacherId;

    public Subject() {
    }

    public Subject(String subjectId, String subjectName, String department, int credits, String teacherId) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.department = department;
        this.credits = credits;
        this.teacherId = teacherId;
    }

    // Getters and Setters
    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subjectId='" + subjectId + '\'' +
                ", subjectName='" + subjectName + '\'' +
                ", department='" + department + '\'' +
                ", credits=" + credits +
                ", teacherId='" + teacherId + '\'' +
                '}';
    }
}
