<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h1>
	게시판
</h1>
<table border=1>
	<c:forEach items="${list}" var="list">
	<tr>
		<td><c:out value="${list.bno}"/></td>
		<td><c:out value="${list.title}"/></td>
		<td><c:out value="${list.writer}"/></td>
		<td><c:out value="${list.regdate}"/></td>
	</tr>
	</c:forEach>
</table>

</body>
</html>