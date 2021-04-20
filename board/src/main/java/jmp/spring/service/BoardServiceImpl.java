package jmp.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jmp.spring.mapper.BoardMapper;
import jmp.spring.vo.BoardVo;
import jmp.spring.vo.Criteria;
@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardMapper mapper;
	
	@Override
	public List<BoardVo> getList(Criteria cri) {
		// TODO Auto-generated method stub
		return mapper.getList(cri);
	}

	@Override
	public int insertBoard(BoardVo vo) {
		// TODO Auto-generated method stub
		return mapper.insertBoard(vo);
	}

	@Override
	public BoardVo get(int bno) {
		// TODO Auto-generated method stub
		return mapper.get(bno);
	}

	@Override
	public int update(BoardVo vo) {
		return mapper.update(vo);
				
	}

	@Override
	public int delete(int bno) {
		// TODO Auto-generated method stub
		return mapper.delete(bno);
	}

	@Override
	public int getTotal(Criteria cri) {
		// TODO Auto-generated method stub
		return mapper.getTotal(cri);
	}

}
