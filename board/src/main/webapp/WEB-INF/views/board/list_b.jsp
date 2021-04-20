<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/resources/header/header.jsp"/>

<script type="text/javascript">
if('${resMsg }' != ""){
	alert('${resMsg }');
}

//내가 선택한 페이지로 이동
function page(page) {

	document.listForm.action="/board/list";
	document.listForm.pageNo.value=page;
	document.listForm.submit();
	
	//location.href = "/board/list?pageNo="+page;
}

//상세 보기 이동
function detail(bno) {
	document.listForm.action="/board/get";
	document.listForm.bno.value=bno;
	document.listForm.submit();
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
                        <div class="panel-heading">
                             <button type="button" onclick="location.href='/board/register'" class="btn btn-default">등록</button>
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>번호</th>
                                        <th>제목</th>
                                        <th>작성자</th>
                                        <th>작성시간</th>
                                    </tr>
                                </thead>
                                <tbody>
                             	   <c:forEach items="${list}" var="list">
                                    <tr class="odd gradeX">
                                        <td>${list.bno }</td>
                                        <td onclick="detail(${list.bno})"><a href="#">${list.title}</a></td>
                                        <td>${list.writer}</td>
                                        <td class="center">${list.regdate}"</td>
                                    </tr>
                                    </c:forEach>
                                    
                                </tbody>
                            </table>
                            <!-- /.table-responsive -->
                            <c:if test="${pageNavi.prev}">
                            <a href = /board/list?pageNo=${pageNavi.startPage-1}> 이전 </a></c:if>
                            <c:forEach var="num" begin="${pageNavi.startPage}" end="${pageNavi.endPage }" >
                            ${num}</c:forEach>
                            <c:if test="${pageNavi.next}"> 
                            <a href = /board/list?pageNo=${pageNavi.endPage+1}>다음 </a></c:if>
                            
                            <nav aria-label="...">
							  <ul class="pagination">
							  
							  	<c:if test="${pageNavi.prev}">
							    <li class="page-item" onclick="javascript:page(${pageNavi.startPage-1})">
							      <a class="page-link"  href="/board/list?pageNo=${pageNavi.startPage-1}">Previous</a>
							    </li>
							    </c:if>
							    <!--choose(switch),when(case),otherwise(default)  -->
							    <c:forEach begin="${pageNavi.startPage}" end="${pageNavi.endPage }" var="page">
								    <c:choose>  
									    <c:when test="${page eq pageNavi.cri.pageNo }">
									    <li class="page-item active" onclick="page(${page})" aria-current="page">
									      <a class="page-link" href="#" >${page }<span class="sr-only"></span></a>
									    </li>
									    </c:when>
										    <c:otherwise>
										  	  <li class="page-item" onclick="page(${page})"><a class="page-link" href="#">${page }</a></li>
										    </c:otherwise>
								    </c:choose>
							    </c:forEach>
							    
							    <c:if test="${pageNavi.next}"> 
							    <li class="page-item">
							     <a class="page-link" href="/board/list?pageNo=${pageNavi.endPage+1}">Next</a>
							    </li>
							    </c:if>
							  </ul>
							</nav>
							<!-- 페이지끝 -->
							
							<!-- 검색 -->
							<form method="get" action="/board/list" name="listForm">
	                            <!-- 상세보기 검색 유지용 -->
	                            <input type="text" name="bno">
	                            <input type="text" name="pageNo" value="${pageNavi.cri.pageNo }">
	                            <!-- 상세보기 검색 유지용 끝 -->
								<div class="form-inline">
	                            <select class="form-control" name="type">
	                                <option value="title">제목</option>
	                                <option value="content">내용</option>
	                                <option value="writer">작성자</option>
	                            </select>
	                            
	                            <input class="form-control" name="keyword" value="${pageNavi.cri.keyword }">
	                            <button type="submit" class="btn btn-default">검색</button>
	                            </div>
                            </form>
                            <!-- 검색 끝 -->
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