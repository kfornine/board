package jmp.spring.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jmp.spring.service.AttachService;
import jmp.spring.vo.AttachFileVo;
import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnails;

@Log4j
@RestController
public class fileUploadAjaxController {

	//기본 경로
	private static final String ROOT_DIR = "C:\\upload\\";
	
	@Autowired
	AttachService service;
	
	@PostMapping("/fileUploadAjax")
	public List<AttachFileVo> fileUpload(MultipartFile[] uploadFile, int attachNo) {
		
		//신규 생성된 파일일 경우 첨부번호 생성
		if(attachNo==0) {
			attachNo = service.getSeq();
		}
		

		for(MultipartFile multipartFile : uploadFile) {
			
			//중복 방지를 위해
			//UUID 범용고유식별자 36개의 문자를 생성
			UUID uuid = UUID.randomUUID();
			
			String uploadPath = getFolder();
			
			String uploadFileName = uuid.toString() + "_" 
									+ multipartFile.getOriginalFilename();
			
			//파일생성
			File saveFile = new File(ROOT_DIR+uploadPath+uploadFileName);
			try {
				//파일을 서버에 저장합니다.
				multipartFile.transferTo(saveFile);
				//확장자를 이용하여 MimeType을 결정합니다
				//마임타입을 확인하지 못하면 null을 반환합니다
				String contentType = Files.probeContentType(saveFile.toPath());
				
				if(contentType.startsWith("image")) {
					String thumbnail =ROOT_DIR+ uploadPath+"s_"+uploadFileName;
					//썸네일 이미지 생성
					Thumbnails.of(saveFile).size(100, 100).toFile(thumbnail);
				}
				
				//파일정보를 db에 저장
				AttachFileVo vo = new AttachFileVo();
				vo.setUuid(uuid.toString());
				vo.setAttachNo(attachNo);
				vo.setFileName(multipartFile.getOriginalFilename());
				vo.setFileType(contentType.startsWith("image")?"Y":"N");
				vo.setUploadPath(uploadPath);
				
				service.insert(vo);
				
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
		//화면에 뿌리기
		List<AttachFileVo> list = service.getList(attachNo);
		return list;
		
	}
	private String getFolder() {
		String uploadPath = "";
		//오늘 날짜를 yyyy-mm-dd에 맞게 가져옴
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str = sdf.format(new Date());
		
		//플렛폼에서 사용하는 구분자를 리턴
		uploadPath = str.replace("-", File.separator) + File.separator;
		
		File saveFile = new File(ROOT_DIR + uploadPath);
		if(!saveFile.exists()) {//파일이 있으면 폴더 만들어줌 메이킹 디렉토리
			saveFile.mkdirs();
		}
		
		return uploadPath;
	}

	
}

