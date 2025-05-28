package com.npn.users.model.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

public record UpdateUserUsernameDto(
        @Length(min = 3, max = 16)
        @NotBlank
        String username
) implements Serializable {

}