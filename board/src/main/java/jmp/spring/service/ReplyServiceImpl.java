package jmp.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jmp.spring.mapper.ReplyMapper;
import jmp.spring.vo.Criteria;
import jmp.spring.vo.ReplyVo;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	ReplyMapper mapper;
	
	@Override
	@Transactional //트렌지터(거래) 설정
	public int insert(ReplyVo vo) {
		// TODO Auto-generated method stub
		int res = mapper.insert(vo);
		//댓글의 갯수는 댓글을 입력하거나,삭제할때 변경되기때문에 여기에 추가
		//댓글의 갯수를 카운트해서 tbl_board 테이블의 replycnt 컬럼에 입력 update
		//갯수가 변경되는 게시글의 번호는 vo.getBno();
		mapper.updateReplyCnt(vo.getBno());
		return res;
	}

	@Override
	public int update(ReplyVo vo) {
		// TODO Auto-generated method stub
		int res = mapper.update(vo);
		mapper.updateReplyCnt(vo.getBno());
		return res;
	}

	@Override
	public int delete(int rno) {
		// TODO Auto-generated method stub
		ReplyVo vo = mapper.get(rno);
		int res = mapper.delete(rno);
		//댓글 삭제시 댓글수가 변경됨 -> 댓글수를 다시카운트
		//rno로 댓글 상세정보 조회
		//상세정보의 bno값에 해당하는 리플의 수를 카운트해서 등록(댓글수업데이트)
		mapper.updateReplyCnt(vo.getBno());
		return res;
	}

	@Override
	public ReplyVo get(int rno) {	//댓글 상세보기
		// TODO Auto-generated method stub
		return mapper.get(rno);
	}

	@Override
	public List<ReplyVo> getList(int bno, Criteria cri) {
		// TODO Auto-generated method stub
		return mapper.getList(bno, cri);
	}

	@Override
	public int getTotal(int bno) {
		// TODO Auto-generated method stub
		return mapper.getTotal(bno);
	}

}
