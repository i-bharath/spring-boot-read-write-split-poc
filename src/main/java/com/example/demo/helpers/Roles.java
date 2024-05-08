package com.example.demo.helpers;

public enum Roles {
    ADMIN(1),
    USER(2);
    private final int value;

    Roles(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Roles fromValue(int value) {
        for (Roles role : Roles.values()) {
            if (role.value == value) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid value for Role: " + value);
    }
}
