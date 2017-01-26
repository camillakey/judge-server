package com.judge_server.core.entity.user;

public enum Role {
    Admin("ADMIN"),
    User("USER");

    private String role;

    Role(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return role;
    }
}
