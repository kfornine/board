package jmp.spring.board;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import jmp.spring.mapper.AttachMapper;
import jmp.spring.service.AttachService;
import jmp.spring.vo.AttachFileVo;
import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class AttachFileTest {
	
	@Autowired
	AttachMapper mapper;

	@Autowired
	AttachService service;
	
	@Test
	public void getseq() {
		log.info("++++++++++++++++++++++++++++++++"+service.getSeq());
	}
	
//	@Test
//	public void insert() {
//		AttachFileVo vo = new AttachFileVo();
//		vo.setAttachNo(1);
//		vo.setFileName("ddd");
//		vo.setFileType("N");
//		vo.setUploadPath("up");
//		vo.setUuid("0001");
//		service.insert(vo);
//	}
	
	@Test
	public void getlist() {
		log.info(mapper.getList(1));
	}
	
	
	
}
