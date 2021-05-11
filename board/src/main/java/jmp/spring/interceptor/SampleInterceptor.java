package jmp.spring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import jmp.spring.vo.User;
import lombok.extern.log4j.Log4j;

@Log4j
public class SampleInterceptor extends HandlerInterceptorAdapter{

	/**
	 * This implementation always returns {@code true}.
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		log.info("\n\n\n\n\n\n\n\n\n");
		log.info(request);
		log.info(response);
		log.info(handler);
		
		//로그인된 사용자만 이용 = 세션을 가지고 와서 유저 객체가 있는지 확인
		//로그인이 안된 사용자는 로그인페이지로 돌려보냄
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user_session");
		if(user == null) {
			response.sendRedirect("/login");
			return false;
		}else {
			user.hasRole("ROLE_ADMIN");
		}
		
		return true;
	}

	/**
	 * This implementation is empty.
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {
		
		log.info("\n\n\n\n\n\n\n\n\n");
		log.info(request);
		log.info(response);
		log.info(handler);
		log.info(modelAndView);
		
	}
}
