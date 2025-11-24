package com.grademanagement.model;

import java.time.LocalDate;

/**
 * Grade class representing a student's grade for a subject
 */
public class Grade {
    private String gradeId;
    private String studentId;
    private Subject subject;
    private double marks;
    private String letterGrade;
    private LocalDate dateAssigned;

    public Grade() {
    }

    public Grade(String gradeId, String studentId, Subject subject, double marks, LocalDate dateAssigned) {
        this.gradeId = gradeId;
        this.studentId = studentId;
        this.subject = subject;
        this.marks = marks;
        this.dateAssigned = dateAssigned;
        this.letterGrade = calculateLetterGrade(marks);
    }

    /**
     * Calculate letter grade based on marks
     */
    private String calculateLetterGrade(double marks) {
        if (marks >= 90)
            return "A+";
        else if (marks >= 85)
            return "A";
        else if (marks >= 80)
            return "A-";
        else if (marks >= 75)
            return "B+";
        else if (marks >= 70)
            return "B";
        else if (marks >= 65)
            return "B-";
        else if (marks >= 60)
            return "C+";
        else if (marks >= 55)
            return "C";
        else if (marks >= 50)
            return "C-";
        else if (marks >= 45)
            return "D";
        else
            return "F";
    }

    /**
     * Get grade points for GPA calculation
     */
    public double getGradePoints() {
        switch (letterGrade) {
            case "A+":
                return 4.0;
            case "A":
                return 4.0;
            case "A-":
                return 3.7;
            case "B+":
                return 3.3;
            case "B":
                return 3.0;
            case "B-":
                return 2.7;
            case "C+":
                return 2.3;
            case "C":
                return 2.0;
            case "C-":
                return 1.7;
            case "D":
                return 1.0;
            case "F":
                return 0.0;
            default:
                return 0.0;
        }
    }

    // Getters and Setters
    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
        this.letterGrade = calculateLetterGrade(marks);
    }

    public String getLetterGrade() {
        return letterGrade;
    }

    public void setLetterGrade(String letterGrade) {
        this.letterGrade = letterGrade;
    }

    public LocalDate getDateAssigned() {
        return dateAssigned;
    }

    public void setDateAssigned(LocalDate dateAssigned) {
        this.dateAssigned = dateAssigned;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "gradeId='" + gradeId + '\'' +
                ", studentId='" + studentId + '\'' +
                ", subject=" + (subject != null ? subject.getSubjectName() : "N/A") +
                ", marks=" + marks +
                ", letterGrade='" + letterGrade + '\'' +
                ", dateAssigned=" + dateAssigned +
                '}';
    }
}
