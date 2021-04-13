package jmp.spring.board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zaxxer.hikari.HikariDataSource;

import jmp.spring.mapper.BoardMapper;
import jmp.spring.service.BoardService;
import jmp.spring.vo.BoardVo;
import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class ojdbcTest {
	
	@Autowired
	BoardService service;
	
	@Autowired
	HikariDataSource datasource;
	
	@Autowired
	BoardMapper mapper;
	
	
	/**
	 * 
	 * 작성자 :
	 * 작성일 :
	 * 반환값 :
	 */
	
	
	@Test
	public void mapperp() {
		BoardVo vo = new BoardVo();
		vo.setContent("내용-mapperTest");
		vo.setTitle("제목-mapperTest");
		vo.setWriter("작성자-mapperTest");
		
		int res = mapper.insertBoard(vo);
		
		//log.info(res);
		log.info("===================="+service.insertBoard(vo));
		
	}
	
	
	@Test
	public void service() {
		log.info("service============================" + service.getList());
		
	}
	
	@Test
	public void mapper() {
		log.info("getlist======================" + mapper.getList()); 
		log.info("mapper===================" + mapper.getTime());
		log.info("mapper===================" + mapper.getTime2());
	}
	
	@Test
	public void hikaricpTest() {
		try {
			log.info("hikari================" + datasource);
			Connection conn = datasource.getConnection();
			
			log.info(conn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	@Test
	public void ojdbcTest() {
		try {
			Connection conn = DriverManager.getConnection
					  ("jdbc:oracle:thin:@localhost:1521:xe", "spring", "spring");
			log.info(conn);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
