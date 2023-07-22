package com.picketing.www.application.exception;

public enum ErrorCode {
    USER_NOT_FOUND(10001, "가입되지 않은 이메일입니다."),
    INVALID_PASSWORD(10002, "잘못된 비밀번호입니다."),
    INTERNAL_UNKNOWN_ERROR(50000, "Unknown Error");

    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }
    public String getMessage() {
        return this.message;
    }
}
