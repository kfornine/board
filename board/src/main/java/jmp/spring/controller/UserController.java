package jmp.spring.controller;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import jmp.spring.service.LoginService;
import jmp.spring.service.MailService;
import jmp.spring.vo.User;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class UserController {

	@Autowired
	LoginService service;
	
	@Autowired
	MailService email;
	
	// 아이디 찾기
	@RequestMapping(value = "/find_id_form", method = RequestMethod.GET)
	public String find_id_form() throws Exception{
		return "/find_id_form";
	}
	
	//아이디 찾기 결과
	@RequestMapping(value = "/find_id", method = RequestMethod.POST)
	public String find_id(User user, Model model) throws Exception{
		User us = service.searchId(user);
		model.addAttribute("id", us.getId());
		return "find_id";
	}
	
	// 비번 찾기
	@RequestMapping(value = "/find_pwd_form", method = RequestMethod.GET)
	public String find_pwd_form() throws Exception{
		return "/find_pwd_form";
	}
	
	//비번 찾기 결과
	@RequestMapping(value = "/find_pwd", method = RequestMethod.POST)
	public String find_pwd(User user, Model model) throws Exception{
		User us = service.searchPwd(user);
		if(service.searchPwd(user)==null) {
			return "error";
		}
		model.addAttribute("pwd", us.getPwd());
		
		email.welcomeMailSend();
		return "find_pwd";
	}
	
	//비밀번호 업데이트
	@RequestMapping(value="update_password", method=RequestMethod.POST)
	public String updatePasswordAction(@RequestParam(value="updateid", defaultValue="", required=false) String id,
										User user) {
		user.setId(id);
		//service.updatePassword(user);
		return "findPasswordConfirm";
	}
	
	
	@GetMapping("/userSearch")
	public void userSearchh() {
		
	}
	
	
	@GetMapping("/login")
	public void loginn() {
		
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.invalidate();
		
		//자동로그인 쿠키를 제거 해줍시다
		//로그아웃을 하게 되면 더이상 자동로그인을 할 수 없습니다
		Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
		
		//널처리 , 로그인 쿠키가 있을때만 처리(없으면 실행안하고 login불러옴)
		if(loginCookie != null) {
			loginCookie.setMaxAge(0);
			loginCookie.setPath("/");
			
			response.addCookie(loginCookie);
		}
		
		return "/login";
	}
	
	@PostMapping("/loginProcess") //폼 action경로
	public String loginProcesss(User vo, Model model, HttpServletRequest request) {
		
		User user = service.login(vo);
		
		if(user == null) {
			model.addAttribute("msg","로그인에 실패하였습니다 \nId,Pwd를 확인하세요");
			return "/login";
			
		}else {
			// user 객체를 세션에 담아줌 - 로그인 처리
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			
			log.info("\n\n\n\n\n\n"+user);
			
			
			model.addAttribute("msg",user.getId()+"님 로그인에 성공하였습니다");
			return "/loginProcess";
		}
		
	}
	
	@GetMapping("/member")
	public void member() {
		
	}
	@PostMapping("/registerMember")
	public String registerMember(User user) {
		//회원가입 처리
		try {
			int res = service.insertUser(user);
			if(res>0) {
				return "forward:/loginProcess";
			}else {
				return "/error";
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			return "/error";
		}
	}
}
