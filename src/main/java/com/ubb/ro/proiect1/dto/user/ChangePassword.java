package com.ubb.ro.proiect1.dto.user;

public class ChangePassword {

    private int userId;

    private String password;

    public ChangePassword() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
