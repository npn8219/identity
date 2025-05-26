package com.npn.users.model.dto;

import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

public record UpdatePermissionDto(
        @Length(min = 1, max = 120)
        String name,
        String description
) implements Serializable {

}