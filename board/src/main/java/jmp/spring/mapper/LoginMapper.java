package jmp.spring.mapper;

import java.util.List;

import jmp.spring.vo.User;

public interface LoginMapper { //crud create,read,delete,update
	public User login(User user);
	public List<String> userRole(String id); //유저권한목록
	public int updateSessionKey(User user);
	public User loginSessionKey(String sessionkey);
	
//	public int insert(User user);
//	public int update(User user);
//	public int delete(String id);
	
}
