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
	�Խ��� 
</h1>
<form action="/board/register" method="post">
	<table border=1>
			<tr>
			<td>����</td><td><input type="text" name="title"></td>
			</tr>
			<tr>
			<td>����</td><td><textarea rows="5" cols="33" name=content></textarea></td>
			</tr>
			<tr>
			<td>�ۼ���</td><td><input type="text" name="writer"></td>
			</tr>
	</table>
		<input type="submit" value="����">
</form>

</body>
</html>