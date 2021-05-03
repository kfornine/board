package jmp.spring.vo;

import lombok.Data;

@Data
public class AttachFileVo {

	//첨부파일 번호,중복방지,업로드경로,파일명,이미지구분(이미지면t아니면f)
	  private int attachNo;
	  private String uuid;
	  private String uploadPath;
	  private String fileName; 
	  private String fileType;
	  private String regdate;
	  //,썸네일전체경로,파일전체경로
	  private String savePath;
	  private String s_savePath;
	  
}
