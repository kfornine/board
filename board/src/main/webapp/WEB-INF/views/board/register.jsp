<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
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
<form action="/board/register.jsp" method="post">
	<table border=1>
			<tr>
			<td>제목</td><td><input type="text" name="text"></td>
			</tr>
			<tr>
			<td>내용</td><td><textarea rows="5" cols="33"></textarea></td>
			</tr>
			<tr>
			<td>작성자</td><td><input type="text" name="text"></td>
			</tr>
			<tr>
			<td><input type="submit" value="제출"></td>
			</tr>
	</table>
</form>

</body>
</html>