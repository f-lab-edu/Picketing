package com.picketing.www.application.filter.encoding.password;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PasswordEncodeConfig {

	private final String SALT_ENV_KEY = "PASSWORD_SALT";

	@Bean
	public String salt() {
		return System.getenv(SALT_ENV_KEY);
	}
}
