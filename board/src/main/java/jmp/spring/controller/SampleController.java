package jmp.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jmp.spring.service.BoardService;
import jmp.spring.vo.BoardVo;
import jmp.spring.vo.Criteria;
import jmp.spring.vo.PageNavi;
import jmp.spring.vo.ReplyVo;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class SampleController {
	
	@Autowired
	BoardService service;
	
	@GetMapping("/reply")
	public String replyy() {
		return "/board/reply";
	}
	
	@GetMapping("/board/RestTest")
	public void restTest() {
	}
	
	//리스트페이지
	@GetMapping("/board/list")
	public String getlist(Criteria cri, Model model) {
		
		model.addAttribute("list", service.getList(cri));
		
		model.addAttribute("pageNavi", new PageNavi(cri, service.getTotal(cri)));
		
		log.info("getList()============");
		
		return "/board/list_b";
				
	}
	
	//등록페이지로 이동
	@GetMapping("/board/register")
	public String register() {
		return "/board/register_b";
	}
	
	@PostMapping("/board/register")
	public String registerExe(BoardVo vo, RedirectAttributes rttr) {
		log.info(vo);
		int res = service.insertBoard(vo);
		log.info("=========="+vo);
		rttr.addFlashAttribute("resMsg", vo.getBno()+"번 게시글이 작성되었습니다");
		return "redirect:/board/list";
		
	}
	
	//상세정보조회
	@GetMapping("/board/get")
	public String get(Criteria cri, BoardVo vo ,Model model) {
		vo = service.get(vo.getBno());
		
		//모델에 담아서 화면에 전달
		model.addAttribute("vo", vo);
		
		//리턴이없으므로 /board/get(URL)로 페이지연결
		
		return "/board/get_b";
	}
	
	//에딧(포스트)
	@PostMapping("/board/edit")
	public String editExe(Criteria cri ,BoardVo vo, RedirectAttributes rttr) {
		
		int res = service.update(vo);
		String resMsg = "";
		
		if(res>0) {
			resMsg = "수정되었습니다";
		}else {
			resMsg = "수정작업이 실패 했습니다. 관리자에게 문의해주세요.";
		}
		//상세화면 이동시 필요한 파라메터를 세팅
		rttr.addAttribute("bno", vo.getBno());//bno에파라미터값 넣기
		
		rttr.addAttribute("pageNo", cri.getPageNo());//페이지번호
		rttr.addAttribute("type", cri.getType());//타입(제목,내용,작성자)
		rttr.addAttribute("keyword", cri.getKeyword());//검색어
		
		rttr.addFlashAttribute("resMsg", resMsg);
		
		return "redirect:/board/get";
		
	}
	
	@GetMapping("/board/edit")
	public String edit(Criteria cri,BoardVo vo ,Model model) {
		//상세정보조회
		vo = service.get(vo.getBno());
		
		//모델에 담아서 화면에 전달
		model.addAttribute("vo", vo);
		
		//리턴이없으므로 /board/get(URL)로 페이지연결
		
		return "/board/edit_b";
	}
	
	//삭제
	@GetMapping("/board/delete")
	public String delete(Criteria cri, BoardVo vo,RedirectAttributes rttr) {
		int res = service.delete(vo.getBno());
		String resMsg="";
		// 삭제 성공 -> 리스트
		if(res>0) {
			resMsg= vo.getBno()+"번 게시글이 삭제 되었습니다";
			rttr.addFlashAttribute("resMsg", resMsg);
			
			rttr.addAttribute("pageNo", cri.getPageNo());//페이지번호
			rttr.addAttribute("type", cri.getType());//타입(제목,내용,작성자)
			rttr.addAttribute("keyword", cri.getKeyword());//검색어
			
			return "redirect:/board/list";
			
		}else {
			//삭제실패 -> 상세화면
			resMsg="게시글 삭제 처리에 실패했습니다.";
			rttr.addAttribute("bno", vo.getBno());
			rttr.addFlashAttribute("resMsg", resMsg);
			
			rttr.addAttribute("pageNo", cri.getPageNo());//페이지번호
			rttr.addAttribute("type", cri.getType());//타입(제목,내용,작성자)
			rttr.addAttribute("keyword", cri.getKeyword());//검색어
			
			return "redirect:/board/get";
		}
	}

}
