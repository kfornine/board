package jmp.spring.mapper;

import org.apache.ibatis.annotations.Param;


public interface IdSearchMapper {
	
	public String searchId(@Param("user_name")String user_name
								, @Param("user_phone")String user_email);
	
	

	
	
	
}
