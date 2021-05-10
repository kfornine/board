package jmp.spring.service;

import org.springframework.beans.factory.annotation.Autowired;

import jmp.spring.mapper.LoginMapper;
import jmp.spring.vo.User;

public class LoginServiceImpl implements LoginService{

	@Autowired
	LoginMapper mapper;
	
	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		return mapper.login(user);
	}

	@Override
	public int insert(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
