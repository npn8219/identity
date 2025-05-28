package com.npn.users.service;

import com.npn.users.model.dto.RegisterDto;
import com.npn.users.model.dto.UpdateUserProfileDto;
import com.npn.users.model.entity.UserEntity;

import java.util.List;

public interface UserServiceBase {

    //User Management (CRUD)
    boolean delete(Long id);
    UserEntity createUser(RegisterDto dto);
    UserEntity updateUser(Long id, UpdateUserProfileDto dto);
    UserEntity getUser(Long id);
    List<UserEntity> getAll();
}
