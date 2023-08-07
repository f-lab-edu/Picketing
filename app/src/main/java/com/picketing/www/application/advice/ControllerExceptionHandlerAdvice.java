package com.picketing.www.application.advice;

import com.picketing.www.application.exception.CustomException;
import com.picketing.www.application.exception.ErrorCode;
import com.picketing.www.presentation.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandlerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> otherExceptionHandler(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.error(ErrorCode.INTERNAL_UNKNOWN_ERROR));
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> customExceptionHandler(CustomException ex) {
        return ResponseEntity
                .status(ex.getErrorCode().getHttpStatus())
                .body(ErrorResponse.error(ex.getErrorCode()));
    }

}
