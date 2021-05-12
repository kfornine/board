package jmp.spring.vo;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class User {
	
	public String id;	//id
	
	public String pwd;	//pw
	
	public String name;	//이름
	
	public String email;//이메일
	
	public String enabled;//사용여부
	
	public List<String> userRole;// 권한 사용자의 권한을 조회 하여 입력 해주세요
	
	//자동로그인 필요한키값,유효기간
	String sessionkey;
	Date sessionlimit;
	
	/**
	 * 권한이 있는지 체크 합니다.
	 * 권한을 가지고 있는지 체크 합니다. 
	 * @param role_id
	 * @return
	 */
	
	//포함되어있으면 참 거짓
	public boolean hasRole(String role_id){
		if(userRole != null) {
			return userRole.contains(role_id);
		}
		
		return false;
	}

	
}
