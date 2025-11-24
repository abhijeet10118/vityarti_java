package com.grademanagement.service;

import com.grademanagement.exception.InvalidInputException;
import com.grademanagement.exception.UserNotFoundException;
import com.grademanagement.model.*;
import com.grademanagement.repository.UserRepository;
import com.grademanagement.util.Logger;
import com.grademanagement.util.Validator;

import java.util.List;

/**
 * Service class for user management operations
 */
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Register a new student
     */
    public Student registerStudent(String userId, String username, String password, String email,
            String studentId, String fullName, String department, int year)
            throws InvalidInputException {

        // Validate inputs
        Validator.validateNonEmpty(userId, "User ID");
        Validator.validateNonEmpty(username, "Username");
        Validator.validatePassword(password);
        Validator.validateEmail(email);
        Validator.validateNonEmpty(studentId, "Student ID");
        Validator.validateNonEmpty(fullName, "Full Name");
        Validator.validateNonEmpty(department, "Department");
        Validator.validateYear(year);

        // Check if user already exists
        if (userRepository.getUserById(userId) != null) {
            throw new InvalidInputException("User ID already exists");
        }

        if (userRepository.getUserByUsername(username) != null) {
            throw new InvalidInputException("Username already exists");
        }

        Student student = new Student(userId, username, password, email,
                studentId, fullName, department, year);
        userRepository.addUser(student);

        Logger.info("Student registered: " + studentId);
        return student;
    }

    /**
     * Register a new teacher
     */
    public Teacher registerTeacher(String userId, String username, String password, String email,
            String teacherId, String fullName, String department)
            throws InvalidInputException {

        // Validate inputs
        Validator.validateNonEmpty(userId, "User ID");
        Validator.validateNonEmpty(username, "Username");
        Validator.validatePassword(password);
        Validator.validateEmail(email);
        Validator.validateNonEmpty(teacherId, "Teacher ID");
        Validator.validateNonEmpty(fullName, "Full Name");
        Validator.validateNonEmpty(department, "Department");

        // Check if user already exists
        if (userRepository.getUserById(userId) != null) {
            throw new InvalidInputException("User ID already exists");
        }

        if (userRepository.getUserByUsername(username) != null) {
            throw new InvalidInputException("Username already exists");
        }

        Teacher teacher = new Teacher(userId, username, password, email,
                teacherId, fullName, department);
        userRepository.addUser(teacher);

        Logger.info("Teacher registered: " + teacherId);
        return teacher;
    }
    
    /**
     * Register a new admin
     */
    public Admin registerAdmin(String userId, String username, String password, String email,
            String adminId, String fullName)
            throws InvalidInputException {

        // Validate inputs
        Validator.validateNonEmpty(userId, "User ID");
        Validator.validateNonEmpty(username, "Username");
        Validator.validatePassword(password);
        Validator.validateEmail(email);
        Validator.validateNonEmpty(adminId, "Admin ID");
        Validator.validateNonEmpty(fullName, "Full Name");

        // Check if user already exists
        if (userRepository.getUserById(userId) != null) {
            throw new InvalidInputException("User ID already exists");
        }

        if (userRepository.getUserByUsername(username) != null) {
            throw new InvalidInputException("Username already exists");
        }

        Admin admin = new Admin(userId, username, password, email,
                adminId, fullName);
        userRepository.addUser(admin);

        Logger.info("Admin registered: " + adminId);
        return admin;
    }

    /**
     * Login user
     */
    public User login(String username, String password) throws UserNotFoundException {
        User user = userRepository.authenticate(username, password);
        if (user == null) {
            throw new UserNotFoundException("Invalid username or password");
        }
        return user;
    }

    /**
     * Get user by ID
     */
    public User getUserById(String userId) throws UserNotFoundException {
        User user = userRepository.getUserById(userId);
        if (user == null) {
            throw new UserNotFoundException("User not found: " + userId);
        }
        return user;
    }

    /**
     * Get all users
     */
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    /**
     * Update user
     */
    public void updateUser(User user) throws InvalidInputException, UserNotFoundException {
        if (userRepository.getUserById(user.getUserId()) == null) {
            throw new UserNotFoundException("User not found: " + user.getUserId());
        }

        Validator.validateEmail(user.getEmail());
        userRepository.updateUser(user);
    }

    /**
     * Delete user
     */
    public void deleteUser(String userId) throws UserNotFoundException {
        if (userRepository.getUserById(userId) == null) {
            throw new UserNotFoundException("User not found: " + userId);
        }
        userRepository.deleteUser(userId);
    }
}
