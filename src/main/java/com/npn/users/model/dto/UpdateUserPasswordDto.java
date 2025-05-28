package com.npn.users.model.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

public record UpdateUserPasswordDto(
        @Length(min = 6, max = 18)
        @NotBlank
        String oldPassword,

        @Length(min = 6, max = 18)
        @NotBlank
        String newPassword
) implements Serializable {

}
