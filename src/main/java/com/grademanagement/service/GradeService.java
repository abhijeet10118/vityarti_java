package com.grademanagement.service;

import com.grademanagement.exception.GradeNotFoundException;
import com.grademanagement.exception.InvalidInputException;
import com.grademanagement.model.Grade;
import com.grademanagement.model.Subject;
import com.grademanagement.repository.GradeRepository;
import com.grademanagement.util.Logger;
import com.grademanagement.util.Validator;

import java.time.LocalDate;
import java.util.List;

/**
 * Service class for grade management operations
 */
public class GradeService {
    private GradeRepository gradeRepository;

    public GradeService(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    /**
     * Add a new subject
     */
    public Subject addSubject(String subjectId, String subjectName, String department,
            int credits, String teacherId) throws InvalidInputException {

        Validator.validateNonEmpty(subjectId, "Subject ID");
        Validator.validateNonEmpty(subjectName, "Subject Name");
        Validator.validateNonEmpty(department, "Department");
        Validator.validatePositive(credits, "Credits");
        Validator.validateNonEmpty(teacherId, "Teacher ID");

        if (gradeRepository.getSubjectById(subjectId) != null) {
            throw new InvalidInputException("Subject ID already exists");
        }

        Subject subject = new Subject(subjectId, subjectName, department, credits, teacherId);
        gradeRepository.addSubject(subject);

        Logger.info("Subject added: " + subjectId);
        return subject;
    }

    /**
     * Add a new grade
     */
    public Grade addGrade(String gradeId, String studentId, String subjectId,
            double marks, LocalDate dateAssigned)
            throws InvalidInputException {

        Validator.validateNonEmpty(gradeId, "Grade ID");
        Validator.validateNonEmpty(studentId, "Student ID");
        Validator.validateNonEmpty(subjectId, "Subject ID");
        Validator.validateMarks(marks);

        if (dateAssigned == null) {
            dateAssigned = LocalDate.now();
        }

        Subject subject = gradeRepository.getSubjectById(subjectId);
        if (subject == null) {
            throw new InvalidInputException("Subject not found: " + subjectId);
        }

        if (gradeRepository.getGradeById(gradeId) != null) {
            throw new InvalidInputException("Grade ID already exists");
        }

        Grade grade = new Grade(gradeId, studentId, subject, marks, dateAssigned);
        gradeRepository.addGrade(grade);

        Logger.info("Grade added: " + gradeId);
        return grade;
    }

    /**
     * Get grade by ID
     */
    public Grade getGradeById(String gradeId) throws GradeNotFoundException {
        Grade grade = gradeRepository.getGradeById(gradeId);
        if (grade == null) {
            throw new GradeNotFoundException("Grade not found: " + gradeId);
        }
        return grade;
    }

    /**
     * Get all grades for a student
     */
    public List<Grade> getGradesByStudentId(String studentId) {
        return gradeRepository.getGradesByStudentId(studentId);
    }

    /**
     * Get all subjects
     */
    public List<Subject> getAllSubjects() {
        return gradeRepository.getAllSubjects();
    }

    /**
     * Get subject by ID
     */
    public Subject getSubjectById(String subjectId) throws InvalidInputException {
        Subject subject = gradeRepository.getSubjectById(subjectId);
        if (subject == null) {
            throw new InvalidInputException("Subject not found: " + subjectId);
        }
        return subject;
    }

    /**
     * Update grade
     */
    public void updateGrade(String gradeId, double marks)
            throws GradeNotFoundException, InvalidInputException {

        Grade grade = getGradeById(gradeId);
        Validator.validateMarks(marks);

        grade.setMarks(marks);
        gradeRepository.updateGrade(grade);

        Logger.info("Grade updated: " + gradeId);
    }

    /**
     * Delete grade
     */
    public void deleteGrade(String gradeId) throws GradeNotFoundException {
        if (gradeRepository.getGradeById(gradeId) == null) {
            throw new GradeNotFoundException("Grade not found: " + gradeId);
        }
        gradeRepository.deleteGrade(gradeId);
    }

    /**
     * Get all grades
     */
    public List<Grade> getAllGrades() {
        return gradeRepository.getAllGrades();
    }
}
