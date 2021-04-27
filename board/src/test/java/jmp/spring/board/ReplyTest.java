package jmp.spring.board;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import jmp.spring.mapper.ReplyMapper;
import jmp.spring.service.ReplyService;
import jmp.spring.vo.Criteria;
import jmp.spring.vo.ReplyVo;
import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class ReplyTest {
	
	@Autowired
	ReplyMapper mapper;

	@Autowired
	ReplyService service;
	
	@Test
	public void getTotalTest() {
		int res = mapper.getTotal(2);
		log.info(res);
	}
	
	@Test
	public void test1() {
		log.info(service.get(1));
		
	}
	
	@Test
	public void test2() {
		Criteria cri = new Criteria();
		cri.setPageNo(1);
		cri.setAmount(10);
		log.info(service.getList(2, cri));
		
	}
	
	@Test
	public void test4() {
		ReplyVo vo = new ReplyVo();
		vo.setBno(1);
		vo.setReply("222");
		vo.setReplyer("222");
		int res = service.insert(vo);
		log.info(res);
		
	}
	
	@Test
	public void test3() {
		ReplyVo vo = new ReplyVo();
		vo.setRno(1);
		vo.setReply("333");
		vo.setReplyer("333");
		int res = service.update(vo);
		log.info(res);
	}
	
	
	@Test
	public void test5() {
		int res = service.delete(1);
		log.info(res);
	}
	
}
