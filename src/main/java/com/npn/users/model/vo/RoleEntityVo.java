package com.npn.users.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
public class RoleEntityVo extends AbstractEntityVo {

    private String name;

    String description;

    private Set<PermissionEntityVo> permissions;

}