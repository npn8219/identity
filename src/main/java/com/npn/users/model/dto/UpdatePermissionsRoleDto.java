package com.npn.users.model.dto;

import java.io.Serializable;
import java.util.Set;

public record UpdatePermissionsRoleDto(
        Set<Long> permissions
) implements Serializable {

}
