package com.picketing.www.business.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Objects;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.picketing.www.application.exception.CustomException;
import com.picketing.www.presentation.dto.request.user.UserSignUpRequest;

@DisplayName("User test")
public class UserTest {

	@Nested
	@DisplayName("생성자")
	public class Constructor {

		private final UserFactory userFactory = new UserFactory();

		@Test
		@DisplayName("정상 생성")
		void create() {
			UserSignUpRequest userSignUpRequest = new UserSignUpRequest(
				"email@gmail.com",
				"password123!"
			);
			User user = userFactory.create(userSignUpRequest);
			assertTrue(Objects.nonNull(user));
		}

		@Test
		@DisplayName("이메일 검증 실패 확인")
		void createButInvalidEmail() {
			UserSignUpRequest userSignUpRequest = new UserSignUpRequest(
				"email",
				"password123!"
			);
			assertThrows(CustomException.class,
				() -> userFactory.create(userSignUpRequest));
		}

		@Test
		@DisplayName("이메일 입력되지 않음")
		void createButEamilIsNull() {
			UserSignUpRequest userSignUpRequest = new UserSignUpRequest(
				null,
				"password123!"
			);
			assertThrows(CustomException.class,
				() -> userFactory.create(userSignUpRequest));
		}

		@Test
		@Disabled
		@DisplayName("비밀번호 입력되지 않음")
		void createButPasswordIsNull() {
			UserSignUpRequest userSignUpRequest = new UserSignUpRequest(
				"email@ruu.kr",
				null
			);
			assertThrows(CustomException.class,
				() -> userFactory.create(userSignUpRequest));
		}

	}
}
