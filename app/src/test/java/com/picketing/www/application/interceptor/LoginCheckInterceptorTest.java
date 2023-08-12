package com.picketing.www.application.interceptor;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import com.picketing.www.application.exception.CustomException;

@DisplayName("LoginCheckInterceptor Test")
class LoginCheckInterceptorTest {

	private LoginCheckInterceptor loginCheckInterceptor;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private MockHttpSession session;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		loginCheckInterceptor = new LoginCheckInterceptor();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		session = new MockHttpSession();
	}

	@Nested
	class Context_Login_User_Has_Session {
		@Test
		@DisplayName("세션이 존재할 때 Interceptor가 true를 반환하는지 테스트")
		void should_returns_true_in_prehandle() throws Exception {
			// given
			session.setAttribute("login_user", "qwerty1234@gmail.com");
			request.setSession(session);

			// when
			boolean result = loginCheckInterceptor.preHandle(request, response, null);

			// then
			Assertions.assertThat(result).isTrue();
		}
	}

	@Nested
	class Context_Login_User_Has_No_Session {
		@Test
		@DisplayName("세션이 존재할 때 Interceptor가 예외를 반환하는지 테스트")
		void should_returns_exception_in_prehandle() {
			Assertions.assertThatThrownBy(() ->
					loginCheckInterceptor.preHandle(request, response, null))
				.isInstanceOf(CustomException.class);
		}
	}

}