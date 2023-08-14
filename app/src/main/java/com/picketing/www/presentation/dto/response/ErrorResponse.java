package com.picketing.www.presentation.dto.response;

import com.picketing.www.application.exception.ErrorCode;

import lombok.Getter;

@Getter
public class ErrorResponse {
	private final int code;

	private final String message;

	public ErrorResponse(ErrorCode code) {
		this.code = code.getCode();
		this.message = code.getMessage();
	}

	public static ErrorResponse error(ErrorCode code) {
		return new ErrorResponse(code);
	}

}
