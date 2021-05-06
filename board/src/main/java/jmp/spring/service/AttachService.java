package jmp.spring.service;

import java.util.List;

import jmp.spring.vo.AttachFileVo;

public interface AttachService {
	
	public int getSeq();
	public int insert(AttachFileVo vo);
	public List<AttachFileVo> getList(int attachNo);
	
	public int delete(String uuid, int attachNo);
	
	public AttachFileVo get(String uuid, int attachNo);

}


