package jmp.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jmp.spring.mapper.AttachMapper;
import jmp.spring.vo.AttachFileVo;

@Service
public class AttachServiceImpl implements AttachService {

	@Autowired
	AttachMapper mapper;

	@Override
	public int getSeq() {
		// TODO Auto-generated method stub
		return mapper.getSeq();
	}

	@Override
	public int insert(AttachFileVo vo) {
		// TODO Auto-generated method stub
		return mapper.insert(vo);
	}

	@Override
	public List<AttachFileVo> getList(int attachNo) {
		// TODO Auto-generated method stub
		return mapper.getList(attachNo);
	}
	

}
