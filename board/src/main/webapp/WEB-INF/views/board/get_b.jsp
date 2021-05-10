<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/resources/header/header.jsp"/>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">

if('${resMsg }' != ""){
	alert('${resMsg }');
}

//상세 보기 이동
function detailBtn(url) {
	document.detailForm.action=url;
	document.detailForm.submit();
	
	//console.log("log",detailBtn);
}

$(document).ready(function(){
	if('${vo.attachNo}' != ''){ //첨부파일이있으면
	getFileList('${vo.attachNo}');
	$("input[name=attachNo]").val('${vo.attachNo}');
	}
	$("#fileInput").remove();
	
});
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
                        <div class="panel-heading">
                            DataTables Advanced Tables
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                              <div class="form-group">
                                  <label>제목</label>
                                  <input readonly class="form-control" value="${vo.title }">
                              </div>
                                <div class="form-group">
                                  <label>내용</label>
                                  <textarea readonly class="form-control" rows="3">${vo.content }</textarea>
                              </div>
                                <div class="form-group">
                                  <label>작성자</label>
                                  <textarea readonly class="form-control" rows="3">${vo.writer }</textarea>
                              </div>
                                <div class="form-group">
                                  <label>등록시간</label>
                                  <textarea readonly class="form-control" rows="3">${vo.regdate }</textarea>
                              </div>
                              
                               	<button type="button" class="btn btn-default" onClick="detailBtn('/board/edit')"<%-- onClick="location.href='/board/edit?bno=${vo.bno }'" --%>>수정</button>
								<button type="button" class="btn btn-default" onClick="detailBtn('/board/delete')">삭제</button>
								<button type="button" class="btn btn-default" onClick="detailBtn('/board/list')">목록</button>
								<input readonly class="form-control" value="${vo.attachNo }">
								<form method="get" name="detailForm">
									<input type="hidden" name="bno" value="${vo.bno}">
									<input type="hidden" name="pageNo" value="${criteria.pageNo}">
									<input type="hidden" name="type" value="${criteria.type}">
									<input type="hidden" name="keyword" value="${criteria.keyword}">
								</form>
									<!-- 첨푸파일 -->
									<jsp:include page="fileUpload.jsp"></jsp:include>
								    <!-- 댓글  -->
									<jsp:include page="reply.jsp"/>
								
								
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->

