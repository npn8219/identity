package com.npn.users.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.OffsetDateTime;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserEntityVo extends AbstractEntityVo {
    private String username;
    private String email;
    private Boolean enabled;
    private Set<RoleEntityVo> roles;
    private OffsetDateTime lastLoginTime;
}
