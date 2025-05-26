package com.npn.users.service;

import com.npn.users.model.dto.CreatePermissionDto;
import com.npn.users.model.dto.UpdatePermissionDto;
import com.npn.users.model.dto.UpdateRolesPermissionDto;
import com.npn.users.model.entity.PermissionEntity;
import com.npn.users.model.vo.PermissionEntityVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PermissionService {

    PermissionEntity create(CreatePermissionDto dto);

    void updateRoles(Long id, UpdateRolesPermissionDto dto);

    void update(Long id, UpdatePermissionDto dto);

    PermissionEntityVo query(Long id);

    List<PermissionEntityVo> queryAll();

    void delete(Long id);
}
