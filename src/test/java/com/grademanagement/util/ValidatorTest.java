package com.grademanagement.util;

import com.grademanagement.exception.InvalidInputException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Validator utility class
 */
public class ValidatorTest {

    @Test
    public void testValidateEmail_Valid() {
        assertDoesNotThrow(() -> {
            Validator.validateEmail("test@example.com");
            Validator.validateEmail("user.name@domain.co.uk");
        });
    }

    @Test
    public void testValidateEmail_Invalid() {
        assertThrows(InvalidInputException.class, () -> {
            Validator.validateEmail("invalid-email");
        });

        assertThrows(InvalidInputException.class, () -> {
            Validator.validateEmail("");
        });
    }

    @Test
    public void testValidatePassword_Valid() {
        assertDoesNotThrow(() -> {
            Validator.validatePassword("password123");
            Validator.validatePassword("123456");
        });
    }

    @Test
    public void testValidatePassword_Invalid() {
        assertThrows(InvalidInputException.class, () -> {
            Validator.validatePassword("short");
        });

        assertThrows(InvalidInputException.class, () -> {
            Validator.validatePassword(null);
        });
    }

    @Test
    public void testValidateMarks_Valid() {
        assertDoesNotThrow(() -> {
            Validator.validateMarks(0.0);
            Validator.validateMarks(50.0);
            Validator.validateMarks(100.0);
        });
    }

    @Test
    public void testValidateMarks_Invalid() {
        assertThrows(InvalidInputException.class, () -> {
            Validator.validateMarks(-1.0);
        });

        assertThrows(InvalidInputException.class, () -> {
            Validator.validateMarks(101.0);
        });
    }

    @Test
    public void testValidateYear_Valid() {
        assertDoesNotThrow(() -> {
            Validator.validateYear(1);
            Validator.validateYear(2);
            Validator.validateYear(3);
            Validator.validateYear(4);
        });
    }

    @Test
    public void testValidateYear_Invalid() {
        assertThrows(InvalidInputException.class, () -> {
            Validator.validateYear(0);
        });

        assertThrows(InvalidInputException.class, () -> {
            Validator.validateYear(5);
        });
    }
}
