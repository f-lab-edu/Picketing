package com.picketing.www.presentation.dto.response;

import com.picketing.www.application.exception.ErrorCode;
import lombok.Getter;

@Getter
public class ErrorResponse {
    private int code;

    private String message;

    public ErrorResponse(String message) {
        this.message = message;
    }

    public ErrorResponse(ErrorCode code) {
        this.code = code.getCode();
        this.message = code.getMessage();
    }

    public static ErrorResponse error(ErrorCode code) {
        return new ErrorResponse(code);
    }

    public static ErrorResponse error(String message) {
        return new ErrorResponse(message);
    }

}
