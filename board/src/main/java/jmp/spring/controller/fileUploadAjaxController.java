package jmp.spring.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	//private static final String ROOT_DIR = "D:\\sys\\spwork\\upload\\";
	
	@Autowired
	AttachService service;
	
	@GetMapping("/attachFileDelete/{uuid}/{attachNo}")
	public String deletee(@PathVariable("uuid") String uuid,
						@PathVariable("attachNo") int attachNo) {
		log.info("uuid : " + uuid);
		log.info("attachNo : " + attachNo);
		//uuid, attachNO
		AttachFileVo vo = service.get(uuid, attachNo);
		log.info("Vo : " + vo);
		//서버에 저장된 파일을 삭제합니다
		File file = new File(ROOT_DIR+vo.getSavePath());
		//파일이있으면 지움
		if(file.exists()) {
			file.delete();
		}
		//만약에 이미지이면
		//서버에 저장된 이미지 파일의 썸내일도 삭제
		if(vo.getFileType() == "Y") {
			File sFile = new File(ROOT_DIR+vo.getS_savePath());
			//파일이있으면 지움
			if(sFile.exists()) {
				sFile.delete();
			}
		}
		int res = service.delete(uuid, attachNo);
		return res>0?"success":"fail";
	}
	
	@GetMapping("/download")
	public ResponseEntity<byte[]> download(String fileName) {
		log.info("/display==================== fileName: " + fileName);
		
		// file 경로 2021/05/05/파일이름
		// urlPath + uuid + _ + 파일이름 = savePath
		File file = new File (ROOT_DIR + fileName);
		if(file.exists()) {
			try {
				//파일을 ResponseEntity에 담아서 반환
				HttpHeaders headers = new HttpHeaders();
				headers.add("Content-type", MediaType.APPLICATION_STREAM_JSON_VALUE);
				headers.add("Content-Disposition", "attachment;filename=\"" 
													+ new String(fileName.getBytes("UTF-8"),"ISO-8859-1")+"\"");
				return new ResponseEntity<>(
						FileCopyUtils.copyToByteArray(file), 
						headers, HttpStatus.OK);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
			}
			
		}else {
			//파일 업음 처리
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		}
	}
	
	/*
	 * 이미지 파일의 경로를 조회하여
	 * 이미지 파일 반환 합니다
	 * @param fileName
	 * 
	 */
	@GetMapping("/display")
	public ResponseEntity<byte[]> display(String fileName) {
		log.info("/display++++++++++++++++++++++++++++++fileName"+fileName);
		// /display?fileName=다운로드.jpg
		File file = new File(ROOT_DIR+fileName);
		
		//파일 있는지 확인 exists=존재(T/F)
		if(file.exists()) {
			try {
				HttpHeaders headers = new HttpHeaders();
				headers.add("Content-Type", Files.probeContentType(file.toPath()));
				
				//responseEntity객체에 파일과 헤더를 담아서 브라우져로 리턴
				return new ResponseEntity<byte[]>(
						FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK);
				
			} catch (IOException e) {
				return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
				// TODO Auto-generated catch block
			}
		}else {
			return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
		}
	}
	/*
	 * 파일리스트를 조회합니다
	 * @return List<AttachFileVo>
	 * 
	 */
	@GetMapping("/getFileList/{attachNo}")
	public List<AttachFileVo> getList(@PathVariable("attachNo") int attachNo){
		return service.getList(attachNo);
	}
	
	@PostMapping("/fileUploadAjax")
	public Map<String, String> fileUpload(MultipartFile[] uploadFile, int attachNo) {
		
		// 신규 생성 이면 sequence값을 가져 옵니다.
		if(attachNo==0) {
			attachNo = service.getSeq();
		}
		
		int res = 0;
		for(MultipartFile file : uploadFile) {
			
			try {
				AttachFileVo vo = 
						new AttachFileVo(   attachNo, 
											getFolder(), // 업로드 경로 지정 ( 년\월\일 )
											file.getOriginalFilename());
				
				log.info("=======================" + vo);
				// 파일을 서버에 저장 해봅시다
				File saveFile = new File(ROOT_DIR+vo.getSavePath());
				
				// 서버에 파일을 생성 합니다.
				file.transferTo(saveFile);
				
				// Mime 타입을 스트링으로 받아옵니다.
				// Myme 타입을 모를경우 null을 반환 합니다. ex) xx.sql
				String contentType = Files.probeContentType(saveFile.toPath());
				log.info("==============contentType : "+contentType);
				if(contentType != null 
						&& contentType.startsWith("image")) {
					Thumbnails.of(saveFile).size(100, 100).toFile(ROOT_DIR+vo.getS_savePath());
					vo.setFileType("Y");
				}

				// 파일 정보를 DB에 저장 합니다.
				if(service.insert(vo)>0) {
					res++;
				}
				
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("attachNo", attachNo+"");
		map.put("result", res+"건 저장 되었습니다.");
		
		return map;
	}
//	@GetMapping("/attachFileDelete2/{uuid}/{attachNo}")
//	public String deletee2(@PathVariable("uuid") String uuid,
//						 @PathVariable("aatachNo") int attachNo) {
//		
//		
//		AttachFileVo vo = service.get(uuid, attachNo);
//		File file = new File(ROOT_DIR + vo.getSavePath());
//		if(file.exists()) {
//			//파일 삭제
//			file.delete();
//		}
//		//db에서 삭제
//		int res = service.delete(uuid, attachNo);
//		return res > 0 ? "success" : "fail";
//	}
//	
//	@GetMapping("/download2")
//	public ResponseEntity<byte[]> download2(String fileName) throws IOException{
//		log.info(fileName);
//		//파일이 있는지 없는지 확인
//		File file = new File(fileName);
//		if(file.exists()) {
//			HttpHeaders headers = new HttpHeaders();
//			headers.add("Content-Type",  MediaType.APPLICATION_OCTET_STREAM_VALUE);
//			
//			headers.add("Content-Disposition", "attachment; filename=\""
//					+ new String(fileName.getBytes("UTF-8"),"ISO-8859-1")
//					+"\"");
//			return new ResponseEntity<byte[]>(
//					FileCopyUtils.copyToByteArray(file)
//					, headers
//					, HttpStatus.OK);
//		}else {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//	}
//	@PostMapping("/fileUploadAjax")
//	public Map<String, String> fileUpload(MultipartFile[] uploadFile, int attachNo) {
//		
//		Map<String, String> map = new HashMap<String, String>();
//		
//		//신규 생성된 파일일 경우 첨부번호 생성
//		if(attachNo==0) {
//			attachNo = service.getSeq();
//		}
//		
//		int res = 0;
//
//		for(MultipartFile multipartFile : uploadFile) {
//			
//			//중복 방지를 위해
//			//UUID 범용고유식별자 36개의 문자를 생성
//			UUID uuid = UUID.randomUUID();
//			
//			String uploadPath = getFolder();
//			
//			String uploadFileName = uuid.toString() + "_" 
//									+ multipartFile.getOriginalFilename();
//			
//			//파일생성
//			File saveFile = new File(ROOT_DIR+uploadPath+uploadFileName);
//			try {
//				//파일을 서버에 저장합니다.
//				multipartFile.transferTo(saveFile);
//				//확장자를 이용하여 MimeType을 결정합니다
//				//마임타입을 확인하지 못하면 null을 반환합니다
//				String contentType = Files.probeContentType(saveFile.toPath());
//				
//				if(contentType != null && 
//						contentType.startsWith("image")) {
//					String thumbnail =ROOT_DIR+ uploadPath+"s_"+uploadFileName;
//					//썸네일 이미지 생성
//					Thumbnails.of(saveFile).size(100, 100).toFile(thumbnail);
//					
//					//파일정보를 db에 저장
//					AttachFileVo vo = new AttachFileVo();
//					//AttachFileVo vo = new AttachFileVo(attachNo, getFolder(), multipartFile.getOriginalFilename());
//					vo.setUuid(uuid.toString());
//					vo.setAttachNo(attachNo);
//					vo.setFileName(multipartFile.getOriginalFilename());
//					vo.setFileType(contentType.startsWith("image")?"Y":"N");
//					vo.setUploadPath(uploadPath);
//					
//					if(service.insert(vo)>0) {
//						res++;
//					}
//				}
//				
//				
//			} catch (IllegalStateException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			log.info("======================="+multipartFile.getOriginalFilename());
//			log.info("======================="+multipartFile.getName());
//			log.info("======================="+multipartFile.getSize());
//			
//			
//		}
//		//화면에 뿌리기
//		//List<AttachFileVo> list = service.getList(attachNo);
//
//		map.put("attachNo", attachNo+"");
//		map.put("result", res+"건 생성");
//		return map;
//		
//	}
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
	}//getFolder

}

