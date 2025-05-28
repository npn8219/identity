package com.npn.users.service.impl;

import com.npn.users.exception.CustomException;
import com.npn.users.mapper.UserMapper;
import com.npn.users.model.dto.*;
import com.npn.users.model.entity.UserEntity;
import com.npn.users.model.vo.TokenVo;
import com.npn.users.model.vo.UserEntityVo;
import com.npn.users.model.vo.UserRolesPermissionsVo;
import com.npn.users.repository.UserRepository;
import com.npn.users.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    UserMapper userMapper;

    @Override
    public TokenVo register(RegisterDto dto) {
        final String username = dto.username().trim();
        final String password = dto.password().trim();

        checkExistedUsername(username);

        UserEntity entity = new UserEntity();
        entity.setUsername(username);
        entity.setPassword(password);

        return createToken(userRepository.save(entity));
    }

    @Override
    public TokenVo login(LoginDto dto) {
        return null;
    }

    @Override
    public void logout() {

    }

    @Override
    public UserEntityVo getLoginInfo() {
        return null;
    }

    @Override
    public void checkDisableRegistration(Boolean value) {

    }

    @Override
    @Transactional
    public void addRoles(Long id, AssignRolesDto dto) {

    }

    @Override
    @Transactional
    public void removeRoles(Long id, AssignRolesDto dto) {

    }

    @Override
    @Transactional
    public void updateRoles(Long id, UpdateRolesUserDto dto) {

    }

    @Override
    public UserRolesPermissionsVo getRolesPermissions(Long id) {
        return null;
    }

    @Override
    @Transactional
    public void updateProfile(Long id, UpdateUserProfileDto dto) {
        UserEntity user = findUser(id);
        user.setFirstName(dto.firstName());
        user.setLastName(dto.lastName());
        user.setEmail(dto.email());
        user.setDob(dto.dob());
    }

    @Override
    @Transactional
    public void updateUsername(Long id, UpdateUserUsernameDto dto) {
        UserEntity user = findUser(id);
        checkExistedUsername(dto.username().trim(), id);
        user.setUsername(dto.username().trim());
    }

    @Transactional
    @Override
    public void updatePassword(Long id, UpdateUserPasswordDto dto) {
        UserEntity user = findUser(id);
        checkPasswordUser(user, dto);
        user.setPassword(dto.newPassword().trim());
    }

    @Override
    @Transactional
    public void updateStates(Long id, UpdateUserStatesDto dto) {

    }

    @Override
    public UserEntityVo queryDetails(Long id) {
        UserEntity user = findUser(id);
        return userMapper.entityToVo(user);
    }

    @Override
    public UserEntityVo query(Long id) {
        return null;
    }

    @Transactional
    @Override
    public void delete(Long id) {
        UserEntity user = findUser(id);
        user.setDeleted(true);
        userRepository.delete(user);
    }

    private UserEntity findUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(CustomException::userNotFound);
    }

    private void checkExistedUsername(String username) {
        if (userRepository.findByUsername(username).isPresent())
            throw CustomException.usernameAlreadyExists();
    }

    private void checkExistedUsername(String name, Long id) {
        if (userRepository.existsByUsernameAndIdNot(name, id).isPresent())
            throw CustomException.usernameAlreadyExists();
    }

    private void checkPasswordUser(UserEntity user, UpdateUserPasswordDto dto) {
        String oldPassword = dto.oldPassword().trim();
        String currentPassword = user.getPassword().trim();

        if (!oldPassword.equals(currentPassword))
            throw CustomException.passwordNotMatch();
    }

    private TokenVo createToken(UserEntity user) {
        TokenVo tokenVo = new TokenVo();
//        tokenVo.setToken(user.getToken());
//        tokenVo.setUserId(user.getId());
        return tokenVo;
    }

}
