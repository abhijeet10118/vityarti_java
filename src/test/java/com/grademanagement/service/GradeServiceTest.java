package com.grademanagement.service;

import com.grademanagement.exception.GradeNotFoundException;
import com.grademanagement.exception.InvalidInputException;
import com.grademanagement.model.Grade;
import com.grademanagement.model.Subject;
import com.grademanagement.repository.GradeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for GradeService
 */
public class GradeServiceTest {
    private GradeService gradeService;
    private GradeRepository gradeRepository;

    @BeforeEach
    public void setUp() {
        gradeRepository = new GradeRepository();
        gradeService = new GradeService(gradeRepository);
    }

    @Test
    public void testAddSubject_Success() throws InvalidInputException {
        Subject subject = gradeService.addSubject(
                "SUB001", "Java Programming", "Computer Science", 3, "TCH001");

        assertNotNull(subject);
        assertEquals("SUB001", subject.getSubjectId());
        assertEquals("Java Programming", subject.getSubjectName());
        assertEquals(3, subject.getCredits());
    }

    @Test
    public void testAddSubject_InvalidCredits() {
        assertThrows(InvalidInputException.class, () -> {
            gradeService.addSubject(
                    "SUB002", "Mathematics", "Math", -1, "TCH002");
        });
    }

    @Test
    public void testAddGrade_Success() throws InvalidInputException {
        // First add a subject
        gradeService.addSubject("SUB003", "Physics", "Science", 4, "TCH003");

        // Then add a grade
        Grade grade = gradeService.addGrade(
                "GRD001", "STU001", "SUB003", 85.5, LocalDate.now());

        assertNotNull(grade);
        assertEquals("GRD001", grade.getGradeId());
        assertEquals(85.5, grade.getMarks(), 0.01);
        assertEquals("A", grade.getLetterGrade());
    }

    @Test
    public void testAddGrade_InvalidMarks() {
        assertThrows(InvalidInputException.class, () -> {
            gradeService.addGrade("GRD002", "STU001", "SUB001", 150.0, LocalDate.now());
        });
    }

    @Test
    public void testGetGradesByStudentId() throws InvalidInputException {
        gradeService.addSubject("SUB004", "Chemistry", "Science", 3, "TCH004");
        gradeService.addGrade("GRD003", "STU002", "SUB004", 75.0, LocalDate.now());
        gradeService.addGrade("GRD004", "STU002", "SUB004", 80.0, LocalDate.now());

        List<Grade> grades = gradeService.getGradesByStudentId("STU002");
        assertEquals(2, grades.size());
    }

    @Test
    public void testUpdateGrade_Success() throws InvalidInputException, GradeNotFoundException {
        gradeService.addSubject("SUB005", "Biology", "Science", 3, "TCH005");
        gradeService.addGrade("GRD005", "STU003", "SUB005", 70.0, LocalDate.now());

        gradeService.updateGrade("GRD005", 85.0);

        Grade updatedGrade = gradeService.getGradeById("GRD005");
        assertEquals(85.0, updatedGrade.getMarks(), 0.01);
        assertEquals("A", updatedGrade.getLetterGrade());
    }

    @Test
    public void testDeleteGrade_Success() throws InvalidInputException, GradeNotFoundException {
        gradeService.addSubject("SUB006", "History", "Arts", 2, "TCH006");
        gradeService.addGrade("GRD006", "STU004", "SUB006", 65.0, LocalDate.now());

        gradeService.deleteGrade("GRD006");

        assertThrows(GradeNotFoundException.class, () -> {
            gradeService.getGradeById("GRD006");
        });
    }
}
