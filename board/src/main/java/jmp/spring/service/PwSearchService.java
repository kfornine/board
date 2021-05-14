package jmp.spring.service;

import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import jmp.spring.vo.User;

public class PwSearchService {
// 패스워드 찾기 이메일 발송
	public void mailSendWithPassword(String user_id, String user_email, HttpServletRequest request) {
		// 비밀번호는 6자리로 보내고 데이터베이스 비밀번호를 바꿔준다
//		String key = getKey(false, 6);
//		userDao = sqlSession.getMapper(UserDaoInterface.class);
//		
//		// 회원 이름 꺼내는 코드
//		User vo = userDao.userInfo(user_id);
//		String name = vo.getName();
//				
//		MimeMessage mail = mailSender.createMimeMessage();
//		String htmlStr = "<h2>안녕하세요 '"+ name +"' 님</h2><br><br>" 
//				+ "<p>비밀번호 찾기를 신청해주셔서 임시 비밀번호를 발급해드렸습니다.</p>"
//				+ "<p>임시로 발급 드린 비밀번호는 <h2 style='color : blue'>'" + key +"'</h2>이며 로그인 후 마이페이지에서 비밀번호를 변경해주시면 됩니다.</p><br>"
//				+ "<h3><a href='http://54.180.123.73/MS/'>MS :p 홈페이지 접속 ^0^</a></h3><br><br>"
//				+ "(혹시 잘못 전달된 메일이라면 이 이메일을 무시하셔도 됩니다)";
//		try {
//			mail.setSubject("[MS :p] 임시 비밀번호가 발급되었습니다", "utf-8");
//			mail.setText(htmlStr, "utf-8", "html");
//			mail.addRecipient(RecipientType.TO, new InternetAddress(user_email));
//			mailSender.send(mail);
//		} catch (MessagingException e) { 
//			e.printStackTrace();
//		}
//		// 비밀번호 암호화해주는 메서드
//		key = UserSha256.encrypt(key);
//		// 데이터 베이스 값은 암호한 값으로 저장시킨다.
//		userDao.searchPassword(user_id, user_email, key);
		
	}
}
