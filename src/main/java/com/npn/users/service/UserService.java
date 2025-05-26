package com.npn.users.service;

import com.npn.users.model.dto.*;
import com.npn.users.model.vo.TokenVo;
import com.npn.users.model.vo.UserEntityVo;
import com.npn.users.model.vo.UserRolesPermissionsVo;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    //Authentication & Authorization
    TokenVo register(RegisterDto dto);
    TokenVo login(LoginDto dto);
    void logout();
    void checkDisableRegistration(Boolean value);

    //Role & Permission Management
    void addRoles(Long id, AssignRolesDto dto);
    void removeRoles(Long id, AssignRolesDto dto);
    void updateRoles(Long id, UpdateRolesUserDto dto);
    UserRolesPermissionsVo getRolesPermissions(Long id);

    //User Profile Management
    void updateProfile(Long id, UpdateUserProfileDto dto);
    void updateUsername(Long id, UpdateUserUsernameDto dto);
    void updatePassword(Long id, UpdateUserPasswordDto dto);
    void updateStates(Long id, UpdateUserStatesDto dto);

    //User Information Retrieval
    UserEntityVo getLoginInfo();
    UserEntityVo queryDetails(String id);
    UserEntityVo query(Long id);

    //User Management (CRUD)
    void delete(Long id);
    long count();

}
