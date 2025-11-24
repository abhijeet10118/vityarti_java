package com.grademanagement.service;

import com.grademanagement.model.Grade;
import com.grademanagement.model.Student;
import com.grademanagement.model.User;
import com.grademanagement.repository.GradeRepository;
import com.grademanagement.repository.UserRepository;
import com.grademanagement.util.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service class for generating reports and analytics
 */
public class ReportService {
    private GradeRepository gradeRepository;
    private UserRepository userRepository;

    public ReportService(GradeRepository gradeRepository, UserRepository userRepository) {
        this.gradeRepository = gradeRepository;
        this.userRepository = userRepository;
    }

    /**
     * Generate student transcript
     */
    public String generateStudentTranscript(String studentId) {
        StringBuilder report = new StringBuilder();
        report.append("=".repeat(60)).append("\n");
        report.append("STUDENT TRANSCRIPT\n");
        report.append("=".repeat(60)).append("\n\n");

        User user = userRepository.getUserById(studentId);
        if (user instanceof Student) {
            Student student = (Student) user;
            report.append("Student ID: ").append(student.getStudentId()).append("\n");
            report.append("Name: ").append(student.getFullName()).append("\n");
            report.append("Department: ").append(student.getDepartment()).append("\n");
            report.append("Year: ").append(student.getYear()).append("\n\n");
        }

        List<Grade> grades = gradeRepository.getGradesByStudentId(studentId);

        if (grades.isEmpty()) {
            report.append("No grades found.\n");
        } else {
            report.append(String.format("%-15s %-25s %-8s %-8s %-8s\n",
                    "Subject ID", "Subject Name", "Marks", "Grade", "Credits"));
            report.append("-".repeat(60)).append("\n");

            double totalPoints = 0.0;
            int totalCredits = 0;

            for (Grade grade : grades) {
                report.append(String.format("%-15s %-25s %-8.2f %-8s %-8d\n",
                        grade.getSubject().getSubjectId(),
                        grade.getSubject().getSubjectName(),
                        grade.getMarks(),
                        grade.getLetterGrade(),
                        grade.getSubject().getCredits()));

                totalPoints += grade.getGradePoints() * grade.getSubject().getCredits();
                totalCredits += grade.getSubject().getCredits();
            }

            report.append("-".repeat(60)).append("\n");
            double gpa = totalCredits > 0 ? totalPoints / totalCredits : 0.0;
            report.append(String.format("\nGPA: %.2f\n", gpa));
        }

        report.append("=".repeat(60)).append("\n");

        Logger.info("Transcript generated for student: " + studentId);
        return report.toString();
    }

    /**
     * Generate class statistics report
     */
    public String generateClassStatistics(String subjectId) {
        StringBuilder report = new StringBuilder();
        report.append("=".repeat(60)).append("\n");
        report.append("CLASS STATISTICS REPORT\n");
        report.append("=".repeat(60)).append("\n\n");

        List<Grade> allGrades = gradeRepository.getAllGrades();
        List<Grade> subjectGrades = new ArrayList<>();

        for (Grade grade : allGrades) {
            if (grade.getSubject().getSubjectId().equals(subjectId)) {
                subjectGrades.add(grade);
            }
        }

        if (subjectGrades.isEmpty()) {
            report.append("No grades found for this subject.\n");
        } else {
            report.append("Subject: ").append(subjectGrades.get(0).getSubject().getSubjectName()).append("\n");
            report.append("Total Students: ").append(subjectGrades.size()).append("\n\n");

            // Calculate statistics
            double sum = 0.0;
            double min = 100.0;
            double max = 0.0;

            Map<String, Integer> gradeDistribution = new HashMap<>();

            for (Grade grade : subjectGrades) {
                double marks = grade.getMarks();
                sum += marks;
                if (marks < min)
                    min = marks;
                if (marks > max)
                    max = marks;

                String letterGrade = grade.getLetterGrade();
                gradeDistribution.put(letterGrade, gradeDistribution.getOrDefault(letterGrade, 0) + 1);
            }

            double average = sum / subjectGrades.size();

            report.append(String.format("Average Marks: %.2f\n", average));
            report.append(String.format("Highest Marks: %.2f\n", max));
            report.append(String.format("Lowest Marks: %.2f\n", min));
            report.append("\nGrade Distribution:\n");
            report.append("-".repeat(30)).append("\n");

            for (Map.Entry<String, Integer> entry : gradeDistribution.entrySet()) {
                report.append(String.format("%-5s: %d students\n", entry.getKey(), entry.getValue()));
            }
        }

        report.append("=".repeat(60)).append("\n");

        Logger.info("Class statistics generated for subject: " + subjectId);
        return report.toString();
    }

    /**
     * Generate department performance report
     */
    public String generateDepartmentReport(String department) {
        StringBuilder report = new StringBuilder();
        report.append("=".repeat(60)).append("\n");
        report.append("DEPARTMENT PERFORMANCE REPORT\n");
        report.append("=".repeat(60)).append("\n\n");
        report.append("Department: ").append(department).append("\n\n");

        List<Grade> allGrades = gradeRepository.getAllGrades();
        List<Grade> deptGrades = new ArrayList<>();

        for (Grade grade : allGrades) {
            if (grade.getSubject().getDepartment().equals(department)) {
                deptGrades.add(grade);
            }
        }

        if (deptGrades.isEmpty()) {
            report.append("No grades found for this department.\n");
        } else {
            Map<String, List<Double>> subjectMarks = new HashMap<>();

            for (Grade grade : deptGrades) {
                String subjectId = grade.getSubject().getSubjectId();
                subjectMarks.putIfAbsent(subjectId, new ArrayList<>());
                subjectMarks.get(subjectId).add(grade.getMarks());
            }

            report.append("Subject-wise Average:\n");
            report.append("-".repeat(60)).append("\n");
            report.append(String.format("%-15s %-25s %-15s\n", "Subject ID", "Subject Name", "Average Marks"));
            report.append("-".repeat(60)).append("\n");

            for (Map.Entry<String, List<Double>> entry : subjectMarks.entrySet()) {
                String subjectId = entry.getKey();
                List<Double> marks = entry.getValue();
                double avg = marks.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);

                Grade sampleGrade = deptGrades.stream()
                        .filter(g -> g.getSubject().getSubjectId().equals(subjectId))
                        .findFirst()
                        .orElse(null);

                if (sampleGrade != null) {
                    report.append(String.format("%-15s %-25s %-15.2f\n",
                            subjectId, sampleGrade.getSubject().getSubjectName(), avg));
                }
            }
        }

        report.append("=".repeat(60)).append("\n");

        Logger.info("Department report generated for: " + department);
        return report.toString();
    }

    /**
     * Get top performing students
     */
    public List<String> getTopPerformingStudents(int topN) {
        List<User> allUsers = userRepository.getAllUsers();
        Map<String, Double> studentGPAs = new HashMap<>();

        for (User user : allUsers) {
            if (user instanceof Student) {
                Student student = (Student) user;
                List<Grade> grades = gradeRepository.getGradesByStudentId(student.getUserId());

                if (!grades.isEmpty()) {
                    double totalPoints = 0.0;
                    int totalCredits = 0;

                    for (Grade grade : grades) {
                        totalPoints += grade.getGradePoints() * grade.getSubject().getCredits();
                        totalCredits += grade.getSubject().getCredits();
                    }

                    double gpa = totalCredits > 0 ? totalPoints / totalCredits : 0.0;
                    studentGPAs.put(student.getStudentId() + " - " + student.getFullName(), gpa);
                }
            }
        }

        return studentGPAs.entrySet().stream()
                .sorted((e1, e2) -> Double.compare(e2.getValue(), e1.getValue()))
                .limit(topN)
                .map(e -> e.getKey() + " (GPA: " + String.format("%.2f", e.getValue()) + ")")
                .collect(java.util.stream.Collectors.toList());
    }
}
