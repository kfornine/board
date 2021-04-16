package jmp.spring.service;

import java.util.List;

import jmp.spring.vo.BoardVo;

public interface BoardService {
	
	public List<BoardVo> getList();
	
	public int insertBoard(BoardVo vo);
	
	public BoardVo get(int bno);
	
	public int update(BoardVo vo);
	
	public int delete(int bno);

}


