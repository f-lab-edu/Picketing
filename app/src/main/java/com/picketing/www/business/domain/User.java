package com.picketing.www.business.domain;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.picketing.www.application.exception.BadRequestException;

public class User {

	final String email;
	final String name;
	final String phoneNumber;
	final LocalDateTime createdAt;
	final LocalDateTime modifiedAt;
	final String password;

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
		final String EMAIL_VALID_REGEX = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
		if (this.email == null
			|| regexNotMatched(this.email, EMAIL_VALID_REGEX)) {
			throw new BadRequestException("Email 형식이 맞지 않습니다.");
		}
	}

	private boolean regexNotMatched(String value, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(value);
		return !matcher.matches();
	}

	public String getEmail() {
		return this.email;
	}
}
