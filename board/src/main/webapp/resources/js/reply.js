/**
 *  ajax
 * 
 */
function getAjaxList(){
	
	$.ajax({
		url : '/reply/list/' + $("#bno").val(),
		method : 'get',
		dataType : 'json', //내가받을형식
		success : function(data, status, xhr){
			console.log("data",data);
			var htmlContent="";
			
			$.each(data,function(index, item){ //item이름으로 정보 뺴오기
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
			
			
			$(".chat").html(htmlContent); //엘리먼츠의 내용을 바꿔줌
			
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
function AjaxInsert(){
	
	// javascript Object
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
			console.log(data);
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