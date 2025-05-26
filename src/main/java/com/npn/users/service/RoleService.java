package com.npn.users.service;

import com.npn.users.model.dto.*;
import com.npn.users.model.entity.RoleEntity;
import com.npn.users.model.vo.RoleEntityVo;
import com.npn.users.model.vo.RolePermissionsVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {

    RoleEntity create(CreateRoleDto dto);

    void addPermissions(Long id, AssignPermissionsDto dto);

    void removePermissions(Long id, AssignPermissionsDto dto);

    void updatePermissions(Long id, UpdatePermissionsRoleDto dto);

    void update(Long id, UpdateRoleDto dto);

    RolePermissionsVo getPermissions(Long id);

    RoleEntityVo query(Long id);

    List<RoleEntityVo> queryAll();

    void delete(Long id);
}
