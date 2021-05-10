<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	if('${vo.attachNo}' != ""){
	getFileList('${vo.attachNo}');
	$("input[name=attachNo]").val('${vo.attachNo}');
	}
});

</script>


<jsp:include page="/resources/header/header.jsp"/>

	<input type=hidden name=bno value=${vo.bno }>
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
	                        <form action="/board/edit" method="post">
								<input type="hidden" name="pageNo" value="${criteria.pageNo}">
								<input type="hidden" name="type" value="${criteria.type}">
								<input type="hidden" name="keyword" value="${criteria.keyword}">
	                              <div class="form-group">
	                                  <label>제목</label>
	                                  <input name=title class="form-control" value="${vo.title }">
	                              </div>
	                                <div class="form-group">
	                                  <label>내용</label>
	                                  <textarea name=content class="form-control" rows="3">${vo.content }</textarea>
	                              </div>
	                                <div class="form-group">
	                                  <label>작성자</label>
	                                  <textarea name=writer class="form-control" rows="3">${vo.writer }</textarea>
	                              </div>
	                              	<input type="submit" value="수정">
	                               	<!--<button type="submit">수정</button>  -->
							</form>
                               	<jsp:include page="fileUpload.jsp"></jsp:include>
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

<jsp:include page="/resources/header/bottom.jsp"/>