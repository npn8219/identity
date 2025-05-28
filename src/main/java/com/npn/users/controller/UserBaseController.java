package com.npn.users.controller;

import com.npn.users.mapper.UserMapper;
import com.npn.users.model.dto.RegisterDto;
import com.npn.users.model.dto.UpdateUserProfileDto;
import com.npn.users.model.entity.UserEntity;
import com.npn.users.model.vo.TokenVo;
import com.npn.users.model.vo.UserEntityVo;
import com.npn.users.repository.UserRepository;
import com.npn.users.service.UserServiceBase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping(value = "/user")
@RestController
@Slf4j
public class UserBaseController {

    private final UserMapper userMapper;
    private final UserServiceBase userServiceBase;
    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<UserEntityVo> create(@RequestBody RegisterDto dto) {
        var user = userMapper.entityToVo(userServiceBase.createUser(dto));
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserEntityVo> update(
            @PathVariable("id") Long id,
            @RequestBody UpdateUserProfileDto dto
    ) {
        var user = userMapper.entityToVo(userServiceBase.updateUser(id, dto));
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntityVo> read(
            @PathVariable("id") Long id
    ) {
        var user = userMapper.entityToVo(userServiceBase.getUser(id));
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable("id") Long id
    ) {
        userServiceBase.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserEntityVo>> readAll() {
        List<UserEntityVo> users = new ArrayList<>();
        userServiceBase.getAll().forEach(entity -> users.add(userMapper.entityToVo(entity)));
        return ResponseEntity.ok(users);
    }
}
