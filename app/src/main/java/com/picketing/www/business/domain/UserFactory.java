package com.picketing.www.business.domain;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.picketing.www.persistence.table.UserPersist;
import com.picketing.www.presentation.dto.request.user.UserSignInRequest;
import com.picketing.www.presentation.dto.request.user.UserSignUpRequest;
import com.picketing.www.presentation.dto.response.user.UserDetailResponse;
import com.picketing.www.presentation.dto.response.user.UserSignInResponse;

@Component
public class UserFactory {

	public User create(UserPersist userPersist) {
		return User.builder()
			.id(userPersist.getId())
			.email(userPersist.getEmail())
			.password(userPersist.getPassword())
			.name(userPersist.getName())
			.phoneNumber(userPersist.getPhoneNumber())
			.createdAt(userPersist.getCreatedAt())
			.modifiedAt(userPersist.getModifiedAt())
			.build();
	}

	public User create(UserSignUpRequest userSignUpRequest) {
		return User.builder()
			.email(userSignUpRequest.email())
			.password(userSignUpRequest.password())
			.createdAt(LocalDateTime.now())
			.modifiedAt(LocalDateTime.now())
			.build();
	}

	public UserPersist persist(User user) {
		return UserPersist.builder()
			.email(user.email)
			.password(user.password)
			.name(user.name)
			.phoneNumber(user.phoneNumber)
			.build();
	}

	public UserDetailResponse findResponse(User user) {
		return UserDetailResponse.builder()
			.email(user.email)
			.name(user.name)
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
			.email(user.email)
			.build();
	}

}
