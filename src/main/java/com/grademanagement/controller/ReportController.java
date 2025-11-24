package com.grademanagement.controller;

import com.grademanagement.service.ReportService;

import java.util.List;

/**
 * Controller class for report generation operations
 */
public class ReportController {
    private ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    /**
     * Generate student transcript
     */
    public String generateStudentTranscript(String studentId) {
        return reportService.generateStudentTranscript(studentId);
    }

    /**
     * Generate class statistics
     */
    public String generateClassStatistics(String subjectId) {
        return reportService.generateClassStatistics(subjectId);
    }

    /**
     * Generate department report
     */
    public String generateDepartmentReport(String department) {
        return reportService.generateDepartmentReport(department);
    }

    /**
     * Get top performing students
     */
    public List<String> getTopPerformingStudents(int topN) {
        return reportService.getTopPerformingStudents(topN);
    }
}
