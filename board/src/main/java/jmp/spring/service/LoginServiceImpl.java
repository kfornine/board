package jmp.spring.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jmp.spring.mapper.LoginMapper;
import jmp.spring.vo.Menu;
import jmp.spring.vo.User;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	LoginMapper mapper;
	
	@Override
	public User login(User vo) {
		// 로그인
		User loginUser = mapper.login(vo);
		// 로그인 성공시 유저의 권한을 조회 해줍니다
		if(loginUser != null) {
			//로그인 유저가 있다면  TODO:비밀번호를 비교하는 로직추가
			//만약 비밀번호가 일치하면 권한 조회해서 유저객체 반환
			//비밀번호가 틀릴경우 ,USER객체 NULL로 반환
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			
			//화면으로부터 입력받은 유저와 로그인한 유저 비교 거짓이면 null반환
			if(!encoder.matches(vo.getPwd(), loginUser.getPwd())){
				return null;
			}
			
			//로그인 유저의 권한을 리스트로조회
			List<String> role = mapper.userRole(loginUser.getId());
			//유저객체리스트에 담아줍니다
			loginUser.setUserRole(role);
		}
		return loginUser;
	}
	
	@Override
	public int updateSessionKey(User user) {
		// TODO Auto-generated method stub
		return mapper.updateSessionKey(user);
	}

	@Override
	public User loginSessionKey(String sessionkey) {
		// TODO Auto-generated method stub
		User user = loginSessionKey(sessionkey);
		List<String> role = mapper.userRole(user.getId());
		user.setUserRole(role);
		return user;
	}

	@Override
	public User checkId(String id) {
		return mapper.checkId(id);
	}
	
	@Override
	/**
	 * 사용자권한 추가 
	 */
	public int insertUser(User user) {
		// TODO: 비밀번호 암호화 작업이 추가
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		//비밀번호 암호화
		String encodePwd = encoder.encode(user.getPwd());
		user.setPwd(encodePwd);
		
		//사용자 추가
		int res = mapper.insertUser(user);
		//권한 추가
		if(res>0) {
			res = mapper.insertUserRole(user.getId(), "ROLE_USER");
		}
		return res;
	}
	
	public User searchId(User user) {
		return mapper.searchId(user);
	}

	@Override
	public User searchPwd(User user) {
		return mapper.searchPwd(user);
	}

	@Override
	public List<Menu> menu() {
		// 메뉴 리스트 가져오기
		return mapper.menu();
	}



}
