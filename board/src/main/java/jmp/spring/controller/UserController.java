package jmp.spring.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import jmp.spring.service.LoginService;
import jmp.spring.service.MailService;
import jmp.spring.vo.User;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class UserController {

	@Autowired
	LoginService service;
	
	@Autowired
	MailService email;
	
	@Autowired
	MailSender sender;
	
	@PostMapping("/searchId")
	@ResponseBody
	public Map<String, String> serchId(@RequestBody User user) { //화면에서 넘오면 유저, 맵으로 반환
		String id = service.searchId2(user); //id찾기 서비스
		Map<String, String> res = new HashMap<String, String>();
		if(StringUtils.isEmpty(id)) { //id가 있으면
			res.put("msg", "이름/이메일에 일치하는 아이디가 없습니다");
		}else {
			res.put("msg", "아이디는" + id + "입니다");
		}
		return res; //맵<"msg","값"> 보내줌
		
	}
	
	@PostMapping("/searchPwd")
	@ResponseBody  //url반환이 아닌 객체를 반환 (rest컨트롤러형식, url에 정보를 저장해서보내는것이 아니라 있는 페이지에 객체(맵,또는 String등등)를 전해줌)
	public Map<String, String> searchPwd(@RequestBody User vo) { //화면에서 넘어오는값 가져오기
		Map<String, String> res = new HashMap<String, String>();

		//유저 가져오기(url에선 new로 생성)
		User user = service.searchPwd2(vo);
		
		if(user != null) {
			//유저가 있으면 비밀번호 업데이트
			String pwd = UUID.randomUUID().toString().substring(0, 7);
			user.setPwd(pwd);
			//비밀번호 업데이트
			int updateRes = service.updatePwd2(user);
			//비밀번호 업데이트 결과 확인
			if(updateRes>0) {
				//업데이트가 있으면 이메일 전송(pwd,유저이메일을 매개변수로)하고 boolean반환
				if(email.sendPwd(pwd, user.getEmail())) {
					res.put("msg", "임시비밀번호를 메일로 전송했습니다");
				}else {
					res.put("msg", "메일전송에 실패했습니다. 관리자에게 문의해주세요");
				}
			}
		}else {
			//사용자 없음
			res.put("msg", "일치하는사용자가없습니다");
		}
		
		return res;
	}
	
	//맵핑가서 처리후 다른사이트를  비밀번호입력
	@RequestMapping("/mail")
	public String sendMail(User user, Model model) {
	  	String uuid = null;
		 for (int i = 0; i < 5; i++) {
		        uuid = UUID.randomUUID().toString().replaceAll("-", ""); // -를 제거해 주었다.
		        uuid = uuid.substring(0, 10); //uuid를 앞에서부터 10자리 잘라줌.
		  }
		User us = service.searchPwd(user);
		
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo("leehjcap1@gmail.com"); //보내는 경로
		msg.setSubject("비밀번호확인해주세요");
		msg.setText("임시비밀번호는"+uuid+"기존비밀번호"+us.getPwd());
		//비밀번호 업데이트 서비스
		msg.setFrom("leehjcap4@gmail.com");
		sender.send(msg);
		return "find_pwd";
	}
	
	// 아이디 찾기
	@RequestMapping(value = "/find_id_form", method = RequestMethod.GET)
	public String find_id_form() throws Exception{
		return "/find_id_form";
	}
	
	//아이디 찾기 결과
	@RequestMapping(value = "/find_id", method = RequestMethod.POST)
	public String find_id(User user, Model model) throws Exception{
		User us = service.searchId(user);
		model.addAttribute("id", us.getId());
		return "find_id";
	}
	
	// 비번 찾기
	@RequestMapping(value = "/find_pwd_form", method = RequestMethod.GET)
	public String find_pwd_form() throws Exception{
		return "/find_pwd_form";
	}
	
	//비번 찾기 결과
	@RequestMapping(value = "/find_pwd", method = RequestMethod.POST)
	public String find_pwd(User user, Model model) throws Exception{
		User us = service.searchPwd(user);
		if(service.searchPwd(user)==null) {
			return "error";
		}
		model.addAttribute("pwd", us.getPwd());
		
		//email.welcomeMailSend();
		return "find_pwd";
	}
	
	//비밀번호 업데이트
	@RequestMapping(value="update_password", method=RequestMethod.POST)
	public String updatePasswordAction(@RequestParam(value="updateid", defaultValue="", required=false) String id,
										User user) {
		user.setId(id);
		//service.updatePassword(user);
		return "findPasswordConfirm";
	}
	
	
	@GetMapping("/userSearch")
	public void userSearchh() {
		
	}
	
	
	@GetMapping("/login")
	public void loginn() {
		
	}
	@GetMapping("/login4")
	public void loginn4() {
		
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.invalidate();
		
		//자동로그인 쿠키를 제거 해줍시다
		//로그아웃을 하게 되면 더이상 자동로그인을 할 수 없습니다
		Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
		
		//널처리 , 로그인 쿠키가 있을때만 처리(없으면 실행안하고 login불러옴), 쿠키 기간0으로
		if(loginCookie != null) {
			loginCookie.setMaxAge(0);
			loginCookie.setPath("/");
			
			response.addCookie(loginCookie);
		}
		
		return "/login";
	}
	
	@PostMapping("/loginProcess") //폼 action경로
	public String loginProcesss(User vo, Model model, HttpServletRequest request) {
		
		User user = service.login(vo);
		
		//메뉴 생성 서비스
		
		if(user == null) {
			model.addAttribute("msg","로그인에 실패하였습니다 Id,Pwd를 확인하세요");
			return "/login";
			
		}else {
			// user 객체를 세션에 담아줌 - 로그인 처리
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			
			log.info("\n\n\n\n\n\n"+user);
			
			model.addAttribute("msg",user.getId()+"님 로그인에 성공하였습니다");
			return "/loginProcess";
		}
		
	}
	
	@GetMapping("/member")
	public void member() {
		
	}
	@PostMapping("/registerMember")
	public String registerMember(User user) {
		//회원가입 처리
		try {
			int res = service.insertUser(user);
			if(res>0) {
				return "forward:/loginProcess";
			}else {
				return "/error";
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			return "/error";
		}
	}
	
	@GetMapping("/checkId/{id}")
	@ResponseBody
	public boolean checkId(@PathVariable("id") String id) {
		//아이디 중복 체크
		if(service.checkId(id) != null) {
			//아이디 있음
			return false;
		}else{
			//아이디 없음
			return true;
		}
	}
}
