/**
 *  ajax
 * 	비동기식 - 따로따로
 * 	자료를 받아와서 처리하던가,
 * 	자료를 보내서 처리함
 */

//ajax이용한 리플 업데이트
function updateAjax(){
	console.log("updateAjax 시작");
	
	// 입력할 파라메터를 javascript Object로 만들어줌
	var updateData = {
			bno : $("#bno").val(),
			reply : $("#reply").val(),
			replyer : $("#replyer").val(),
			rno : $("#rno").val()
	};
	
	$.ajax({
		url : '/reply/update',
		method : 'post',
		dataType : 'json',
		
		data : JSON.stringify(updateData),
		contentType : 'application/json; charset=UTF-8',
		
		success : function(data, status){
			console.log(data);
			if(data.result == "success"){
				//모달창 닫기
				$("#myModal").modal("hide");
				//리스트조회하기
				getAjaxList();
			}else{
				alert("입력중 오류가 발생했습니다");
			}
		},
		error : function(xhr, status, error){
			console.log(error);
		}
		
	});
}



//ajax이용한 삭제
function deleteAjax(){
	console.log("deleteAjax 시작");
	$.ajax({
		url : '/reply/delete/' +$("#rno").val(),
		method :'get',
		dataType :'json',
		success :function(data){
			console.log(data);
			//리플리스트 조회
			getAjaxList();
			
		},
		error :function(error){
			console.log(error);
		}
		
	});
}



//ajax이용한 리스트
function getAjaxList(){
	
	$.ajax({
		url : '/reply/list/' + $("#bno").val()+'/'+$("#replyPageNo").val(), //input에 id를 가져옴
		method : 'get',
		dataType : 'json', //내가받을형식
		success : function(data, status, xhr){
			console.log("실행결과",data);
			//debugger;
			var htmlContent="";
			
			if(data.list.length == 0){
				
				htmlContent += 
				"<li class='left clearfix'>"
				+	"<div>"
				+	"<p> 등록된 댓글이 없습니다 </p>"
				+	"</div>"
				+	"</li>";
				$(".chat").html(htmlContent); //엘리먼츠의 내용을 바꿔줌
				
			}else{
				$.each(data.list,function(index, item){ //item이름으로 정보 뺴오기, map에서 data.list사용
					htmlContent += 
						"<li onclick=replyDetail('"+ item.rno +"') class='left clearfix' data-rno=''>"
						+"<div>"
						+	"<div class='header'><strong class='primary-font'>["+ item.rno +"] "+ item.replyer +"</strong>"
						+		"<small class='pull-right text-muted'>"+ item.updateDate +"</small>"
						+	"</div>"
						+		"<p>"+ item.reply +"</p>"
						+	"</div>"
						+	"</li>";
				});
			}
			
			
			$(".chat").html(htmlContent); //엘리먼츠의 내용을 바꿔줌
			
			//페이지 보여주기
			replyPage(data.pageNavi);
			
		},
		error : function(xhr, status, error){
			console.log("error",error);
		}		
		
	});
	
}

/*
 * 리플 넣기
 * 
 */

//ajax이용한 삽입
function AjaxInsert(){
	
	// 입력할 파라메터를 javascript Object로 만들어줌
	var replyData = {
			bno : $("#bno").val(),
			reply : $("#reply").val(),
			replyer : $("#replyer").val()
	};
	
	console.log("obj", replyData);
	console.log("json", JSON.stringify(replyData));
	
	//***서버 접속 url***
	$.ajax({
		url : '/reply/insert',
		method : 'post',
		dataType : 'json',
		//JSON 형식으로 변환
		data : JSON.stringify(replyData),//내가 줄형식
		contentType : 'application/json; charset=UTF-8',
		
		success : function(data, status){
			console.log(data);
			
			if(data.result == "success"){
				//모달창 닫기
				$("#myModal").modal("hide");
				//리스트조회하기
				$("#replyPageNo").val("1");
				getAjaxList();
			}else{
				alert("입력중 오류가 발생했습니다");
			}
		},
		error : function(xhr, status, error){
			console.log(error);
		}
		
	});
	
}
/*
 * 
 * 1건의 리플을 조회
 * @returns
 * 
 */

//ajax이용한 상세보기
function getAjax(){
	$.ajax({
		url : '/reply/get/' + $("#rno").val(),
		method : 'get',
		dataType : 'json',
		
		success : function(data, status) {
			console.log(data);
			
			$("#reply").val(data.reply);
			$("#replyer").val(data.replyer);
		},
		error : function(xhr, status, error) {
			console.log(error);
		}
		
	});
}


//함수로 빼기(매기변수를 만들어)
function commAjax(url, method, data, callback, error){
	$.ajax({
		url : url,
		method : method,
		dataType : 'json',
		//JSON 형식으로 변환
		data : JSON.stringify(data),//내가 줄형식
		contentType : 'application/json; charset=UTF-8',
		
		success : function(data, status){

			if(callback){
				callback(data);
			}
			
		},
		error : function(xhr, status, error){
			console.log(error);
			
			if(error){
				callback(errorThrown);
			}
		}
		
	});
}