package com.grademanagement;

import com.grademanagement.controller.GradeController;
import com.grademanagement.controller.ReportController;
import com.grademanagement.controller.UserController;
import com.grademanagement.exception.GradeNotFoundException;
import com.grademanagement.exception.InvalidInputException;
import com.grademanagement.exception.UserNotFoundException;
import com.grademanagement.model.*;
import com.grademanagement.repository.GradeRepository;
import com.grademanagement.repository.UserRepository;
import com.grademanagement.service.GradeService;
import com.grademanagement.service.ReportService;
import com.grademanagement.service.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * Main application class for Student Grade Management System
 */
public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static UserController userController;
    private static GradeController gradeController;
    private static ReportController reportController;
    private static User currentUser;

    public static void main(String[] args) {
        initializeSystem();
        showWelcomeScreen();

        boolean running = true;
        while (running) {
            if (currentUser == null) {
                running = showLoginMenu();
            } else {
                running = showMainMenu();
            }
        }

        System.out.println("\nThank you for using Student Grade Management System!");
        scanner.close();
    }

    /**
     * Initialize system components
     */
    private static void initializeSystem() {
        UserRepository userRepository = new UserRepository();
        GradeRepository gradeRepository = new GradeRepository();

        UserService userService = new UserService(userRepository);
        GradeService gradeService = new GradeService(gradeRepository);
        ReportService reportService = new ReportService(gradeRepository, userRepository);

        userController = new UserController(userService);
        gradeController = new GradeController(gradeService);
        reportController = new ReportController(reportService);

        // Create default admin user if not exists
        try {
            userController.registerUser("ADMIN", "admin1", "admin", "admin123",
                    "admin@university.edu", "ADM001", "System Administrator",
                    "Administration", null);
        } catch (InvalidInputException e) {
            // Admin already exists, ignore
        }
    }

    /**
     * Show welcome screen
     */
    private static void showWelcomeScreen() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("    STUDENT GRADE MANAGEMENT SYSTEM");
        System.out.println("=".repeat(60));
    }

    /**
     * Show login menu
     */
    private static boolean showLoginMenu() {
        System.out.println("\n--- LOGIN MENU ---");
        System.out.println("1. Login");
        System.out.println("2. Register as Student");
        System.out.println("3. Register as Teacher");
        System.out.println("4. Exit");
        System.out.print("Select option: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                handleLogin();
                break;
            case 2:
                handleStudentRegistration();
                break;
            case 3:
                handleTeacherRegistration();
                break;
            case 4:
                return false;
            default:
                System.out.println("Invalid option!");
        }
        return true;
    }

    /**
     * Handle user login
     */
    private static void handleLogin() {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        try {
            currentUser = userController.login(username, password);
            System.out.println("\nLogin successful! Welcome, " + currentUser.getUsername());
        } catch (UserNotFoundException e) {
            System.out.println("\nError: " + e.getMessage());
        }
    }

    /**
     * Handle student registration
     */
    private static void handleStudentRegistration() {
        System.out.println("\n--- STUDENT REGISTRATION ---");
        System.out.print("User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("Full Name: ");
        String fullName = scanner.nextLine();
        System.out.print("Department: ");
        String department = scanner.nextLine();
        System.out.print("Year (1-4): ");
        int year = scanner.nextInt();
        scanner.nextLine();

        try {
            userController.registerUser("STUDENT", userId, username, password, email,
                    studentId, fullName, department, year);
            System.out.println("\nRegistration successful! You can now login.");
        } catch (InvalidInputException e) {
            System.out.println("\nError: " + e.getMessage());
        }
    }

    /**
     * Handle teacher registration
     */
    private static void handleTeacherRegistration() {
        System.out.println("\n--- TEACHER REGISTRATION ---");
        System.out.print("User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Teacher ID: ");
        String teacherId = scanner.nextLine();
        System.out.print("Full Name: ");
        String fullName = scanner.nextLine();
        System.out.print("Department: ");
        String department = scanner.nextLine();

        try {
            userController.registerUser("TEACHER", userId, username, password, email,
                    teacherId, fullName, department, null);
            System.out.println("\nRegistration successful! You can now login.");
        } catch (InvalidInputException e) {
            System.out.println("\nError: " + e.getMessage());
        }
    }

    /**
     * Show main menu based on user role
     */
    private static boolean showMainMenu() {
        String role = currentUser.getRole();

        if ("ADMIN".equals(role)) {
            return showAdminMenu();
        } else if ("TEACHER".equals(role)) {
            return showTeacherMenu();
        } else if ("STUDENT".equals(role)) {
            return showStudentMenu();
        }

        return false;
    }

    /**
     * Show admin menu
     */
    private static boolean showAdminMenu() {
        System.out.println("\n--- ADMIN MENU ---");
        System.out.println("1. Add Subject");
        System.out.println("2. View All Subjects");
        System.out.println("3. View All Users");
        System.out.println("4. Generate Department Report");
        System.out.println("5. View Top Performing Students");
        System.out.println("6. Logout");
        System.out.print("Select option: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                handleAddSubject();
                break;
            case 2:
                handleViewAllSubjects();
                break;
            case 3:
                handleViewAllUsers();
                break;
            case 4:
                handleDepartmentReport();
                break;
            case 5:
                handleTopStudents();
                break;
            case 6:
                currentUser = null;
                break;
            default:
                System.out.println("Invalid option!");
        }
        return true;
    }

    /**
     * Show teacher menu
     */
    private static boolean showTeacherMenu() {
        System.out.println("\n--- TEACHER MENU ---");
        System.out.println("1. Add Grade");
        System.out.println("2. Update Grade");
        System.out.println("3. View All Subjects");
        System.out.println("4. Generate Class Statistics");
        System.out.println("5. Logout");
        System.out.print("Select option: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                handleAddGrade();
                break;
            case 2:
                handleUpdateGrade();
                break;
            case 3:
                handleViewAllSubjects();
                break;
            case 4:
                handleClassStatistics();
                break;
            case 5:
                currentUser = null;
                break;
            default:
                System.out.println("Invalid option!");
        }
        return true;
    }

    /**
     * Show student menu
     */
    private static boolean showStudentMenu() {
        System.out.println("\n--- STUDENT MENU ---");
        System.out.println("1. View My Grades");
        System.out.println("2. Generate Transcript");
        System.out.println("3. Logout");
        System.out.print("Select option: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                handleViewMyGrades();
                break;
            case 2:
                handleGenerateTranscript();
                break;
            case 3:
                currentUser = null;
                break;
            default:
                System.out.println("Invalid option!");
        }
        return true;
    }

    /**
     * Handle add subject
     */
    private static void handleAddSubject() {
        System.out.println("\n--- ADD SUBJECT ---");
        System.out.print("Subject ID: ");
        String subjectId = scanner.nextLine();
        System.out.print("Subject Name: ");
        String subjectName = scanner.nextLine();
        System.out.print("Department: ");
        String department = scanner.nextLine();
        System.out.print("Credits: ");
        int credits = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Teacher ID: ");
        String teacherId = scanner.nextLine();

        try {
            gradeController.addSubject(subjectId, subjectName, department, credits, teacherId);
            System.out.println("\nSubject added successfully!");
        } catch (InvalidInputException e) {
            System.out.println("\nError: " + e.getMessage());
        }
    }

    /**
     * Handle add grade
     */
    private static void handleAddGrade() {
        System.out.println("\n--- ADD GRADE ---");
        System.out.print("Grade ID: ");
        String gradeId = scanner.nextLine();
        System.out.print("Student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("Subject ID: ");
        String subjectId = scanner.nextLine();
        System.out.print("Marks (0-100): ");
        double marks = scanner.nextDouble();
        scanner.nextLine();

        try {
            gradeController.addGrade(gradeId, studentId, subjectId, marks, LocalDate.now());
            System.out.println("\nGrade added successfully!");
        } catch (InvalidInputException e) {
            System.out.println("\nError: " + e.getMessage());
        }
    }

    /**
     * Handle update grade
     */
    private static void handleUpdateGrade() {
        System.out.println("\n--- UPDATE GRADE ---");
        System.out.print("Grade ID: ");
        String gradeId = scanner.nextLine();
        System.out.print("New Marks (0-100): ");
        double marks = scanner.nextDouble();
        scanner.nextLine();

        try {
            gradeController.updateGrade(gradeId, marks);
            System.out.println("\nGrade updated successfully!");
        } catch (GradeNotFoundException | InvalidInputException e) {
            System.out.println("\nError: " + e.getMessage());
        }
    }

    /**
     * Handle view all subjects
     */
    private static void handleViewAllSubjects() {
        System.out.println("\n--- ALL SUBJECTS ---");
        List<Subject> subjects = gradeController.getAllSubjects();

        if (subjects.isEmpty()) {
            System.out.println("No subjects found.");
        } else {
            System.out.printf("%-15s %-25s %-15s %-8s %-15s\n",
                    "Subject ID", "Subject Name", "Department", "Credits", "Teacher ID");
            System.out.println("-".repeat(80));
            for (Subject subject : subjects) {
                System.out.printf("%-15s %-25s %-15s %-8d %-15s\n",
                        subject.getSubjectId(), subject.getSubjectName(),
                        subject.getDepartment(), subject.getCredits(), subject.getTeacherId());
            }
        }
    }

    /**
     * Handle view all users
     */
    private static void handleViewAllUsers() {
        System.out.println("\n--- ALL USERS ---");
        UserService userService = new UserService(new UserRepository());
        List<User> users = userService.getAllUsers();

        if (users.isEmpty()) {
            System.out.println("No users found.");
        } else {
            System.out.printf("%-15s %-15s %-25s %-10s\n",
                    "User ID", "Username", "Email", "Role");
            System.out.println("-".repeat(70));
            for (User user : users) {
                System.out.printf("%-15s %-15s %-25s %-10s\n",
                        user.getUserId(), user.getUsername(), user.getEmail(), user.getRole());
            }
        }
    }

    /**
     * Handle view my grades
     */
    private static void handleViewMyGrades() {
        System.out.println("\n--- MY GRADES ---");
        List<Grade> grades = gradeController.getStudentGrades(currentUser.getUserId());

        if (grades.isEmpty()) {
            System.out.println("No grades found.");
        } else {
            System.out.printf("%-15s %-25s %-8s %-8s %-8s %-12s\n",
                    "Grade ID", "Subject Name", "Marks", "Grade", "Credits", "Date");
            System.out.println("-".repeat(80));
            for (Grade grade : grades) {
                System.out.printf("%-15s %-25s %-8.2f %-8s %-8d %-12s\n",
                        grade.getGradeId(), grade.getSubject().getSubjectName(),
                        grade.getMarks(), grade.getLetterGrade(),
                        grade.getSubject().getCredits(), grade.getDateAssigned());
            }

            // Calculate GPA
            double totalPoints = 0.0;
            int totalCredits = 0;
            for (Grade grade : grades) {
                totalPoints += grade.getGradePoints() * grade.getSubject().getCredits();
                totalCredits += grade.getSubject().getCredits();
            }
            double gpa = totalCredits > 0 ? totalPoints / totalCredits : 0.0;
            System.out.println("\nGPA: " + String.format("%.2f", gpa));
        }
    }

    /**
     * Handle generate transcript
     */
    private static void handleGenerateTranscript() {
        String transcript = reportController.generateStudentTranscript(currentUser.getUserId());
        System.out.println("\n" + transcript);
    }

    /**
     * Handle class statistics
     */
    private static void handleClassStatistics() {
        System.out.print("Subject ID: ");
        String subjectId = scanner.nextLine();
        String report = reportController.generateClassStatistics(subjectId);
        System.out.println("\n" + report);
    }

    /**
     * Handle department report
     */
    private static void handleDepartmentReport() {
        System.out.print("Department: ");
        String department = scanner.nextLine();
        String report = reportController.generateDepartmentReport(department);
        System.out.println("\n" + report);
    }

    /**
     * Handle top students
     */
    private static void handleTopStudents() {
        System.out.print("Number of top students to display: ");
        int topN = scanner.nextInt();
        scanner.nextLine();

        List<String> topStudents = reportController.getTopPerformingStudents(topN);
        System.out.println("\n--- TOP PERFORMING STUDENTS ---");
        if (topStudents.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (int i = 0; i < topStudents.size(); i++) {
                System.out.println((i + 1) + ". " + topStudents.get(i));
            }
        }
    }
}
