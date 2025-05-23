package com.npn.users.model.enums;

import lombok.Getter;

@Getter
public enum UserStatus {
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE"),
    PENDING("PENDING"),
    BLOCKED("BLOCKED")
    ;
    private final String value;

    UserStatus(String value) {
        this.value = value;
    }
}
