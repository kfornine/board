package jmp.spring.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import jmp.spring.service.LoginService;
import jmp.spring.vo.User;

public class AuthInterceptor extends HandlerInterceptorAdapter{
	
	@Autowired
	LoginService service;
	
	/**
	 * This implementation always returns {@code true}.
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");

		//자동로그인처리
		// 만약 유저가 null이라면
		if(user == null) {
			//자동로그인이 가능한 사용자인지 확인
			//Cookie[] loginCookie = request.getCookies();
			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
			if(loginCookie != null) {
				user = service.loginSessionKey(loginCookie.getValue());
				//세션에 유저 객체를 생성
				session.setAttribute("user", user);
			}
		}
		
		// 로그인 OK 
		if( user != null) {
			// ROLE_USER 권한 체크
			if(user.hasRole("ROLE_USER")) { //참이면 true
				return true;		
			} else { //거짓이면 login으로
				response.sendRedirect("/login");
				return false;
			}
			
		} else {
			response.sendRedirect("/login");
			return false;
		}
		
	}

	/**
	 * This implementation is empty.
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {
	}
}
