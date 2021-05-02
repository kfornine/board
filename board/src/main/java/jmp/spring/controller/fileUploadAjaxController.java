package jmp.spring.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;

@Log4j
@RestController
public class fileUploadAjaxController {

	
	@PostMapping("/fileUploadAjax")
	public void fileUpload(MultipartFile[] uploadFile) {
		
		for(MultipartFile multipartFile : uploadFile) {

			//파일생성
			File saveFile = new File(multipartFile.getOriginalFilename());
			try {
				
				multipartFile.transferTo(saveFile);
				
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			log.info("======================="+multipartFile.getOriginalFilename());
			log.info("======================="+multipartFile.getName());
			log.info("======================="+multipartFile.getSize());
			
		}
	}
	
}
