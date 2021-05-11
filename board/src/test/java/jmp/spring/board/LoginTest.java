package jmp.spring.board;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import jmp.spring.mapper.LoginMapper;
import jmp.spring.service.LoginService;
import jmp.spring.vo.User;
import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class LoginTest {
	
	@Autowired
	LoginMapper mapper;
	
	@Autowired
	LoginService service;
	
	@Test
	public void userTest() {
		User user = new User();
		user.setId("user01");
		user.setPwd("1234");
		log.info("\n\n\n\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"+mapper.login(user));
	}
	
	@Test
	public void userRoleTest() {
		System.out.println(mapper.userRole("user01"));
	}
}
