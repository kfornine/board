<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/resources/header/header.jsp"/>

<script type="text/javascript">
if('${resMsg }' != ""){
	alert('${resMsg }');
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
                            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
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
                                        <td><a href="/board/get?bno=${list.bno}"> ${list.title}</a></td>
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
							    <li class="page-item disabled">
							    <c:if test="${pageNavi.prev}">
							      <a class="page-link" href="/board/list?pageNo=${pageNavi.startPage-1}">Previous</a></c:if>
							    </li>
							    <li class="page-item"><a class="page-link" href="#">1</a></li>
							    <li class="page-item active" aria-current="page">
							      <a class="page-link" href="#">2</a>
							    </li>
							    <li class="page-item"><a class="page-link" href="#">3</a></li>
							    <li class="page-item">
							    <c:if test="${pageNavi.next}"> 
							     <a class="page-link" href="/board/list?pageNo=${pageNavi.startPage+11}">Next</a></c:if>
							    </li>
							  </ul>
							</nav>
                            
                            
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