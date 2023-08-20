package com.picketing.www.application.filter.encoding.password;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PasswordEncodeConfig {

	@Value("${PASSWORD_SALT}")
	private String saltEnvKey;

	@Bean
	public String salt() {
		return saltEnvKey;
	}
}
