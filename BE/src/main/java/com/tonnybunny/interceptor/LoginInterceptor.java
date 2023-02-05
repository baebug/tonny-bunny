package com.tonnybunny.interceptor;


import com.tonnybunny.common.auth.service.AuthService;
import com.tonnybunny.exception.CustomException;
import com.tonnybunny.exception.ErrorCode;
import io.swagger.models.HttpMethod;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;


@Component
@RequiredArgsConstructor
public class LoginInterceptor implements HandlerInterceptor {

	private final AuthService authService;


	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		if (isPreflightRequest(request)) {
			// 모든 토큰 정보를 읽는 로직
			//			Enumeration eHeader = request.getHeaderNames();
			//			while (eHeader.hasMoreElements()) {
			//				String request_Name = (String) eHeader.nextElement();
			//				String request_Value = request.getHeader(request_Name);
			//				System.out.println("request_Name : " + request_Name + " | request_Value : " + request_Value);
			//			}
			return true;
		}
		System.out.println("#Interceptor PreHandle Method Req URI : " + request.getRequestURI());
		/**
		 * 이 영역에서 인증여부를 판단하여 로그인 페이지로 보낼 로직을 구현
		 *
		 *         HttpSession session = request.getSession(false);
		 *         if(session != null) {
		 *             Object obj = session.getAttribute("member");
		 *             if(obj != null)
		 *                 return true;
		 *         }
		 *
		 *         response.sendRedirect(request.getContextPath() + "/");
		 *         return false;
		 */

		System.out.println("Access 토큰 확인 인터셉터");
		String accessToken = request.getHeader("ACCESS_TOKEN");
		System.out.println("AccessToken:" + accessToken);

		if (accessToken != null) {
			if (authService.isValidToken(accessToken)) {
				return true;
			} else {
				// 토큰 정보에 문제가 있음 = 재발급 과정으로
				throw new CustomException(ErrorCode.ACCESS_TOKEN_ERROR);
			}
		} else {
			// 토큰 자체가 없음 = 로그인 페이지로?
			throw new CustomException(ErrorCode.NOT_FOUND_TOKEN);
		}
		//
		//		return true;

	}


	private boolean isPreflightRequest(HttpServletRequest request) {
		return isOptions(request) && hasHeaders(request) && hasMethod(request) && hasOrigin(request);
	}


	private boolean isOptions(HttpServletRequest request) {
		return request.getMethod().equalsIgnoreCase(HttpMethod.OPTIONS.toString());
	}


	private boolean hasHeaders(HttpServletRequest request) {
		return Objects.nonNull(request.getHeader("Access-Control-Request-Headers"));
	}


	private boolean hasMethod(HttpServletRequest request) {
		return Objects.nonNull(request.getHeader("Access-Control-Request-Method"));
	}


	private boolean hasOrigin(HttpServletRequest request) {
		return Objects.nonNull(request.getHeader("Origin"));
	}

}
