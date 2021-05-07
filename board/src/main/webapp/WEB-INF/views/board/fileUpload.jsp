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
		//attachNo이 바뀌는순간 getFileList()실행
		$("#attachNo").on("change",function(){
			getFileList($("#attachNo").val());
		});
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
					$("#attachNo").val(result.attachNo);
					$("#uploadFile").val("");
					//document.uploadForm.uploadFile.value="";
					//파일 저장후 리스트를 호출 합니다.
					getFileList(result.attachNo);
				},
				error:function(){
					console.log("error");
				}
			});//ajax
		});//uploadbtn.on
	});//document.ready
	
	
	function getFileList(attachNo){
		//서버에 다녀와
		$.ajax({
			url : '/getFileList/'+ attachNo,
			method : 'get',
			dataType : 'json',
			
			success :function(result){
				//result2 : List<attachFileVo>
				var htmlContent = "";
				$.each(result, function(index, vo){
					//encodeURIComponent(문자열)
					//savePath = 
					var s_savePath = encodeURIComponent(vo.s_savePath);
					var savePath = encodeURIComponent(vo.savePath);
					console.log("인코딩전",vo.savePath);
					console.log("인코딩후",savePath);
					//만약에 이미지이면 이미지를 보여주고
					if(vo.fileType == "Y"){
						htmlContent += "<li><a href=/download?fileName="+savePath+">" 
									+ "<img src=/display?fileName="+s_savePath+">" 
									+ vo.fileName + "</a>"
									+ "<span onClick = attachFileDelete2("+vo.uuid+", "+vo.attachNo+") data-type='image'> x </span>"
									+ "</li>";
					}else{
					//이미지가 아니면 파일 이름을 출력
						htmlContent += "<li><a href=/download?fileName=" + savePath + ">" 
								    + vo.fileName + "</a></li>";
					}
				});
				$("#fileListView").html(htmlContent);
				console.log("result결과",result);
			}
			
		});//ajax
	}
	//파일 삭제
	function attachFileDelete(uuid, attachNo){
		console.log("func",attachFileDelete);
		$.ajax({
			url : '/attachFileDelete2/'+uuid+'/'+attachNo,
			method:'get',
			
			success:function(res){
				console.log("Res",res);
				//파일 저장후 리스트를 호출 합니다.
				getFileList(attachNo);
			},
			error:function(){
				console.log("error");
			}
		});//ajax
	}
	
	function attachFileDelete2(uuid, attachNo){
		console.log("func","attachFileDelete2");
		console.log("uuid",uuid);
		console.log("attachNo",attachNo);
		
		$.ajax({
			url: 'attachFileDelete/'+uuid+'/'+attachNo,
			method: 'get',
			success: function(result){
				console.log(result);
				//파일 저장후 리스트를 호출 합니다.
				getFileList(attachNo);
			} ,
			error: function(){
				console.log("error");
			} 
			
		});
	}
</script>
<style>
.uploadResult {
	width: 100%;
	background-color: gray;
}

.uploadResult ul {
	display: flex;
	flex-flow: row;
	justify-content: center;
	align-items: center;
}

.uploadResult ul li {
	list-style: none;
	padding: 10px;
}

.uploadResult ul li img {
	width: 100px;
}
</style>

</head>
<body>

<form action="/uploadFormAction" method="post" 
	enctype="multipart/form-data" name="uploadForm">

attachNo<input type="text" name="attachNo" id="attachNo" value="0">
<input type="file" name="uploadFile" multiple>
<button type="button" id="uploadBtn">전송</button>

<!-- 파일 리스트를 출력 합니다. -->
<div class="uploadResult">
	<ul id="fileListView">
		<li>fileName1</li>
	</ul>
</div>

</form>



</body>
</html>