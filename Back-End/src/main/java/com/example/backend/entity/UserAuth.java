package com.example.backend.entity;

public class UserAuth {
    private String UserName;
    private int Role;

    public UserAuth(String name, int role) {
        this.UserName = name;
        this.Role = role;
    }

    public String getUserName() {
        return UserName;
    }

    public int getRole() {
        return Role;
    }

    public void setRole(int role) {
        Role = role;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }
}
