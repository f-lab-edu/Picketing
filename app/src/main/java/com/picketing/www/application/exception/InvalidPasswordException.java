package com.picketing.www.application.exception;

public class InvalidPasswordException extends CustomException {
    private final ErrorCode errorCode;

    public InvalidPasswordException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
