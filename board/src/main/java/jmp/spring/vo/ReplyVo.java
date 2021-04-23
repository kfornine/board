package jmp.spring.vo;

import lombok.Data;

@Data
public class ReplyVo {

	//리플번호,게시물번호
	int rno;
	int bno;
	
	//리플,작성자,작성시간,수정시
	String reply;
	String replyer;
	String replyDate;
	String updateDate;
	
}