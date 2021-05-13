package jmp.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import jmp.spring.vo.User;

public interface LoginMapper { //crud create,read,delete,update
	public User login(User user);
	public List<String> userRole(String id); //유저권한목록
	
	public int updateSessionKey(User user);
	public User loginSessionKey(String sessionkey);
	
	public int insertUser(User user);
	public int insertUserRole(@Param("id")String id, @Param("role")String role);
	
	/*
	 * 자동 로그인을 위한 키값과 유효기간을 저장합니다
	 * -로그인 사용자의 아이디
	 * -세션 ID
	 */
	
	
}
