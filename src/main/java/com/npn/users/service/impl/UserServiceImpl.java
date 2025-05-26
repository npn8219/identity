package com.npn.users.service.impl;

import com.npn.users.mapper.UserMapper;
import com.npn.users.model.dto.*;
import com.npn.users.model.entity.UserEntity;
import com.npn.users.model.vo.TokenVo;
import com.npn.users.model.vo.UserEntityVo;
import com.npn.users.model.vo.UserRolesPermissionsVo;
import com.npn.users.repository.UserRepository;
import com.npn.users.service.UserService;
import org.springframework.util.StringUtils;

public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    UserMapper userMapper;


    @Override
    public TokenVo register(RegisterDto dto) {
//
//        final String username = dto.username().trim();
//        final String password = dto.password().trim();
//
        return null;
    }

    @Override
    public TokenVo login(LoginDto dto) {
        return null;
    }

    @Override
    public void logout() {

    }

    @Override
    public void checkDisableRegistration(Boolean value) {

    }

    @Override
    public void addRoles(Long id, AssignRolesDto dto) {

    }

    @Override
    public void removeRoles(Long id, AssignRolesDto dto) {

    }

    @Override
    public void updateRoles(Long id, UpdateRolesUserDto dto) {

    }

    @Override
    public UserRolesPermissionsVo getRolesPermissions(Long id) {
        return null;
    }

    @Override
    public void updateProfile(Long id, UpdateUserProfileDto dto) {

    }

    @Override
    public void updateUsername(Long id, UpdateUserUsernameDto dto) {

    }

    @Override
    public void updatePassword(Long id, UpdateUserPasswordDto dto) {

    }

    @Override
    public void updateStates(Long id, UpdateUserStatesDto dto) {

    }

    @Override
    public UserEntityVo getLoginInfo() {
        return null;
    }

    @Override
    public UserEntityVo queryDetails(String id) {
        return null;
    }

    @Override
    public UserEntityVo query(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public long count() {
        return 0;
    }
}
