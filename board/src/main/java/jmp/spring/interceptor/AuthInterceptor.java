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

		//로그인하지않았지만 자동로그인 처리되어있으면(유저가null && 자동로그인체크)
		//자동로그인처리
		// 만약 유저가 null이라면
		if(user == null) {
			//자동로그인이 가능한 사용자인지 확인
			//Cookie[] loginCookie = request.getCookies();
			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
			//자동로그인 사용자(로그인쿠키가 있으면)
			if(loginCookie != null) {
				user = service.loginSessionKey(loginCookie.getValue());
				//널처리
				if(user != null) {
					//세션에 유저 객체를 생성
					session.setAttribute("user", user);
				}
			}
		}
		
		//1.로그인 확인 2.권한 확인
		// 로그인 OK 
		//유저가 있으면
		if( user != null) {
			// ROLE_USER 권한 체크
			if(user.hasRole("ROLE_USER")) { //참이면 true
				//로그인이 되어있고 권한이 충분한 자만이 /board/list에 접근
				return true;		
			}
		}
		
		//만약 로그인을 안했거나 권한이 없다면 로그인 페이지로 이동 합니다.
		//원래 요청했던 페이지와 파라메터를 세션에 저장 합니다. --> 세션 삭제해야함
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n"+request.getRequestURI());
		System.out.println(request.getQueryString());
		
		String uri = request.getRequestURI(); //기존에 요청의 uri페이지 정보
		String query= request.getQueryString(); //기존 요청의 파라메터
		
		if(query != null) {
			uri += "?" + query;
		}
		//loginprocess?
		session.setAttribute("tmpUri", uri);
		
		//로그인 안함	
		response.sendRedirect("/login");
		return false;
	}

	/**
	 * This implementation is empty.
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {
	}
}
