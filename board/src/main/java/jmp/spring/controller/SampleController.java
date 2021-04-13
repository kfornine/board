package jmp.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jmp.spring.service.BoardService;
import jmp.spring.vo.BoardVo;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class SampleController {
	
	@Autowired
	BoardService service;
	
	
	@GetMapping("/board/list")
	public void getlist(Model model) {
		
		model.addAttribute("list", service.getList());
		
		log.info("getList()============");
		
	}
	//등록페이지
	@GetMapping("/board/register")
	public void register() {
		
	}
	
	@PostMapping("/board/register")
	public String registerExe(BoardVo vo) {
		log.info(vo);
		int res = service.insertBoard(vo);
		
		return "redirect:/board/list";
		
	}
	
	@GetMapping("/board/get")
	public void get() {
		
	}
	
	@PostMapping("/edit")
	public void edit() {
		
	}
	
	@GetMapping("/delete")
	public void delete() {
		
	}

}
