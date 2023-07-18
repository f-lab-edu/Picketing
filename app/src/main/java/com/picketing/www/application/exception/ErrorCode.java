package com.picketing.www.application.exception;

public enum ErrorCode {

    USER_NOT_FOUND(10001, "가입되지 않은 이메일입니다.");

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
