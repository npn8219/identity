package com.npn.users.model.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

public record UpdateUserProfileDto(
        String firstName,

        String lastName,

        LocalDate dob,

        String email
) implements Serializable {
}
