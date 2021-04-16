<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
if('${resMsg }' != ""){
	alert('${resMsg }');
}
</script>
<h1>
	게시판 
</h1>
<form>
	<table border=1>
			<tr>
			<td>제목</td><td>${vo.title }</td>
			</tr>
			<tr>
			<td>내용</td><td>${vo.content }</td>
			</tr>
			<tr>
			<td>작성자</td><td>${vo.writer }</td>
			</tr>
	</table>
		<button type="button" onClick="location.href='/board/edit?bno=${vo.bno }'">수정</button>
		<button type="button" onClick="location.href='/board/delete?bno=${vo.bno }'">삭제</button>
</form>
</body>
</html>