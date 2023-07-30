package com.picketing.www.business.domain;

import com.picketing.www.application.exception.BadRequestException;
import com.picketing.www.presentation.dto.request.user.UserSignUpRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
            assertThrows(BadRequestException.class,
                    () -> userFactory.create(userSignUpRequest));
        }

        @Test
        @DisplayName("이메일 입력되지 않음")
        void createButEamilIsNull() {
            UserSignUpRequest userSignUpRequest = new UserSignUpRequest(
                    null,
                    "password123!"
            );
            assertThrows(BadRequestException.class,
                    () -> userFactory.create(userSignUpRequest));
        }

        @Test
        @DisplayName("비밀번호 검증 실패 확인")
        void createButInvalidPassword() {
            UserSignUpRequest userSignUpRequest = new UserSignUpRequest(
                    "email@ruu.kr",
                    "password123"
            );
            assertThrows(BadRequestException.class,
                    () -> userFactory.create(userSignUpRequest));
        }

        @Test
        @DisplayName("비밀번호 입력되지 않음")
        void createButPasswordIsNull() {
            UserSignUpRequest userSignUpRequest = new UserSignUpRequest(
                    "email@ruu.kr",
                    null
            );
            assertThrows(BadRequestException.class,
                    () -> userFactory.create(userSignUpRequest));
        }


    }
}
