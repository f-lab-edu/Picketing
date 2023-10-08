package com.picketing.www.application.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import com.picketing.www.application.exception.CustomException;
import com.picketing.www.application.exception.ErrorCode;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
		Exception {
		HttpSession session = request.getSession();
		Long userId = (Long)session.getAttribute("login_user");
		System.out.println("LoginCheckInterceptor.preHandle");
		System.out.println("userId = " + userId);

		if (userId == null || userId == 0L) {
			throw new CustomException(ErrorCode.UNAUTHORIZED);
		}
		return true;
	}
}
