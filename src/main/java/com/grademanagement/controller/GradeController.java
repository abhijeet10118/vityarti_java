package com.grademanagement.controller;

import com.grademanagement.exception.GradeNotFoundException;
import com.grademanagement.exception.InvalidInputException;
import com.grademanagement.model.Grade;
import com.grademanagement.model.Subject;
import com.grademanagement.service.GradeService;

import java.time.LocalDate;
import java.util.List;

/**
 * Controller class for grade management operations
 */
public class GradeController {
    private GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    /**
     * Add a new subject
     */
    public Subject addSubject(String subjectId, String subjectName, String department,
            int credits, String teacherId) throws InvalidInputException {
        return gradeService.addSubject(subjectId, subjectName, department, credits, teacherId);
    }

    /**
     * Add a new grade
     */
    public Grade addGrade(String gradeId, String studentId, String subjectId,
            double marks, LocalDate dateAssigned) throws InvalidInputException {
        return gradeService.addGrade(gradeId, studentId, subjectId, marks, dateAssigned);
    }

    /**
     * Get grades for a student
     */
    public List<Grade> getStudentGrades(String studentId) {
        return gradeService.getGradesByStudentId(studentId);
    }

    /**
     * Get all subjects
     */
    public List<Subject> getAllSubjects() {
        return gradeService.getAllSubjects();
    }

    /**
     * Update grade
     */
    public void updateGrade(String gradeId, double marks)
            throws GradeNotFoundException, InvalidInputException {
        gradeService.updateGrade(gradeId, marks);
    }

    /**
     * Delete grade
     */
    public void deleteGrade(String gradeId) throws GradeNotFoundException {
        gradeService.deleteGrade(gradeId);
    }
}
