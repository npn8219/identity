package com.npn.users.service.impl;

import com.npn.users.mapper.PermissionMapper;
import com.npn.users.mapper.RoleMapper;
import com.npn.users.model.dto.AssignPermissionsDto;
import com.npn.users.model.dto.CreateRoleDto;
import com.npn.users.model.dto.UpdatePermissionsRoleDto;
import com.npn.users.model.dto.UpdateRoleDto;
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
        log.info("dto: {}", dto);
        RoleEntity roleEntity = new RoleEntity();
        roleMapper.dtoToEntity(dto, roleEntity);
        var roleSaved = roleRepository.save(roleEntity);
        log.info("roleSaved: {}", roleSaved.getId());
        roleRepository.findAll().forEach(role -> log.info("role ID =  {}, role name = {}", role.getId(), role.getName()));
        log.info("roleSaved: {}", roleRepository.findById(roleSaved.getId()));
        return roleSaved;
    }

    @Transactional
    @Override
    public void addPermissions(Long id, AssignPermissionsDto dto) {
        if (CollectionUtils.isEmpty(dto.ids())) {
            return;
        }

        RoleEntity roleEntity = roleRepository.findById(id)
                .orElseThrow();
        roleEntity.getPermissions().addAll(
                dto.ids()
                        .stream()
                        .map(pid -> permissionRepository.findById(pid)
                                .orElseThrow()
                        )
                        .toList()
        );
    }

    @Transactional
    @Override
    public void removePermissions(Long id, AssignPermissionsDto dto) {
        RoleEntity roleEntity = roleRepository.findById(id)
                .orElseThrow();

        if (Objects.nonNull(dto.ids())) {
            dto.ids()
                    .stream()
                    .map(pid -> permissionRepository.findById(pid)
                            .orElseThrow()
                    )
                    .forEach(roleEntity.getPermissions()::remove);
        }
    }

    @Transactional
    @Override
    public void updatePermissions(Long id, UpdatePermissionsRoleDto dto) {
        RoleEntity roleEntity = roleRepository.findById(id)
                .orElseThrow();

        if (Objects.nonNull(dto.permissions())) {
            roleEntity.setPermissions(
                    dto.permissions()
                            .stream()
                            .map(pid -> permissionRepository.findById(pid)
                                    .orElseThrow()
                            )
                            .collect(Collectors.toSet())
            );
        }
    }

    @Transactional
    @Override
    public void update(Long id, UpdateRoleDto dto) {
        RoleEntity roleEntity = roleRepository.findById(id)
                .orElseThrow();
        roleMapper.dtoToEntity(dto, roleEntity);
    }

    @Override
    public RolePermissionsVo getPermissions(Long id) {
        RoleEntity roleEntity = roleRepository.findById(id)
                .orElseThrow();

        RolePermissionsVo vo = new RolePermissionsVo();
        vo.setRole(roleMapper.entityToVo(roleEntity));
        vo.setPermissions(
                roleEntity.getPermissions()
                        .stream()
                        .map(permissionMapper::entityToVo)
                        .toList()
        );
        return vo;
    }

    @Override
    public RoleEntityVo query(Long id) {
        RoleEntity roleEntity = roleRepository.findById(id)
                .orElseThrow();

        RoleEntityVo vo = roleMapper.entityToVo(roleEntity);
        setPermissions(vo, roleEntity);
        return vo;
    }

    @Override
    public List<RoleEntityVo> queryAll() {
        return roleRepository.findAll().stream()
                .map(roleMapper::entityToVo)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void delete(Long id) {
        RoleEntity roleEntity = roleRepository.findById(id)
                .orElseThrow();
        roleRepository.delete(roleEntity);
    }

    private void setPermissions(RoleEntityVo vo, RoleEntity roleEntity) {
        vo.setPermissions(
                roleEntity.getPermissions()
                        .stream()
                        .map(permissionMapper::entityToVo)
                        .collect(Collectors.toSet())
        );
    }
}
