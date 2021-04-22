package jmp.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import jmp.spring.service.ReplyService;
import jmp.spring.vo.ReplyVo;
import lombok.extern.log4j.Log4j;

@RestController
@Log4j
public class ReplyController {
	
	@Autowired
	ReplyService service;
	
	//생성
	@PostMapping("/reply/new")
	public String newt(ReplyVo vo) {
		int res = service.insert(vo);
		return "redirect:/reply/list";
	}
	
	
	@GetMapping("/reply/list/{bno}")
	public List<ReplyVo> getList(@PathVariable("bno") int bno) {
		List<ReplyVo> list = service.getList(bno);
		log.info("=================="+list);
		//1.로그찍고 2.쿼리확인 3.쿼리를 디벨로퍼에서 실행
		return list;
	}
	
	
	
	/* 
	 * @Autowired
	 * BoardService service;
	 * @GetMapping("/reply/test") 
	 * public ResponseEntity<List<BoardVo>> restTest() {
	 * Criteria cri = new Criteria(); //ResponseEntity 헤더값(status Code)을 바꿈,응답독립채
	 * return new ResponseEntity<List<BoardVo>>(service.getList(cri),
	 * HttpStatus.OK); //return service.getList(cri); }
	 * 
	 * @GetMapping("/reply/test/{pageNo}")//경로변수, url로 넘어온값을 활용 경로변수로 씀 public
	 * ResponseEntity<List<BoardVo>> restPathTest(@PathVariable("pageNo") int
	 * pageNo) { Criteria cri = new Criteria(pageNo, 20); //url에쓴값을 변수로 넣음
	 * //ResponseEntity 헤더값(status Code)을 바꿈,응답독립채 return new
	 * ResponseEntity<List<BoardVo>>(service.getList(cri), HttpStatus.OK); //return
	 * service.getList(cri); }
	 */

}
