package jmp.spring.board;

import java.util.HashMap;
import java.util.Map;

import jmp.spring.vo.BoardVo;
import jmp.spring.vo.ReplyVo;
import lombok.extern.log4j.Log4j;

@Log4j
public class Test {

	
	@org.junit.Test
	public void mapTest() {
		BoardVo boardVo = new BoardVo();
		boardVo.setBno(1);
		ReplyVo replyVo = new ReplyVo();
		replyVo.setRno(13);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("boardVo", boardVo);
		map.put("replyVo", replyVo);
		
		log.info(map.toString());
		log.info(map.get("replyVo"));
		log.info(map.get("boardVo"));
		
	}
	
}