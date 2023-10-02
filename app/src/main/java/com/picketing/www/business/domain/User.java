package com.picketing.www.business.domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.picketing.www.application.exception.CustomException;
import com.picketing.www.application.exception.ErrorCode;
import com.picketing.www.persistence.table.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USERS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter(AccessLevel.PROTECTED)
public class User extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String email;
	private String name;
	private String phoneNumber;
	private String password;

	@Builder
	User(Long id, String email, String password, String name, String phoneNumber) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.phoneNumber = phoneNumber;
		initValidation();
	}

	private void initValidation() {
		if (this.email == null || isValidEmailPattern(this.email)) {
			throw new CustomException(ErrorCode.INVALID_EMAIL_FORMAT);
		}
	}

	private boolean isValidEmailPattern(String value) {
		final String emailValidRegex = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
		Pattern pattern = Pattern.compile(emailValidRegex);
		Matcher matcher = pattern.matcher(value);
		return !matcher.matches();
	}

	public String getEmail() {
		return this.email;
	}

	public boolean match(User loginUser) {
		return loginUser.password.equals(this.password);
	}

	public Long getId() {
		return this.id;
	}
}
