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
	public void checkId() {
		System.out.println(mapper.checkId("user01"));
	}
	
	
	@Test
	public void logintest() {
		User vo = new User();
		vo.setId("user01");
		service.login(vo);
	}
	
	@Test
	public void userslogintet() {
		User user = new User();
		user.setId("1");
		user.setPwd("2");
		user.setEmail("3.@naver.com");
		user.setName("33");
		
		mapper.insertUser(user);
		String role = "ROLE_USER";
		mapper.insertUserRole("22", role);
		
	}
	
	
	@Test
	public void loginservie() {
		System.out.println(mapper.loginSessionKey("C8D29AD8FA72BCE2791FDEFB984365CE"));
	}
	
	
	@Test
	public void userUpdateSessionKey() {
		User user = new User();
		user.setSessionkey("user01_sessionkey01");
		user.setId("user01");
		
		System.out.println(mapper.updateSessionKey(user)); 
	}
	
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
