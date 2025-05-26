package com.npn.users.model.dto;

import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

public record UpdateRoleDto(
        @Length(min = 1, max = 10)
        String name,
        String description
) implements Serializable {

}