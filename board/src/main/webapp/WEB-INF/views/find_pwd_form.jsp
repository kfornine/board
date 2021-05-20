<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>비밀번호 찾기 form</title>
<script type="text/javascript">
function checkMember(){
	var form = document.regform;
	var id = form.id.value;
	var name = form.name.value;
	var email = form.email.value;
	
	var regExPw = /^[a-zA-Z0-9]{8,20}$/;
	var chk_num = pw1.search(/[0-9]/g); //비밀번호와 숫자 인덱스 검색,숫자가 1개라도 있어야하고
	var chk_eng = pw1.search(/[a-zA-Z]/g); //비번 영문자의 인덱스 검색,문자가 하나라도 있어야함
	
	//i have a house. /g 2개 /i 공백까지
	
	if(id.length < 4 || id.length > 12){
		alert("아이디는 4~12자까지 가능합니다");
		form.memberId.select();
		return false;
	}
	if(!regExPw.test(pw1) || chk_num < 0 || chk_eng < 0){ //test() = .equals랑 비슷하고 있으면true
		alert("비밀번호는 영문자와 숫자 조합으로 8~20자까지 가능합니다 한글x");
		form.id.select();
		return false;
	}
	if(pw1 != pw2){
		alert("비밀번호를 동일하게 입력해주세요")
		form.name.select();
		return false;
	}
	if(name == ""){
		alert("이름을 입력해 주세요")
		form.email.focus();
		return false;
	}
	
	form.submit();
}//function
</script>
</head>
<body>
	<div class="w3-content w3-container w3-margin-top">
		<div class="w3-container w3-card-4">
			
			
			<form action="/mail" method="post" name=regform>
				<div class="w3-center w3-large w3-margin-top">
					<h3>비밀번호 찾기 form</h3>
				</div>
				<div>
					<p>
						<label>아이디</label>
						<input class="w3-input" type="text" id="id" name="id" required>
						<label>이름</label>
						<input class="w3-input" type="text" id="name" name="name" required>
						<label>Email</label>
						<input class="w3-input" type="email" id="email" name="email" required>
					</p>
					<p class="w3-center">
						<button type="button" onclick="checkMember()" id=findBtn class="w3-button w3-block w3-black w3-ripple w3-margin-top w3-round">find</button>
						<button type="button" onclick="history.go(-1);" class="w3-button w3-block w3-black w3-ripple w3-margin-top w3-margin-bottom w3-round">Cancel</button>
					</p>
				</div>
			</form>
		</div>
	</div>
</body>
</html>