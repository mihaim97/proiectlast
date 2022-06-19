package com.ubb.ro.proiect1.dto.user;

public class NewRole {

    private int userId;

    private String role;

    public NewRole() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
