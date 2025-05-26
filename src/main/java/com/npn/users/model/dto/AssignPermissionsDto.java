package com.npn.users.model.dto;

import java.io.Serializable;
import java.util.List;

public record AssignPermissionsDto(
        List<Long> ids
) implements Serializable {

}
