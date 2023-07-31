package com.picketing.www.application.exception;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum ErrorCode {
    DUPLICATED_EMAIL(10000, "중복된 이메일입니다.", HttpStatus.BAD_REQUEST),
    INVALID_EMAIL_FORMAT(10001, "Email 형식이 맞지 않습니다.", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD_FORMAT(10002, "비밀번호 형식이 맞지 않습니다.", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(10003, "가입되지 않은 이메일입니다.", HttpStatus.NOT_FOUND),
    INVALID_PASSWORD(10004, "잘못된 비밀번호입니다.", HttpStatus.BAD_REQUEST),
    INTERNAL_UNKNOWN_ERROR(50000, "Unknown Error", HttpStatus.INTERNAL_SERVER_ERROR);

    private final int code;
    private final String message;
    private final HttpStatus httpStatus;
    public int getCode() {
        return code;
    }
    public String getMessage() {
        return this.message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
