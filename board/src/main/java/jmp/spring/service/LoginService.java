package jmp.spring.service;

import jmp.spring.vo.User;

public interface LoginService {
	public User login(User vo);
	
	public int updateSessionKey(User user);
	
	public User loginSessionKey(String sessionkey);

	public int insertUser(User user);
	
	public User searchId(User user);
	public User searchPwd(User user);
}
