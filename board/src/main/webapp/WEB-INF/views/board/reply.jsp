<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/resources/header/header.jsp"/>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

<script src="/resources/js/reply.js" type="text/javascript"></script>

<script type="text/javascript">
//리플작성 AjaxInsert() 서버통신, getAjaxList
$(document).ready(
function(){
	
	//addReplyBtn을 클릭하면 모델창을 보여줌
	$("#addReplyBtn").on("click",function(){
		$("#replyInsertBtn").show();
		$("#reply").val("");
		$("#replyer").val("");
		$("#myModal").modal("show");
	});
	
	$("#modifyBtn").on("click",function(){
		updateAjax();
		
	});
	
	$("#removeBtn").on("click",function(){
		deleteAjax();
		
	});

	// 선택자
	// 아이디 : #, class :. , 태그
	
	//저장버튼을 클릭하면 저장하고 모달창을 닫아준다
	$("#replyInsertBtn").on("click",function(){
		//리플작성
		AjaxInsert();
		
		//리스트 조회하기(콜백 후에(비동기시라서))
	});
	getAjaxList()});
/*
 * 리플 상세화면을 보여준다
 */
function replyDetail(rno){
	$("#rno").val(rno);
	//버튼 숨김 처리
	$("#replyInsertBtn").hide();
	//모달창 보여주기
	$("#myModal").modal("show");
	//상세내용조회
	getAjax(); //ajax실행
}

function replyPage(pageNavi){
	
	var startPage = pageNavi.startPage;
	var endPage = pageNavi.endPage;
	var pageContent = "";
	
	// 이전페이지, 다음페이지
	//pageNavi.prev;
	//pageNavi.next;
	
	//이전  페이지네비게이션으로 이동
	if(pageNavi.prev){
		pageContent +=
			'<li class="page-item disabled">'
		   +'<a class="page-link" href="#" tabindex="-1">Previous</a>'
		   +'</li>';
	}
	
	for(startPage; startPage<endPage; startPage++){
		pageContent += '<li class="page-item"><a class="page-link" href="#">'+startPage+'</a></li>'
	}
	//다음 페이지네비게이션으로 이동
	if(pageNavi.next){
		
	}
	$(".pagination").html(pageContent);
}
	


</script>



        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Tables</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            
                            
                            
                           <!-- 답글 -->
                           <div class='row'>

							  <div class="col-lg-12">
							
							    <!-- /.panel -->
							    <div class="panel panel-default">
							      
							      <div class="panel-heading">
							        <i class="fa fa-comments fa-fw"></i> Reply
							        <button id='addReplyBtn' class='btn btn-primary btn-xs pull-right'>New Reply</button>
							      </div>      
							      
							      
							      <!-- /.panel-heading -->
							      <div class="panel-body">        
							      
							        <ul class="chat">
										<li class='left clearfix' data-rno='"+list.rno+"'>
										<div>
											<div class='header'><strong class='primary-font'>[1] 홍길동</strong> 
				    							<small class='pull-right text-muted'>12:00:00</small>
				    						</div>
				     						<p>수고가 많으십니다!</p>
				     					</div>
				     					</li>
							        </ul>
							        <!-- ./ end ul -->
							      </div>
							      <!-- /.panel .chat-panel -->
							
								<div class="panel-footer">
								
									<!-- 페이징  -->
									<nav aria-label="...">
									<ul class="pagination">
									</ul>
									</nav>
								
								</div>
							
							bno<input type="text" value="2" id="bno" ><br>
							rno<input type="text" id="rno"><br>
							pageNo<input type="text" id="pageNo" value="1">
									</div>
							  </div>
							  <!-- ./ end row -->
							  
							  
							  
							</div>
                            
                            
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            
        </div>
        <!-- /#page-wrapper -->
			
			
			
        <!-- 모달 Modal -->
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                   <div class="modal-dialog">
                       <div class="modal-content">
                           <div class="modal-header">
                               <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                               <h4 class="modal-title" id="myModalLabel">Reply</h4>
                           </div>
                           <div class="modal-body">
                                 <ul class="list-group list-group-flush">
							    <li class="list-group-item">
									<input type="text" class="form-control ml-2" placeholder="replyer" id="replyer">
								</li>
								<li class="list-group-item">
									<textarea class="form-control" id="reply" placeholder="reply" rows="3"></textarea>
							    </li>
							</ul>
                           </div>
                           <div class="modal-footer">
                               <button type="button" class="btn btn-warning" data-dismiss="modal" id="modifyBtn">Modify</button>
                               <button type="button" class="btn btn-danger" data-dismiss="modal" id="removeBtn">Remove</button>
                               <button type="button" class="btn btn-default" data-dismiss="modal">cancle</button>
                               <button type="button" class="btn btn-primary" id="replyInsertBtn">save</button>
                           </div>
                       </div>
                       <!-- /.modal-content -->
            </div>
            <!-- /.modal-dialog -->
        </div>
        <!-- /.modal -->
                

        
<jsp:include page="/resources/header/bottom.jsp"/>


