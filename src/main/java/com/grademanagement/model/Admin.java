package com.grademanagement.model;

/**
 * Admin class extending User functionality
 */
public class Admin extends User {
    private String adminId;
    private String fullName;

    public Admin() {
        super();
        setRole("ADMIN");
    }

    public Admin(String userId, String username, String password, String email,
            String adminId, String fullName) {
        super(userId, username, password, email, "ADMIN");
        this.adminId = adminId;
        this.fullName = fullName;
    }

    // Getters and Setters
    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminId='" + adminId + '\'' +
                ", fullName='" + fullName + '\'' +
                ", username='" + getUsername() + '\'' +
                '}';
    }
}
