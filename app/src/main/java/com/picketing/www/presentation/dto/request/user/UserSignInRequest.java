package com.picketing.www.presentation.dto.request.user;

public record UserSignInRequest(
        String email,
        String password
) {
}
