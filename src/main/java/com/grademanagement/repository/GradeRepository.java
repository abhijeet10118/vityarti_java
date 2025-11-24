package com.grademanagement.repository;

import com.grademanagement.model.Grade;
import com.grademanagement.model.Subject;
import com.grademanagement.util.FileHandler;
import com.grademanagement.util.Logger;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Repository class for grade data persistence
 */
public class GradeRepository {
    private static final String GRADES_FILE = "data/grades.txt";
    private static final String SUBJECTS_FILE = "data/subjects.txt";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private Map<String, Grade> grades;
    private Map<String, Subject> subjects;

    public GradeRepository() {
        this.grades = new HashMap<>();
        this.subjects = new HashMap<>();
        loadSubjects();
        loadGrades();
    }

    /**
     * Load subjects from file
     */
    private void loadSubjects() {
        try {
            if (FileHandler.fileExists(SUBJECTS_FILE)) {
                List<String> lines = FileHandler.readFile(SUBJECTS_FILE);
                for (String line : lines) {
                    if (!line.trim().isEmpty()) {
                        Subject subject = parseSubject(line);
                        if (subject != null) {
                            subjects.put(subject.getSubjectId(), subject);
                        }
                    }
                }
            }
        } catch (IOException e) {
            Logger.error("Error loading subjects from file", e);
        }
    }

    /**
     * Load grades from file
     */
    private void loadGrades() {
        try {
            if (FileHandler.fileExists(GRADES_FILE)) {
                List<String> lines = FileHandler.readFile(GRADES_FILE);
                for (String line : lines) {
                    if (!line.trim().isEmpty()) {
                        Grade grade = parseGrade(line);
                        if (grade != null) {
                            grades.put(grade.getGradeId(), grade);
                        }
                    }
                }
            }
        } catch (IOException e) {
            Logger.error("Error loading grades from file", e);
        }
    }

    /**
     * Parse subject from string
     */
    private Subject parseSubject(String line) {
        try {
            String[] parts = line.split("\\|");
            if (parts.length != 5)
                return null;

            return new Subject(parts[0], parts[1], parts[2],
                    Integer.parseInt(parts[3]), parts[4]);
        } catch (Exception e) {
            Logger.error("Error parsing subject: " + line, e);
            return null;
        }
    }

    /**
     * Parse grade from string
     */
    private Grade parseGrade(String line) {
        try {
            String[] parts = line.split("\\|");
            if (parts.length != 5)
                return null;

            String subjectId = parts[2];
            Subject subject = subjects.get(subjectId);

            if (subject == null) {
                Logger.warning("Subject not found for grade: " + subjectId);
                return null;
            }

            Grade grade = new Grade(parts[0], parts[1], subject,
                    Double.parseDouble(parts[3]),
                    LocalDate.parse(parts[4], DATE_FORMATTER));
            return grade;
        } catch (Exception e) {
            Logger.error("Error parsing grade: " + line, e);
            return null;
        }
    }

    /**
     * Save subjects to file
     */
    private void saveSubjects() {
        try {
            List<String> lines = new ArrayList<>();
            for (Subject subject : subjects.values()) {
                lines.add(subject.getSubjectId() + "|" + subject.getSubjectName() + "|" +
                        subject.getDepartment() + "|" + subject.getCredits() + "|" +
                        subject.getTeacherId());
            }
            FileHandler.writeFile(SUBJECTS_FILE, lines);
        } catch (IOException e) {
            Logger.error("Error saving subjects to file", e);
        }
    }

    /**
     * Save grades to file
     */
    private void saveGrades() {
        try {
            List<String> lines = new ArrayList<>();
            for (Grade grade : grades.values()) {
                lines.add(grade.getGradeId() + "|" + grade.getStudentId() + "|" +
                        grade.getSubject().getSubjectId() + "|" + grade.getMarks() + "|" +
                        grade.getDateAssigned().format(DATE_FORMATTER));
            }
            FileHandler.writeFile(GRADES_FILE, lines);
        } catch (IOException e) {
            Logger.error("Error saving grades to file", e);
        }
    }

    /**
     * Add a new subject
     */
    public void addSubject(Subject subject) {
        subjects.put(subject.getSubjectId(), subject);
        saveSubjects();
        Logger.info("Subject added: " + subject.getSubjectId());
    }

    /**
     * Get subject by ID
     */
    public Subject getSubjectById(String subjectId) {
        return subjects.get(subjectId);
    }

    /**
     * Get all subjects
     */
    public List<Subject> getAllSubjects() {
        return new ArrayList<>(subjects.values());
    }

    /**
     * Add a new grade
     */
    public void addGrade(Grade grade) {
        grades.put(grade.getGradeId(), grade);
        saveGrades();
        Logger.info("Grade added: " + grade.getGradeId());
    }

    /**
     * Get grade by ID
     */
    public Grade getGradeById(String gradeId) {
        return grades.get(gradeId);
    }

    /**
     * Get all grades for a student
     */
    public List<Grade> getGradesByStudentId(String studentId) {
        List<Grade> studentGrades = new ArrayList<>();
        for (Grade grade : grades.values()) {
            if (grade.getStudentId().equals(studentId)) {
                studentGrades.add(grade);
            }
        }
        return studentGrades;
    }

    /**
     * Get all grades
     */
    public List<Grade> getAllGrades() {
        return new ArrayList<>(grades.values());
    }

    /**
     * Update grade
     */
    public void updateGrade(Grade grade) {
        if (grades.containsKey(grade.getGradeId())) {
            grades.put(grade.getGradeId(), grade);
            saveGrades();
            Logger.info("Grade updated: " + grade.getGradeId());
        }
    }

    /**
     * Delete grade
     */
    public void deleteGrade(String gradeId) {
        if (grades.remove(gradeId) != null) {
            saveGrades();
            Logger.info("Grade deleted: " + gradeId);
        }
    }
}
