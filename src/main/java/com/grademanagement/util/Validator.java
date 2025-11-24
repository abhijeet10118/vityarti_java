package com.grademanagement.util;

import com.grademanagement.exception.InvalidInputException;

/**
 * Utility class for input validation
 */
public class Validator {

    /**
     * Validate email format
     */
    public static void validateEmail(String email) throws InvalidInputException {
        if (email == null || email.trim().isEmpty()) {
            throw new InvalidInputException("Email cannot be empty");
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new InvalidInputException("Invalid email format");
        }
    }

    /**
     * Validate password strength
     */
    public static void validatePassword(String password) throws InvalidInputException {
        if (password == null || password.length() < 6) {
            throw new InvalidInputException("Password must be at least 6 characters long");
        }
    }

    /**
     * Validate marks range (0-100)
     */
    public static void validateMarks(double marks) throws InvalidInputException {
        if (marks < 0 || marks > 100) {
            throw new InvalidInputException("Marks must be between 0 and 100");
        }
    }

    /**
     * Validate non-empty string
     */
    public static void validateNonEmpty(String value, String fieldName) throws InvalidInputException {
        if (value == null || value.trim().isEmpty()) {
            throw new InvalidInputException(fieldName + " cannot be empty");
        }
    }

    /**
     * Validate positive integer
     */
    public static void validatePositive(int value, String fieldName) throws InvalidInputException {
        if (value <= 0) {
            throw new InvalidInputException(fieldName + " must be a positive number");
        }
    }

    /**
     * Validate year range (1-4 for typical undergraduate)
     */
    public static void validateYear(int year) throws InvalidInputException {
        if (year < 1 || year > 4) {
            throw new InvalidInputException("Year must be between 1 and 4");
        }
    }
}
