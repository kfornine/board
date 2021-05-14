package jmp.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jmp.spring.mapper.IdSearchMapper;

@Service
public class IdSearchServiceImpl implements IdSearchService {

	@Autowired
	IdSearchMapper mapper;
	
	// 아이디 찾기
	public String searchId(String user_name, String user_email) {

		String result = "";

		try {
			result = mapper.searchId(user_name, user_email);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
}
