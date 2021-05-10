package jmp.spring.vo;


import java.util.UUID;

import lombok.Data;

@Data
public class AttachFileVo {

	//첨부파일 번호,중복방지,업로드경로,파일명,이미지구분(이미지면Y아니면N)
	  private int attachNo;
	  private String uuid;
	  private String uploadPath;
	  private String fileName; 
	  private String fileType;
	  private String regdate;
	  
	  //추가
	  public AttachFileVo(int attachNo, String uploadPath, String fileName) {
			
			UUID uuid = UUID.randomUUID();
			this.uuid=uuid.toString();
			this.fileType = "N";
			
			this.attachNo = attachNo;
			this.uploadPath = uploadPath;
			this.fileName = fileName;
			this.savePath = uploadPath + uuid + "_" + fileName;
			this.s_savePath = uploadPath + "s_" + uuid + "_" + fileName;
		//,썸네일전체경로,파일전체경로
	  }
	  
	  private String savePath;
	  private String s_savePath;
	  
	  

	  
}
