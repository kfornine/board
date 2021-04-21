package jmp.spring.vo;

import java.util.Date;

import lombok.Data;

@Data
public class ReplyVo {

	int rno;
	int bno;
	
	String reply;
	String replyer;
	Date replyDate;
	Date updateDate;
	
	
	
}
