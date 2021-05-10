package jmp.spring.mapper;

import jmp.spring.vo.User;

public interface LoginMapper { //crud create,read,delete,update
	public User login(User user);
	public int insert(User user);
	public int update(User user);
	public int delete(String id);
	
}
