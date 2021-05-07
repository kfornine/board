package jmp.spring.board;

import java.util.Properties;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import jmp.spring.service.MailService;
import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class mailServiceTest {
	
	@Autowired
	Properties prop;
	
	@Autowired
	MailService ms;
	
	@org.junit.Test
	public void Test2() {
		
		log.info("+++++++++++++++++++++++++++++++++++++++++"+prop);
		log.info(prop.getProperty("mail.id"));
		log.info(prop.getProperty("mail.pw"));
		ms.welcomeMailSend();
		
//		MailService ms = new MailService();
//		log.info("---------------");
//		//메일 전송 테스트
//		ms.welcomeMailSend();
//		log.info("---------------");
	}
}
