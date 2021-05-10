package jmp.spring.vo;

import lombok.Data;

@Data
public class BoardVo {

	int bno; //번호
	String title; //제목
	String content; //내용
	String writer; //작성자
	String regdate; //작성일
	String updatedate; //수정일
	String replycnt; //리플 수

	//첨부파일번호 추가
	String attachNo;
}
