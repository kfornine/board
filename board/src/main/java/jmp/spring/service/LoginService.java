package jmp.spring.service;

import jmp.spring.vo.User;

public interface LoginService {
	public User login(User user);
	public int insert(User user);
	public int update(User user);
	public int delete(String id);
}
