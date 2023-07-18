package com.picketing.www.presentation.dto.response;

import ch.qos.logback.core.spi.ErrorCodes;

public class ErrorResponse<T> {

    private int code;

    private String message;

    public ErrorResponse(String message) {
        this.message = message;
    }


}
