package jmp.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jmp.spring.service.LoginService;
import jmp.spring.vo.User;

@Controller
public class UserController {

	@Autowired
	LoginService service;
	
	
	@GetMapping("/login")
	public void loginn() {
		
	}
	
	@PostMapping("/loginProcess") //폼 action경로
	public String loginProcesss(User vo, Model model) {
		
		User user = service.login(vo);
		
		if(user == null) {
			model.addAttribute("msg","로그인에 실패하였습니다 \nId,Pwd를 확인하세요");
			return "/login";
			
		}else {
			
			model.addAttribute("msg",user.getId()+"님 로그인에 성공하였습니다");
			return "/loginProcess";
		}
		
	}
	
	
	
}
