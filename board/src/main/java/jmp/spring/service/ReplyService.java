package jmp.spring.service;

import java.util.List;

import jmp.spring.vo.Criteria;
import jmp.spring.vo.ReplyVo;

public interface ReplyService {
	
	public int insert(ReplyVo vo);
	public int update(ReplyVo vo);
	public int delete(int rno);
	public ReplyVo get(int rno);
	public List<ReplyVo> getList(int bno, Criteria cri);
	public int getTotal(int bno);


}
