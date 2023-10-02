package com.picketing.www.business.domain;

import org.springframework.stereotype.Component;

import com.picketing.www.presentation.dto.request.user.UserSignInRequest;
import com.picketing.www.presentation.dto.request.user.UserSignUpRequest;
import com.picketing.www.presentation.dto.response.user.UserDetailResponse;
import com.picketing.www.presentation.dto.response.user.UserSignInResponse;

@Component
public class UserFactory {

	public User create(UserSignUpRequest userSignUpRequest) {
		return User.builder()
			.email(userSignUpRequest.email())
			.password(userSignUpRequest.password())
			.build();
	}

	public UserDetailResponse findResponse(User user) {
		return UserDetailResponse.builder()
			.email(user.getEmail())
			.name(user.getName())
			.build();
	}

	public User create(UserSignInRequest userSignInRequest) {
		return User.builder()
			.email(userSignInRequest.email())
			.password(userSignInRequest.password())
			.build();
	}

	public UserSignInResponse signInResponse(User user) {
		return UserSignInResponse.builder()
			.email(user.getEmail())
			.build();
	}

}
