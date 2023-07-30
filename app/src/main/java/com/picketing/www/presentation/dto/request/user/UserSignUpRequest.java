package com.picketing.www.presentation.dto.request.user;

public record UserSignUpRequest(
	String email,
	String password
) {
}
