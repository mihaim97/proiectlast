package com.ubb.ro.proiect1.dto.user;

public class DeleteRole {

    private String userId;

    private String roleId;

    public DeleteRole() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
