package jmp.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jmp.spring.mapper.LoginMapper;
import jmp.spring.vo.User;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	LoginMapper mapper;
	
	@Override
	public User login(User user) {
		// 로그인
		User loginUser = mapper.login(user);
		// 로그인 성공시 유저의 권한을 조회 해줍니다
		if(loginUser != null) {
			//로그인 유저의 권한을 리스트로조회
			List<String> role = mapper.userRole(loginUser.getId());
			//유저객체리스트에 담아줍니다
			loginUser.setUserRole(role);
		}
		return loginUser;
	}
	
	@Override
	public int updateSessionKey(User user) {
		// TODO Auto-generated method stub
		return mapper.updateSessionKey(user);
	}

	@Override
	public User loginSessionKey(String sessionkey) {
		// TODO Auto-generated method stub
		User user = loginSessionKey(sessionkey);
		List<String> role = mapper.userRole(user.getId());
		user.setUserRole(role);
		return user;
	}
	




}
