package jmp.spring.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import jmp.spring.service.AttachService;
import lombok.extern.log4j.Log4j;


@Controller
@Log4j
public class FileUploadController {
	
	@Autowired
	AttachService service;
	
	@GetMapping("/board/fileUpload")
	public void fileUploadForm() {
		
	}

	@PostMapping("/uploadFormAction")
	public void fileUpload(MultipartFile[] uploadFile, int attachNo) {

		for(MultipartFile multipartFile : uploadFile) {
			
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
			
			log.info("==============================="+multipartFile.getOriginalFilename());
			log.info("==============================="+multipartFile.getSize());
			
		}
	}
	
}
