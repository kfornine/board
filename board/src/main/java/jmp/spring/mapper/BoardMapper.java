package jmp.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import jmp.spring.vo.BoardVo;

public interface BoardMapper {
	
	@Select("select sysdate from dual")
	public String getTime();
	
	public String getTime2();
	
	//리스트 페이지
	public List<BoardVo> getList();
	
	//인서트 페이지 /업데이트 인서트는 인트를 반환받는다
	public int insertBoard(BoardVo vo);
}
