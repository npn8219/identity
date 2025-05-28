package com.npn.users.service.impl;

import com.npn.users.exception.CustomException;
import com.npn.users.model.dto.RegisterDto;
import com.npn.users.model.dto.UpdateUserProfileDto;
import com.npn.users.model.entity.UserEntity;
import com.npn.users.repository.UserRepository;
import com.npn.users.service.UserServiceBase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
@Slf4j
public class UserServiceBaseImpl implements UserServiceBase {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public boolean delete(Long id) {
        UserEntity user = findUser(id);
        if (user.getDeleted()) {
            throw CustomException.userDeleted();
        }
        user.setDeleted(true);
        userRepository.save(user);
        return true;
    }

    @Override
    @Transactional
    public UserEntity createUser(RegisterDto dto) {
        if (findUserByUsername(dto.username()) != null)
            throw CustomException.usernameAlreadyExists();
        UserEntity user = new UserEntity();
        user.setUsername(dto.username().trim());
        user.setPassword(dto.password().trim());
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public UserEntity updateUser(Long id, UpdateUserProfileDto dto) {
        UserEntity user = findUser(id);
        if (user.getDeleted()) {
            throw CustomException.userDeleted();
        }
        user.setFirstName(dto.firstName());
        user.setLastName(dto.lastName());
        user.setEmail(dto.email());
        user.setDob(dto.dob());
        return userRepository.save(user);
    }

    @Override
    public UserEntity getUser(Long id) {
        UserEntity user = findUser(id);
        if (user.getDeleted()) {
            throw CustomException.userDeleted();
        }
        return user;
    }

    @Override
    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

    private UserEntity findUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(CustomException::userNotFound);
    }

    private UserEntity findUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }
}
