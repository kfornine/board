package jmp.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jmp.spring.mapper.ReplyMapper;
import jmp.spring.vo.ReplyVo;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	ReplyMapper mapper;
	
	@Override
	public int insert(ReplyVo vo) {
		// TODO Auto-generated method stub
		return mapper.insert(vo);
	}

	@Override
	public int update(ReplyVo vo) {
		// TODO Auto-generated method stub
		return mapper.update(vo);
	}

	@Override
	public int delete(int rno) {
		// TODO Auto-generated method stub
		return mapper.delete(rno);
	}

	@Override
	public ReplyVo get(int rno) {
		// TODO Auto-generated method stub
		return mapper.get(rno);
	}

	@Override
	public List<ReplyVo> getList(int bno) {
		// TODO Auto-generated method stub
		return mapper.getList(bno);
	}

}
