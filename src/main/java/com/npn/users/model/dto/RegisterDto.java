package com.npn.users.model.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

public record RegisterDto(

        @Length(min = 3, max = 16)
        @NotBlank
        String username,

        @Length(min = 6, max = 18)
        @NotBlank
        String password
) implements Serializable {

}
