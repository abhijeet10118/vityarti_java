package com.grademanagement.controller;

import com.grademanagement.exception.InvalidInputException;
import com.grademanagement.exception.UserNotFoundException;
import com.grademanagement.model.User;
import com.grademanagement.service.UserService;

/**
 * Controller class for user management operations
 */
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Handle user login
     */
    public User login(String username, String password) throws UserNotFoundException {
        return userService.login(username, password);
    }

    /**
     * Handle user registration
     */
    public User registerUser(String role, String userId, String username, String password,
            String email, String id, String fullName, String department, Integer year)
            throws InvalidInputException {

        if ("STUDENT".equals(role)) {
            return userService.registerStudent(userId, username, password, email,
                    id, fullName, department, year);
        } else if ("TEACHER".equals(role)) {
            return userService.registerTeacher(userId, username, password, email,
                    id, fullName, department);
        } else if ("ADMIN".equals(role)) {
            return userService.registerAdmin(userId, username, password, email,
                    id, fullName);
        } else {
            throw new InvalidInputException("Invalid role: " + role);
        }
    }

    /**
     * Get user by ID
     */
    public User getUser(String userId) throws UserNotFoundException {
        return userService.getUserById(userId);
    }
}
