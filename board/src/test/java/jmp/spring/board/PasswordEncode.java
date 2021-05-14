package jmp.spring.board;

import java.util.UUID;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncode {
	
	
	@Test
	public void uuidTest() {
		//비밀번호 찾기 -> 임시 비밀번호를 이메일로 전달합니다
		//임시 비밀번호의 생성
		System.out.println(UUID.randomUUID().toString().substring(0,7));
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		//임시번호 인코딩
		encoder.encode(UUID.randomUUID().toString().substring(0,7));
		
	}
	
	
	
	
	@Test
	public void testgo() {
		//스프링에서 제공하는 암호화
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		//4567 암호화
		System.out.println("\n\n문자열\n\n"+encoder.encode("4567"));
		
		//기존의 입력된 값과 일치하는지 확인
		System.out.println(encoder.matches("1234", encoder.encode("1234")));
		

	}

}
