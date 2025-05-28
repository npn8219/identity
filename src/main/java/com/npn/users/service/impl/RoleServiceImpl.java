package com.npn.users.service.impl;

import com.npn.users.exception.CustomException;
import com.npn.users.mapper.PermissionMapper;
import com.npn.users.mapper.RoleMapper;
import com.npn.users.model.dto.AssignPermissionsDto;
import com.npn.users.model.dto.CreateRoleDto;
import com.npn.users.model.dto.UpdatePermissionsRoleDto;
import com.npn.users.model.dto.UpdateRoleDto;
import com.npn.users.model.entity.PermissionEntity;
import com.npn.users.model.entity.RoleEntity;
import com.npn.users.model.vo.RoleEntityVo;
import com.npn.users.model.vo.RolePermissionsVo;
import com.npn.users.repository.PermissionRepository;
import com.npn.users.repository.RoleRepository;
import com.npn.users.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleMapper roleMapper;

    private final RoleRepository roleRepository;

    private final PermissionRepository permissionRepository;

    private final PermissionMapper permissionMapper;

    @Transactional
    @Override
    public RoleEntity create(CreateRoleDto dto) {
        checkExistedRoleName(dto.name());
        RoleEntity roleEntity = new RoleEntity();
        roleMapper.dtoToEntity(dto, roleEntity);
        return roleRepository.save(roleEntity);
    }

    @Transactional
    @Override
    public void addPermissions(Long id, AssignPermissionsDto dto) {
        if (CollectionUtils.isEmpty(dto.ids()))
            return;

        RoleEntity roleEntity = findRole(id);
        var permissionsToAdd = dto.ids().stream()
                .map(this::findPermission)
                .toList();
        roleEntity.getPermissions().addAll(permissionsToAdd);
    }

    @Transactional
    @Override
    public void removePermissions(Long id, AssignPermissionsDto dto) {
        RoleEntity roleEntity = findRole(id);

        if (Objects.nonNull(dto.ids())) {
            dto.ids().stream()
                    .map(this::findPermission)
                    .forEach(roleEntity.getPermissions()::remove);
        }
    }

    @Transactional
    @Override
    public void updatePermissions(Long id, UpdatePermissionsRoleDto dto) {
        RoleEntity roleEntity = findRole(id);

        if (Objects.nonNull(dto.permissions())) {
            roleEntity.setPermissions(
                    dto.permissions().stream()
                            .map(this::findPermission)
                            .collect(Collectors.toSet())
            );
        }
    }

    @Transactional
    @Override
    public void update(Long id, UpdateRoleDto dto) {
        RoleEntity roleEntity = findRole(id);
        checkExistedRoleName(dto.name(), id);
        roleMapper.dtoToEntity(dto, roleEntity);
    }

    @Override
    public RolePermissionsVo getPermissions(Long id) {
        RoleEntity roleEntity = findRole(id);

        RolePermissionsVo vo = new RolePermissionsVo();
        vo.setRole(roleMapper.entityToVo(roleEntity));
        if (!roleEntity.getPermissions().isEmpty()) {
            vo.setPermissions(roleEntity.getPermissions().stream().map(permissionMapper::entityToVo).toList());
        }
        return vo;
    }

    @Override
    public RoleEntityVo query(Long id) {
        RoleEntity roleEntity = findRole(id);

        RoleEntityVo vo = roleMapper.entityToVo(roleEntity);
        setPermissions(vo, roleEntity);
        return vo;
    }

    @Override
    public List<RoleEntityVo> queryAll() {
        return roleRepository.findAll().stream()
                .map(role -> {
                    RoleEntityVo vo = roleMapper.entityToVo(role);
                    setPermissions(vo, role);
                    return vo;
                })
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void delete(Long id) {
        RoleEntity roleEntity = findRole(id);
        roleRepository.delete(roleEntity);
    }

    private void setPermissions(RoleEntityVo vo, RoleEntity roleEntity) {
        vo.setPermissions(roleEntity.getPermissions().stream()
                .map(permissionMapper::entityToVo)
                .collect(Collectors.toSet())
        );
    }

    private PermissionEntity findPermission(Long id) {
        return permissionRepository.findById(id)
                .orElseThrow(CustomException::permissionNotFound);
    }

    private RoleEntity findRole(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(CustomException::roleNotFound);
    }

    private void checkExistedRoleName(String name) {
        if (roleRepository.findByName(name).isPresent())
            throw CustomException.roleAlreadyExists();
    }

    private void checkExistedRoleName(String name, Long id) {
        if (roleRepository.findByNameAndIdNot(name, id).isPresent())
            throw CustomException.roleAlreadyExists();
    }

}
