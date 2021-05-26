package jmp.spring.service;

import java.util.List;

import jmp.spring.vo.Menu;
import jmp.spring.vo.User;

public interface LoginService {
	public User login(User vo);
	
	public int updateSessionKey(User user);
	
	public User loginSessionKey(String sessionkey);

	public User checkId(String id);
	
	public int insertUser(User user);
	
	public User searchId(User user);
	public User searchPwd(User user);
	
	public List<Menu> menu();

	public String searchId2(User user);

	public User searchPwd2(User user);

	public int updatePwd2(User user);
}
