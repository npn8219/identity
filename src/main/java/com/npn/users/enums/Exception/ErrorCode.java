package com.npn.users.enums.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    // Lỗi chung
    UNCATEGORIZED_EXCEPTION("UNCATEGORIZED_ERROR", "Đã xảy ra lỗi không mong muốn.", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_INPUT("INVALID_INPUT", "Dữ liệu đầu vào không hợp lệ.", HttpStatus.BAD_REQUEST),

    // Lỗi liên quan đến User
    USER_NOT_FOUND("USER_NOT_FOUND", "Người dùng này không tồn tại.", HttpStatus.NOT_FOUND),
    USERNAME_ALREADY_EXISTS("USERNAME_ALREADY_EXISTS", "Tên đăng nhập này đã tồn tại.", HttpStatus.BAD_REQUEST),
    EMAIL_ALREADY_EXISTS("EMAIL_ALREADY_EXISTS", "Địa chỉ email này đã tồn tại.", HttpStatus.BAD_REQUEST),
    PASSWORD_NOT_MATCH("PASSWORD_NOT_MATCH", "Mật khẩu cũ không chính xác.", HttpStatus.BAD_REQUEST),
    USER_UNAUTHENTICATED("USER_UNAUTHENTICATED", "Người dùng chưa được xác thực.", HttpStatus.UNAUTHORIZED),
    USER_FORBIDDEN("USER_FORBIDDEN", "Bạn không có quyền thực hiện hành động này.", HttpStatus.FORBIDDEN),
    USER_DELETED("USER_DELETED", "Người dùng này đã bị xóa", HttpStatus.NOT_FOUND),


    // Lỗi liên quan đến Role
    ROLE_NOT_FOUND("ROLE_NOT_FOUND", "Vai trò này không tồn tại.", HttpStatus.NOT_FOUND),
    ROLE_ALREADY_EXISTS("ROLE_ALREADY_EXISTS", "Vai trò này đã tồn tại.", HttpStatus.BAD_REQUEST),

    // Lỗi liên quan đến Permission
    PERMISSION_NOT_FOUND("PERMISSION_NOT_FOUND", "Quyền này không tồn tại.", HttpStatus.NOT_FOUND),
    PERMISSION_ALREADY_EXISTS("PERMISSION_ALREADY_EXISTS", "Quyền này đã tồn tại.", HttpStatus.BAD_REQUEST)


    ;

    private final String code;
    private final String message;
    private final HttpStatus status;

}