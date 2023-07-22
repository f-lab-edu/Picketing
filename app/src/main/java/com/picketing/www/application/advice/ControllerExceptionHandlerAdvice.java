package com.picketing.www.application.advice;


import com.picketing.www.application.exception.BadRequestException;
import com.picketing.www.application.exception.ErrorCode;
import com.picketing.www.application.exception.UserNotFoundException;
import com.picketing.www.presentation.dto.response.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandlerAdvice {

    @ExceptionHandler(BadRequestException.class)
    public ErrorResponse badRequestExceptionHandler(BadRequestException ex) {
        return new ErrorResponse(ex.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ErrorResponse userNotFoundExceptionHandler(UserNotFoundException ex) {
        return new ErrorResponse(ErrorCode.USER_NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ErrorResponse otherExceptionHandler(Exception ex) {
        return new ErrorResponse(ErrorCode.INTERNAL_UNKNOWN_ERROR);
    }

}
