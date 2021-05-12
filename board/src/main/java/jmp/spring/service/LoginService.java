package jmp.spring.service;

import jmp.spring.vo.User;

public interface LoginService {
	public User login(User user);
	
	public int updateSessionKey(User user);
	
	public User loginSessionKey(String sessionkey);

	
}
