package jmp.spring.board;

import org.junit.Test;

import jmp.spring.vo.Criteria;
import jmp.spring.vo.PageNavi;
import lombok.extern.log4j.Log4j;

@Log4j
public class PageNaviTest {

	
	@Test
	public void pageNavi() {
		Criteria cri = new Criteria();
		cri.setPageNo(5);
		
		PageNavi navi = new PageNavi(cri,150);
		
		log.info(navi);
	}
}
