package jmp.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	@GetMapping("/login/index")
	public void index() {
		
	}
	
	@GetMapping("/login/login")
	public void login() {
		
	}
}
