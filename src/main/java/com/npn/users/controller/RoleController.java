package com.npn.users.controller;

import com.npn.users.model.dto.AssignPermissionsDto;
import com.npn.users.model.dto.CreateRoleDto;
import com.npn.users.model.dto.UpdatePermissionsRoleDto;
import com.npn.users.model.dto.UpdateRoleDto;
import com.npn.users.model.vo.RoleEntityVo;
import com.npn.users.model.vo.RolePermissionsVo;
import com.npn.users.service.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping(value = "/roles")
@RestController
@Slf4j
public class RoleController {

    private final RoleService roleService;

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody CreateRoleDto dto) {
        log.warn("create role: {}", dto.name());
        return ResponseEntity.created(URI.create("/roles/" + roleService.create(dto).getId())).build();
    }

    @PostMapping("/{id}/add-permissions")
    public ResponseEntity<Void> addPermissions(
            @PathVariable Long id,
            @Valid @RequestBody AssignPermissionsDto dto
    ) {
        roleService.addPermissions(id, dto);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/remove-permissions")
    public ResponseEntity<Void> removePermissions(
            @PathVariable Long id,
            @Valid @RequestBody AssignPermissionsDto dto
    ) {
        roleService.removePermissions(id, dto);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/permissions")
    public ResponseEntity<Void> updatePermissions(
            @PathVariable Long id,
            @Valid @RequestBody UpdatePermissionsRoleDto dto
    ) {
        roleService.updatePermissions(id, dto);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateRoleDto dto
    ) {
        roleService.update(id, dto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/permissions")
    public ResponseEntity<RolePermissionsVo> getPermissions(@PathVariable Long id) {
        return ResponseEntity.ok(roleService.getPermissions(id));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RoleEntityVo> query(@PathVariable Long id) {
        return ResponseEntity.ok(roleService.query(id));
    }

    @GetMapping
    public ResponseEntity<List<RoleEntityVo>> queryAll() {
        return ResponseEntity.ok(roleService.queryAll());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        roleService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
