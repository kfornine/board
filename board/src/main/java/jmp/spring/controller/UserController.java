package jmp.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jmp.spring.service.LoginService;
import jmp.spring.vo.User;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class UserController {

	@Autowired
	LoginService service;
	
	
	@GetMapping("/login")
	public void loginn() {
		
	}
	
	@PostMapping("/loginProcess") //폼 action경로
	public String loginProcesss(User vo, Model model, HttpServletRequest req) {
		
		User user = service.login(vo);
		
		if(user == null) {
			model.addAttribute("msg","로그인에 실패하였습니다 \nId,Pwd를 확인하세요");
			return "/login";
			
		}else {
			// user 객체를 세션에 담아줌 - 로그인 처리
			HttpSession session = req.getSession();
			session.setAttribute("user_session", user);
			log.info("\n\n\n\n\n\n"+user);
			
			
			model.addAttribute("msg",user.getId()+"님 로그인에 성공하였습니다");
			return "/loginProcess";
		}
		
	}
	
	
	
}
