package com.grademanagement.exception;

/**
 * Custom exception for grade not found scenarios
 */
public class GradeNotFoundException extends Exception {
    public GradeNotFoundException(String message) {
        super(message);
    }

    public GradeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
