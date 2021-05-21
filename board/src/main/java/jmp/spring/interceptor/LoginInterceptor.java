package jmp.spring.interceptor;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import jmp.spring.service.LoginService;
import jmp.spring.vo.User;
import lombok.extern.log4j.Log4j;



@Log4j
public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	LoginService service;
	
	/**
	 * 컨트롤러 실행전에 실행(화면 불러오기전에)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		return true;
		
	}

	/**
	 * 컨트롤러 실행후에 실행(화면 불러온후)
	 * loginProcess이 실행된 이후 처리
	 * 	 * 조건 >> 세션에 쥬저객체가 생성되어있다
	 * 		   	  자동로그인에 체크가 되어 있다
	 * 1.자동로그인을 위한 쿠키를 생성
	 * 2.db에 세션키값과 유효기간을 저장
	 * -->저장도니 자동로그인 쿠키의 value(sessionId)값을
	 * users테이블에서 조회 해서 일치하는 값이 있으면 자동로그인 처리
	 * 
	 * 저장후 users테이블에 sessionkey값이 저장되었는지확인
	 * 
	 * 로그인프로세스 컨트롤러가 실행된 이후에 자동로그인을 위한 쿠키를 저장
	 * 만약에 쿠기가 생성되지 않는다면 로직이 실행되는지 로그를 찍어봅시다
	 * 인터셉터 클래스가 실행 되지 않는다면 설정파일에 등록이 되어있는지 확인해보세요
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {
		/**
		 * 		컨트롤러가 실행된 이후
		 *		로그인이 성공했다면 = 세션에 user객체가 생성되어져있다면
		 *		세션에 user 객체를 생성하는 시점 : /loginProcess
		 *		세션 반환 = request.getSession();
		 *		쿠키[] 반환 = request.getCookies();
		 *		WebUtills.getCookie(request, "");
		 * 
		 */
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");//object를 user로 형변환 내가 정해준 이름"user"
		System.out.println("\nuser\n\n\n\n\n\n\n"+user);
		System.out.println("\nrequest.getAttribute(\"useCookie\")\n\n\n\n\n\n\n"+request.getParameter("useCookie"));
		/**
		 *	//로그인에 성공 했다면 자동로그인을 위한 쿠키를 생성 합니다
		 *	//자동 로그인에 체크가 되었을때 로직추가
		 *	//request.getAttribute("useCookie");
 		 *	//StringUtils.isEmpty(request.getAttribute("useCookie")); 비어있지않으면
 		 *	//getParameter로 파라미터값을 가져옵니다
		 */
		if(user != null 
				&& request.getParameter("useCookie") != null) { //useCookie 체크박스에 체크되있다면
			/**
			 * 
			 * users테이블에 쿠기정보를 저장합니다
			 * userid, sessionkey = 세션아이디를 저장 하기로 약속
			 * loginCookie = 자동로그인시 생성하는 쿠키
			 * 여기에 저장되는 value 값과 user테이블의 sessionkey에 저장되는 값은 동일해야함
			 * 
			 * */


			 //2.db에 세션 키값과 유효기간을 저장
			 //자동로그인시 저장된 쿠키값을 db에서 조회
			 user.setSessionkey(session.getId()); 
			 service.updateSessionKey(user);

				//*****자동로그인을 위한 쿠키를 생성 합니다.*****
			 Cookie loginCookie = new Cookie("loginCookie", session.getId());
			 //1.유효기간과 경로를 지정합니다
			 loginCookie.setMaxAge(60*60*24*7); //7일
			 loginCookie.setPath("/");
			 
			 //자동로그인을 위해 생성한 쿠키를 response 객체에 저장 합니다.
			 response.addCookie(loginCookie);
			 
			 //만약 자동로그인시 지정된 tmpUri가 세션에 존재한다면 
			 //원래 요청한 페이지가 있었다면 그 요청 페이지로 이동
			 
			 
			 String tmpUri = (String)session.getAttribute("tmlUri");
			 if(!StringUtils.isEmpty(tmpUri)) {
				 response.sendRedirect(tmpUri);
			 }
		}
		
		
		
	}
	

}
