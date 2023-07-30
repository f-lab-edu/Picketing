package com.picketing.www.application.exception;

public class UserNotFoundException extends CustomException {
    private final ErrorCode errorCode;

    public UserNotFoundException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
