package jmp.spring.service;

import java.util.List;

import jmp.spring.vo.BoardVo;

public interface BoardService {
	
	public List<BoardVo> getList();
	
	public int insertBoard(BoardVo vo);
}
