<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/resources/header/header.jsp"/>


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
                <form action="/board/register" method="post">
                    <div class="panel panel-default">
                    
                    
                        <div class="panel-heading">
                            DataTables Advanced Tables
                        </div>
                        <!-- /.panel-heading -->
                        
                        <div class="panel-body">
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
                               	<button type="submit">등록</button>
                               
                               	</form>
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