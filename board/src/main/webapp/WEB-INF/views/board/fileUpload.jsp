<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script type="text/javascript">
	//화면이 준비되면 실행합니다
	$(document).ready(function(){
		
		//id가 uploadBtn인 엘리먼트에 클릭 이벤트를 부여 합니다.
		$("#uploadBtn").on("click",function(){
			//formData 생성
			var formData = new FormData(document.uploadForm);
			console.log(formData.get("attachNo"));
			
			$.ajax({
				url: '/fileUploadAjax',
				method:'post',
				dataType : 'json',
				//processData속성과 contentType속성은
				processData:false,
				contentType:false,
				data:formData,
				success:function(result){
					console.log("callback result : ",result);
				},
				error:function(){
					console.log("error");
				}
			})
		});
	});
</script>
</head>
<body>

<form action="/uploadFormAction" method="post" 
	enctype="multipart/form-data" name="uploadForm">

attachNo<input type="text" name="attachNo" value="0">
<input type="file" name="uploadFile" multiple>
<button type="button" id="uploadBtn">전송</button>

</form>



</body>
</html>