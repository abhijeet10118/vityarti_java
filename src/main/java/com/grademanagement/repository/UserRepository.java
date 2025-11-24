package com.grademanagement.repository;

import com.grademanagement.model.*;
import com.grademanagement.util.FileHandler;
import com.grademanagement.util.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Repository class for user data persistence
 */
public class UserRepository {
    private static final String USERS_FILE = "data/users.txt";
    private Map<String, User> users;

    public UserRepository() {
        this.users = new HashMap<>();
        loadUsers();
    }

    /**
     * Load users from file
     */
    private void loadUsers() {
        try {
            if (FileHandler.fileExists(USERS_FILE)) {
                List<String> lines = FileHandler.readFile(USERS_FILE);
                for (String line : lines) {
                    if (!line.trim().isEmpty()) {
                        User user = parseUser(line);
                        if (user != null) {
                            users.put(user.getUserId(), user);
                        }
                    }
                }
            }
        } catch (IOException e) {
            Logger.error("Error loading users from file", e);
        }
    }

    /**
     * Save users to file
     */
    private void saveUsers() {
        try {
            List<String> lines = new ArrayList<>();
            for (User user : users.values()) {
                lines.add(serializeUser(user));
            }
            FileHandler.writeFile(USERS_FILE, lines);
        } catch (IOException e) {
            Logger.error("Error saving users to file", e);
        }
    }

    /**
     * Parse user from string
     */
    private User parseUser(String line) {
        try {
            String[] parts = line.split("\\|");
            if (parts.length < 6)
                return null;

            String role = parts[5];
            User user;

            switch (role) {
                case "STUDENT":
                    user = new Student(parts[0], parts[1], parts[2], parts[3],
                            parts[6], parts[7], parts[8], Integer.parseInt(parts[9]));
                    break;
                case "TEACHER":
                    user = new Teacher(parts[0], parts[1], parts[2], parts[3],
                            parts[6], parts[7], parts[8]);
                    break;
                case "ADMIN":
                    user = new Admin(parts[0], parts[1], parts[2], parts[3],
                            parts[6], parts[7]);
                    break;
                default:
                    user = new User(parts[0], parts[1], parts[2], parts[3], role);
            }

            return user;
        } catch (Exception e) {
            Logger.error("Error parsing user: " + line, e);
            return null;
        }
    }

    /**
     * Serialize user to string
     */
    private String serializeUser(User user) {
        StringBuilder sb = new StringBuilder();
        sb.append(user.getUserId()).append("|")
                .append(user.getUsername()).append("|")
                .append(user.getPassword()).append("|")
                .append(user.getEmail()).append("|")
                .append(user.getRole()).append("|");

        if (user instanceof Student) {
            Student s = (Student) user;
            sb.append(s.getStudentId()).append("|")
                    .append(s.getFullName()).append("|")
                    .append(s.getDepartment()).append("|")
                    .append(s.getYear());
        } else if (user instanceof Teacher) {
            Teacher t = (Teacher) user;
            sb.append(t.getTeacherId()).append("|")
                    .append(t.getFullName()).append("|")
                    .append(t.getDepartment());
        } else if (user instanceof Admin) {
            Admin a = (Admin) user;
            sb.append(a.getAdminId()).append("|")
                    .append(a.getFullName());
        }

        return sb.toString();
    }

    /**
     * Add a new user
     */
    public void addUser(User user) {
        users.put(user.getUserId(), user);
        saveUsers();
        Logger.info("User added: " + user.getUserId());
    }

    /**
     * Get user by ID
     */
    public User getUserById(String userId) {
        return users.get(userId);
    }

    /**
     * Get user by username
     */
    public User getUserByUsername(String username) {
        for (User user : users.values()) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Get all users
     */
    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    /**
     * Update user
     */
    public void updateUser(User user) {
        if (users.containsKey(user.getUserId())) {
            users.put(user.getUserId(), user);
            saveUsers();
            Logger.info("User updated: " + user.getUserId());
        }
    }

    /**
     * Delete user
     */
    public void deleteUser(String userId) {
        if (users.remove(userId) != null) {
            saveUsers();
            Logger.info("User deleted: " + userId);
        }
    }

    /**
     * Authenticate user
     */
    public User authenticate(String username, String password) {
        User user = getUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            Logger.info("User authenticated: " + username);
            return user;
        }
        Logger.warning("Authentication failed for: " + username);
        return null;
    }
}
