package com.picketing.www.application.filter.encoding;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.picketing.www.application.exception.BadRequestException;
import com.picketing.www.application.filter.encoding.password.PasswordEncoder;

@DisplayName("Password 패턴 검증 테스트")
class PasswordEncodingFilterTest {

    PasswordEncoder passwordEncoder = new PasswordEncoder("testsalt1234");
    PasswordEncodingFilter passwordEncodingFilter = new PasswordEncodingFilter(passwordEncoder);

    @Nested
    @DisplayName("isPasswordInsecure")
    class IsPasswordInsecure {

        @Test
        @DisplayName("안전한 비밀번호")
        void safePassword() {
            boolean passwordInsecure = passwordEncodingFilter.isPasswordInsecure("testABC1@");
            assertFalse(passwordInsecure);
        }

        @Test
        @DisplayName("안전하지 않은 비밀번호")
        void passwordInsecure() {
            boolean passwordInsecure = passwordEncodingFilter.isPasswordInsecure("test");
            assertTrue(passwordInsecure);
        }
    }

    @Nested
    @DisplayName("validPassword")
    class ValidPassword {

        @Test
        @DisplayName("안전한 비밀번호")
        void safePassword() {
            passwordEncodingFilter.validPassword("testABC1@");
        }

        @Test
        @DisplayName("안전하지 않은 비밀번호")
        void passwordInsecure() {
            passwordEncodingFilter.validPassword("test");
            assertThrows(
                BadRequestException.class,
                () -> passwordEncodingFilter.validPassword("test")
            );
        }
    }
}