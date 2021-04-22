<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

<script type="text/javascript">

//페이지 초기화작업
$(document).ready(function(){
	getAjaxList();
	
	// id가 getListBtn인 엘리멘탈에 이벤트를 걸어줌
	$("#getListBtn").on("click", function(){
		console.log("test");
		getAjaxList();
		
	});
});

function getAjaxList(){
	
	$.ajax({
		//서버 접속 url 1->bno
		url : '/reply/list/1',
		methord : 'get',
		dataType : 'json',
		// 처리 성공
		success : function(data, textStatus, jqXHR){
			console.log("data"+data);
			
			console.log($("#repleTbl").html());
			
			var tblContent = "";
			
			$.each(data, function(index, item){
				tblContent +="<tr><td>" + item.reply + "</td><td>" + item.replyer + "</td></tr>";
			}); //가상함수
			
			
			$("#repleTbl").html(tblContent);
			
			//console.log("textStatus", textStatus);
			//console.log("jqXHR", jqXHR);
			
			// 콜백함수가 있으면 콜백함수 실행
			if(callback){
				callback(data);
			}
			
		},
		// 처리 실패
		error : function(jqXHR, textStatus, errorThrown){
			console.log("error!");
			console.log("errorThrown", errorThrown);
			console.log("textStatus", textStatus);
			console.log("jqXHR", jqXHR);
			
			// 콜백함수가 있으면 콜백함수 실행
			if(error){
				error(errorThrown);
			}
			
		}
		
	});
	
	
}

</script>
</head>
<body>
<button id="getListBtn">리스트</button>
<br>
<table border="1" id="repleTbl">
<tr><td>리플</td><td>작성자</td></tr>
</table>


</body>
</html>