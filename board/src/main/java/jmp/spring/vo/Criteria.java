package jmp.spring.vo;

import lombok.Data;

@Data
public class Criteria {
	//페이지번호, 페이지당 게시물수
	int pageNo;
	int amount;
	
	//검색타입
	String type;
	//검색 키워드
	String keyword;
	
	public Criteria() { //초기화 1,10으로
		this.pageNo = 1;
		this.amount = 10;
	}
	
	
}
