package com.npn.users.enums;

import lombok.Getter;

@Getter
public enum TaskStatus {
    PENDING("PENDING"),
    VIEWED("VIEWED"),
    COMPLETED("COMPLETED"),
    CANCELLED("CANCELLED")
    ;
    private final String value;

    TaskStatus(String value) {
        this.value = value;
    }
}
