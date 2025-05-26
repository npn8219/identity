package com.npn.users.model.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

public record CreateRoleDto(
    @Length(min = 1, max = 20)
    @NotBlank
    String name,
    String description
) implements Serializable {

}
