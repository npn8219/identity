package com.npn.users.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
public class PermissionEntityVo extends AbstractEntityVo {

    private String name;

    private String description;

    private RoleEntityVo role;

    private Set<RoleEntityVo> roles;

}