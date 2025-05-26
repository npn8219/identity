package com.npn.users.model.dto;

import java.io.Serializable;
import java.util.Set;

public record UpdateRolesPermissionDto(
        Set<Long> roles
) implements Serializable {

}
