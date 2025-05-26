package com.npn.users.controller;

import com.npn.users.model.dto.CreatePermissionDto;
import com.npn.users.model.dto.UpdatePermissionDto;
import com.npn.users.model.dto.UpdateRolesPermissionDto;
import com.npn.users.model.vo.PermissionEntityVo;
import com.npn.users.service.PermissionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping(value = "/permissions")
@RestController
@Slf4j
public class PermissionController {

    private final PermissionService permissionService;

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody CreatePermissionDto dto) {
        return ResponseEntity
                .created(URI.create("/permissions/" + permissionService.create(dto).getId()))
                .build();
    }

    @PutMapping("/{id}/roles")
    public ResponseEntity<Void> updateRoles(
            @PathVariable Long id,
            @Valid @RequestBody UpdateRolesPermissionDto dto
    ) {
        permissionService.updateRoles(id, dto);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdatePermissionDto dto
    ) {
        permissionService.update(id, dto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PermissionEntityVo> query(@PathVariable Long id) {
        return ResponseEntity.ok(permissionService.query(id));
    }

    @GetMapping
    public ResponseEntity<List<PermissionEntityVo>> queryAll() {
        return ResponseEntity.ok(permissionService.queryAll());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        permissionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
