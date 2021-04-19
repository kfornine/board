package jmp.spring.vo;

import lombok.Data;

@Data
public class Criteria {
	int pageNo;
	int amount;
	
	public Criteria() { //초기화 1,10으로
		this.pageNo = 1;
		this.amount = 10;
	}
	
	
}
