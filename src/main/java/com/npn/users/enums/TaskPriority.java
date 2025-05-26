package com.npn.users.enums;

import lombok.Getter;

@Getter
public enum TaskPriority {
    NONE("NONE"),
    LOW("LOW"),
    MEDIUM("MEDIUM"),
    HIGH("HIGH")
    ;
    private final String value;

    TaskPriority(String value) {
        this.value = value;
    }
}
