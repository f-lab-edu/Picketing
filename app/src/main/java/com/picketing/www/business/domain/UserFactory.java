package com.picketing.www.business.domain;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.picketing.www.persistence.table.UserPersist;
import com.picketing.www.presentation.dto.request.user.UserSignUpRequest;
import com.picketing.www.presentation.dto.response.user.UserDetailResponse;

@Component
public class UserFactory {

	public User create(UserPersist userPersist) {
		return new User(
			userPersist.email(),
			userPersist.password(),
			userPersist.name(),
			userPersist.phoneNumber(),
			userPersist.createdAt(),
			userPersist.modifiedAt()
		);
	}

	public User create(UserSignUpRequest userSignUpRequest) {
		return new User(
			userSignUpRequest.email(),
			userSignUpRequest.password(),
			null,
			null,
			LocalDateTime.now(),
			LocalDateTime.now()
		);
	}

	public UserPersist persist(User user) {
		return new UserPersist(
			user.email,
			user.password,
			user.name,
			user.phoneNumber,
			user.createdAt,
			user.modifiedAt
		);
	}

	public UserDetailResponse findResponse(User user) {
		return new UserDetailResponse(
			user.email,
			user.name
		);
	}
}
