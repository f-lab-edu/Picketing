package com.picketing.www.application.filter.encoding;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.core.JsonGenerator;
import com.picketing.www.application.exception.ErrorCode;
import com.picketing.www.presentation.dto.response.ErrorResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.picketing.www.application.exception.BadRequestException;
import com.picketing.www.application.filter.encoding.password.HttpRequestWrapper;
import com.picketing.www.application.filter.encoding.password.PasswordEncoder;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Component
@WebFilter(urlPatterns = {"/api/users"})
@RequiredArgsConstructor
public class PasswordEncodingFilter implements Filter {

	private final PasswordEncoder passwordEncoder;
	private final ObjectMapper objectMapper;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
		throws IOException, ServletException {
		HttpRequestWrapper requestWrapper = new HttpRequestWrapper((HttpServletRequest)request);
		String body = requestWrapper.getBody();
		try {
			String resolveJson = changePassword(body);
			requestWrapper.setBody(resolveJson);
		} catch (BadRequestException ex) {
			HttpServletResponse httpServletResponse = (HttpServletResponse) response;
			objectMapper.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
			httpServletResponse.setStatus(ErrorCode.INVALID_PASSWORD_FORMAT.getHttpStatus().value());
			httpServletResponse.getWriter()
					.write(objectMapper.writeValueAsString(ErrorResponse.error(ErrorCode.INVALID_PASSWORD_FORMAT)));
			httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
			return;
		}

		chain.doFilter(requestWrapper, response);
	}

	private String changePassword(String json) throws JsonProcessingException, BadRequestException {
		final String FIELD_NAME = "password";
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode rootNode = objectMapper.readTree(json);
		if (rootNode.isObject()) {
			ObjectNode objectNode = (ObjectNode)rootNode;
			if (objectNode.has(FIELD_NAME)) {
				String plainTextPassword = String.valueOf(objectNode.get("password"));

				validPassword(plainTextPassword);

				String encodedPassword = passwordEncoder.encode(plainTextPassword);
				objectNode.put(FIELD_NAME, encodedPassword);
			}
		}
		return objectMapper.writeValueAsString(rootNode);
	}

	private void validPassword(String plainTextPassword) throws BadRequestException {
		if (plainTextPassword == null || isValidPasswordPattern(plainTextPassword)) {
			throw new BadRequestException(ErrorCode.INVALID_PASSWORD_FORMAT);
		}
	}

	private boolean isValidPasswordPattern(String value) {
		final String PASSWORD_VALID_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$";
		Pattern pattern = Pattern.compile(PASSWORD_VALID_REGEX);
		Matcher matcher = pattern.matcher(value);
		return !matcher.matches();
	}
}
