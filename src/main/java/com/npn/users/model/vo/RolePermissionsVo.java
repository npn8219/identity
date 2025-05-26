package com.npn.users.model.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class RolePermissionsVo implements Serializable {

    private RoleEntityVo role;

    private List<PermissionEntityVo> permissions;

}