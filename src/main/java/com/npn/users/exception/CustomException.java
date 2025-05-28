package com.npn.users.exception;

import com.npn.users.enums.Exception.ErrorCode;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CustomException extends RuntimeException {

    private final ErrorCode errorCode;

    // Lỗi chung
    public static CustomException uncategorizedException() {
        return new CustomException(ErrorCode.UNCATEGORIZED_EXCEPTION);
    }

    public static CustomException invalidInput() {
        return new CustomException(ErrorCode.INVALID_INPUT);
    }

    // Lỗi liên quan đến User
    public static CustomException userNotFound() {
        return new CustomException(ErrorCode.USER_NOT_FOUND);
    }

    public static CustomException usernameAlreadyExists() {
        return new CustomException(ErrorCode.USERNAME_ALREADY_EXISTS);
    }

    public static CustomException emailAlreadyExists() {
        return new CustomException(ErrorCode.EMAIL_ALREADY_EXISTS);
    }

    public static CustomException passwordNotMatch() {
        return new CustomException(ErrorCode.PASSWORD_NOT_MATCH);
    }

    public static CustomException userUnauthenticated() {
        return new CustomException(ErrorCode.USER_UNAUTHENTICATED);
    }

    public static CustomException userForbidden() {
        return new CustomException(ErrorCode.USER_FORBIDDEN);
    }

    public static CustomException userDeleted() {
        return new CustomException(ErrorCode.USER_DELETED);
    }


    // Lỗi liên quan đến Role
    public static CustomException roleNotFound() {
        return new CustomException(ErrorCode.ROLE_NOT_FOUND);
    }

    public static CustomException roleAlreadyExists() {
        return new CustomException(ErrorCode.ROLE_ALREADY_EXISTS);
    }

    // Lỗi liên quan đến Permission
    public static CustomException permissionNotFound() {
        return new CustomException(ErrorCode.PERMISSION_NOT_FOUND);
    }

    public static CustomException permissionAlreadyExists() {
        return new CustomException(ErrorCode.PERMISSION_ALREADY_EXISTS);
    }
}