package jmp.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import jmp.spring.vo.Criteria;
import jmp.spring.vo.ReplyVo;

public interface ReplyMapper {
	
	public int insert(ReplyVo vo);
	public int update(ReplyVo vo);
	public int delete(int rno);
	public ReplyVo get(int rno);
	//파라메터가 2개이상인 경우 @Param을 꼭 써줘야함
	public List<ReplyVo> getList(@Param("bno") int bno,
								 @Param("cri") Criteria cri);//파람이 여러개일경우 이름을 넣어줌
	public int getTotal(int bno);
	//리플 업데이트
	public int updateReplyCnt(int bno);

}
