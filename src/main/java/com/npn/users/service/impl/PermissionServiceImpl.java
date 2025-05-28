package com.npn.users.service.impl;

import com.npn.users.exception.CustomException;
import com.npn.users.mapper.PermissionMapper;
import com.npn.users.mapper.RoleMapper;
import com.npn.users.model.dto.CreatePermissionDto;
import com.npn.users.model.dto.UpdatePermissionDto;
import com.npn.users.model.dto.UpdateRolesPermissionDto;
import com.npn.users.model.entity.PermissionEntity;
import com.npn.users.model.entity.RoleEntity;
import com.npn.users.model.vo.PermissionEntityVo;
import com.npn.users.repository.PermissionRepository;
import com.npn.users.repository.RoleRepository;
import com.npn.users.service.PermissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
@Slf4j
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;

    private final RoleRepository roleRepository;

    private final PermissionMapper permissionMapper;

    private final RoleMapper roleMapper;

    @Transactional
    @Override
    public PermissionEntity create(CreatePermissionDto dto) {
        PermissionEntity permissionEntity = new PermissionEntity();
        permissionMapper.dtoToEntity(dto, permissionEntity);
        return permissionRepository.save(permissionEntity);
    }

    @Transactional
    @Override
    public void updateRoles(Long id, UpdateRolesPermissionDto dto) {
        PermissionEntity permissionEntity = findPermission(id);

        if (Objects.nonNull(dto.roles())) {
            permissionEntity.setRoles(dto.roles().stream()
                    .map(pid -> roleRepository.findById(pid).orElseThrow(CustomException::roleNotFound))
                    .collect(Collectors.toSet()));
            permissionRepository.save(permissionEntity);
        }
    }

    @Transactional
    @Override
    public void update(Long id, UpdatePermissionDto dto) {
        PermissionEntity permissionEntity = findPermission(id);
        permissionMapper.dtoToEntity(dto, permissionEntity);
        permissionRepository.save(permissionEntity);
    }

    @Override
    public PermissionEntityVo query(Long id) {
        PermissionEntity permissionEntity = findPermission(id);
        PermissionEntityVo vo = permissionMapper.entityToVo(permissionEntity);
        setRoles(vo, permissionEntity);
        return vo;
    }

    @Override
    public List<PermissionEntityVo> queryAll() {
        List<PermissionEntityVo> listPermissions = new ArrayList<>();
        permissionRepository.findAll().forEach(permissionEntity -> {
            PermissionEntityVo vo = permissionMapper.entityToVo(permissionEntity);
            setRoles(vo, permissionEntity);
            listPermissions.add(permissionMapper.entityToVo(permissionEntity));
        });
        return listPermissions;
    }

    @Transactional
    @Override
    public void delete(Long id) {
        PermissionEntity permissionEntity = findPermission(id);
        permissionEntity.getRoles().forEach(roleEntity -> removePermissions(permissionEntity, roleEntity));
        permissionRepository.delete(permissionEntity);
    }

    private void setRoles(PermissionEntityVo vo, PermissionEntity permission) {
        vo.setRoles(permission.getRoles().stream()
                .map(roleMapper::entityToVo)
                .collect(Collectors.toSet())
        );
    }

    private void removePermissions(PermissionEntity permission, RoleEntity role) {
        if (role.getName().equals("ADMIN")) return;
        role.getPermissions().remove(permission);
        roleRepository.save(role);
        permissionRepository.delete(permission);
    }

    private PermissionEntity findPermission(Long id) {
        return permissionRepository.findById(id)
                .orElseThrow(CustomException::permissionNotFound);
    }
}
