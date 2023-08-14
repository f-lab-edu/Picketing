package com.picketing.www.application.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
	DUPLICATED_EMAIL(10000, "중복된 이메일입니다.", HttpStatus.BAD_REQUEST),
	INVALID_EMAIL_FORMAT(10001, "Email 형식이 맞지 않습니다.", HttpStatus.BAD_REQUEST),
	INVALID_PASSWORD_FORMAT(10002, "비밀번호 형식이 맞지 않습니다.", HttpStatus.BAD_REQUEST),
	USER_NOT_FOUND(10003, "가입되지 않은 이메일입니다.", HttpStatus.NOT_FOUND),
	INVALID_PASSWORD(10004, "잘못된 비밀번호입니다.", HttpStatus.BAD_REQUEST),
	UNAUTHORIZED(10005, "로그인 한 사용자 정보를 찾을 수 없습니다 다시 로그인 해주세요", HttpStatus.UNAUTHORIZED),
	INTERNAL_UNKNOWN_ERROR(50000, "Unknown Error", HttpStatus.INTERNAL_SERVER_ERROR);

	private final int code;
	private final String message;
	private final HttpStatus httpStatus;
}
