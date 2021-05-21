package jmp.spring.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import jmp.spring.service.LoginService;
import jmp.spring.vo.Menu;
import lombok.extern.log4j.Log4j;


@Log4j
public class MenuInterceptor extends HandlerInterceptorAdapter{
	
	@Autowired
	LoginService service;
	
	
	/**
	 * This implementation always returns {@code true}.
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//세션에 메뉴가 없다면 메뉴를 조회후 세션에 저장
		//메뉴 조회후 세션에 저장
		HttpSession session = request.getSession();
		if(session.getAttribute("menu") == null) {
			//메뉴를 조회 하여 세션에 담음
			List<Menu> list = service.menu();
			session.setAttribute("menu", list);
		}
		
		return true; //진행->
	}

	/**
	 * This implementation is empty.
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {
	}

}
