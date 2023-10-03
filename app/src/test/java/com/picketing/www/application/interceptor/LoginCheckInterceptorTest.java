package com.picketing.www.application.interceptor;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.picketing.www.application.exception.CustomException;

@SpringBootTest
@DisplayName("LoginCheckInterceptor Test")
class LoginCheckInterceptorTest {

	@Autowired
	private RequestMappingHandlerMapping mapping;

	@DisplayName("exclude URL로 접근 시, interceptor를 거치지 않는지 테스트")
	@Test
	void should_not_intercept_when_request_to_excluded_url() throws Exception {
		// given
		MockHttpServletRequest request = new MockHttpServletRequest("POST", "/api/users");

		HandlerExecutionChain chain = mapping.getHandler(request);

		assert chain != null;
		Optional<HandlerInterceptor> interceptor = chain.getInterceptorList()
			.stream()
			.filter(LoginCheckInterceptor.class::isInstance)
			.findFirst();

		// then
		Assertions.assertThat(interceptor).isEmpty();
	}

	@DisplayName("include URL로 접근 시, interceptor를 거치는지 테스트")
	@Test
	void should_intercept_when_request_to_included_url() throws Exception {
		// given
		MockHttpServletRequest request = new MockHttpServletRequest("POST", "/api/reservation");

		HandlerExecutionChain chain = mapping.getHandler(request);

		assert chain != null;
		Optional<HandlerInterceptor> interceptor = chain.getInterceptorList()
			.stream()
			.filter(LoginCheckInterceptor.class::isInstance)
			.findFirst();

		// then
		Assertions.assertThat(interceptor).isNotEmpty();
	}

	@Nested
	class ContextLoginUserHasSession {
		@Test
		@DisplayName("세션이 존재할 때 Interceptor가 true를 반환하는지 테스트")
		void should_returns_true_in_prehandle() throws Exception {
			MockHttpServletRequest request = new MockHttpServletRequest();
			MockHttpServletResponse response = new MockHttpServletResponse();
			MockHttpSession session = new MockHttpSession();
			LoginCheckInterceptor interceptor = new LoginCheckInterceptor();

			// given
			session.setAttribute("login_user", 1L);
			request.setSession(session);

			// when
			boolean result = interceptor.preHandle(request, response, null);

			// then
			Assertions.assertThat(result).isTrue();
		}
	}

	@Nested
	class ContextLoginUserHasNoSession {
		@Test
		@DisplayName("세션이 존재할 때 Interceptor가 예외를 반환하는지 테스트")
		void should_returns_exception_in_prehandle() throws Exception {
			MockHttpServletRequest request = new MockHttpServletRequest();
			MockHttpServletResponse response = new MockHttpServletResponse();
			MockHttpSession session = new MockHttpSession();
			LoginCheckInterceptor interceptor = new LoginCheckInterceptor();
			// given
			session.setAttribute("login_user", 1L);
			request.setSession(session);
			Assertions.assertThat(interceptor.preHandle(request, response, null)).isTrue();

			session.removeAttribute("login_user");

			Assertions.assertThatThrownBy(() ->
					interceptor.preHandle(request, response, null))
				.isInstanceOf(CustomException.class);
		}
	}

}
