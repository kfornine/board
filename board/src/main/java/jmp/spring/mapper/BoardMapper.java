package jmp.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;


import jmp.spring.vo.BoardVo;
import jmp.spring.vo.Criteria;
public interface BoardMapper {
	
	@Select("select sysdate from dual")
	public String getTime();
	
	public String getTime2();
	
	public List<BoardVo> getList(Criteria cri);
	
	public int insertBoard(BoardVo vo);
	
	public BoardVo get(int bno);
	
	public int update(BoardVo vo);
	
	public int delete(int bno);
	
	public int getTotal(Criteria cri);
}
