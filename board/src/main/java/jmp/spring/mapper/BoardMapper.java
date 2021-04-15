package jmp.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import jmp.spring.vo.BoardVo;

public interface BoardMapper {
	
	@Select("select sysdate from dual")
	public String getTime();
	
	public String getTime2();
	
	//����Ʈ ������
	public List<BoardVo> getList();
	
	//�μ�Ʈ ������ /������Ʈ �μ�Ʈ�� ��Ʈ�� ��ȯ�޴´�
	public int insertBoard(BoardVo vo);
}
