package com.picketing.www.business.domain;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.picketing.www.application.exception.BadRequestException;

import lombok.Builder;

public class User {
    final String email;
    final String name;
    final String phoneNumber;
    final LocalDateTime createdAt;
    final LocalDateTime modifiedAt;
    String password;

    @Builder
    User(String email, String password, String name, String phoneNumber, LocalDateTime createdAt,
        LocalDateTime modifiedAt) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        initValidation();
    }

    private void initValidation() {
        if (this.email == null || isValidEmailPattern(this.email)) {
            throw new BadRequestException("Email 형식이 맞지 않습니다.");
        }
    }

    private boolean isValidEmailPattern(String value) {
        final String EMAIL_VALID_REGEX = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
        Pattern pattern = Pattern.compile(EMAIL_VALID_REGEX);
        Matcher matcher = pattern.matcher(value);
        return !matcher.matches();
    }

    public String getEmail() {
        return this.email;
    }

    public boolean match(User loginUser) {
        return loginUser.password.equals(this.password);
    }
}
