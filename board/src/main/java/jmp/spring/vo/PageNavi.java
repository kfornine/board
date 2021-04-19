package jmp.spring.vo;

import lombok.Data;

@Data
public class PageNavi {

	int startPage;
	int endPage;
	boolean prev;
	boolean next;
	
	//페이지 구하는 생성자,endPage,startpage를 세팅함
	public PageNavi(Criteria cri, int Total) {
		endPage = (int)Math.ceil((cri.getPageNo()/10.0))*10;
		startPage = endPage-9;
		
		int realEndPage = (int)Math.ceil((Total*1.0)/cri.getAmount());
		
		//전페이지로이동은 1보다 클떄나와야함
		prev = startPage > 1 ? true : false;
		next = realEndPage>endPage ? true : false;

		//끝페이지가 진짜페이지보다 크면 진짜페이지로 해줌
		endPage = endPage>realEndPage ? realEndPage : endPage;
	}
	
	
}
