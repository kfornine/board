<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>
	게시판 
</h1>
<form action="/board/register" method="post">
	<table border=1>
			<tr>
			<td>제목</td><td><input type="text" name=title></td>
			</tr>
			<tr>
			<td>내용</td><td><textarea rows="5" cols="33" name=content></textarea></td>
			</tr>
			<tr>
			<td>작성자</td><td><input type="text" name=writer></td>
			</tr>
	</table>
		<input type="submit" value="제출">
</form>

</body>
</html>