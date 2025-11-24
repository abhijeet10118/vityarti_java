package com.grademanagement.service;

import com.grademanagement.exception.InvalidInputException;
import com.grademanagement.exception.UserNotFoundException;
import com.grademanagement.model.Student;
import com.grademanagement.model.Teacher;
import com.grademanagement.model.User;
import com.grademanagement.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for UserService
 */
public class UserServiceTest {
    private UserService userService;
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository = new UserRepository();
        userService = new UserService(userRepository);
    }

    @Test
    public void testRegisterStudent_Success() throws InvalidInputException {
        Student student = userService.registerStudent(
                "user1", "student1", "password123", "student1@test.com",
                "STU001", "John Doe", "Computer Science", 2);

        assertNotNull(student);
        assertEquals("STU001", student.getStudentId());
        assertEquals("John Doe", student.getFullName());
        assertEquals("STUDENT", student.getRole());
    }

    @Test
    public void testRegisterStudent_InvalidEmail() {
        assertThrows(InvalidInputException.class, () -> {
            userService.registerStudent(
                    "user1", "student1", "password123", "invalid-email",
                    "STU001", "John Doe", "Computer Science", 2);
        });
    }

    @Test
    public void testRegisterStudent_InvalidYear() {
        assertThrows(InvalidInputException.class, () -> {
            userService.registerStudent(
                    "user1", "student1", "password123", "student1@test.com",
                    "STU001", "John Doe", "Computer Science", 5);
        });
    }

    @Test
    public void testRegisterTeacher_Success() throws InvalidInputException {
        Teacher teacher = userService.registerTeacher(
                "user2", "teacher1", "password123", "teacher1@test.com",
                "TCH001", "Jane Smith", "Mathematics");

        assertNotNull(teacher);
        assertEquals("TCH001", teacher.getTeacherId());
        assertEquals("Jane Smith", teacher.getFullName());
        assertEquals("TEACHER", teacher.getRole());
    }

    @Test
    public void testLogin_Success() throws InvalidInputException, UserNotFoundException {
        userService.registerStudent(
                "user3", "testuser", "testpass", "test@test.com",
                "STU002", "Test User", "Physics", 1);

        User user = userService.login("testuser", "testpass");
        assertNotNull(user);
        assertEquals("testuser", user.getUsername());
    }

    @Test
    public void testLogin_InvalidCredentials() throws InvalidInputException {
        userService.registerStudent(
                "user4", "testuser2", "testpass", "test2@test.com",
                "STU003", "Test User 2", "Chemistry", 1);

        assertThrows(UserNotFoundException.class, () -> {
            userService.login("testuser2", "wrongpass");
        });
    }
}
