/** oneclick - javascript
 *  폼 유효성 검사
	1.아이디 - 4자~12자
	2.비밀번호 - 8자~20자 영문자,숫자 조합(혼합)
	3.비밀번호가 비밀번호 확인과 같지 않으면 '비밀번호를 동일하게 입력해 주세요'
	4.이름을 입력하지 않으면 '이름을 입력해 주세요'
 */
function checkMember(){
	var form = document.regForm;
	var id = form.id.value;
	var pw1 = form.pswd1.value;
	var pw2 = form.pswd2.value;
	var name = form.name.value;
	
	var regExPw = /^[a-zA-Z0-9]{8,20}$/;
	var chk_num = pw1.search(/[0-9]/g); //비밀번호와 숫자 인덱스 검색,숫자가 1개라도 있어야하고
	var chk_eng = pw1.search(/[a-zA-Z]/g); //비번 영문자의 인덱스 검색,문자가 하나라도 있어야함
	
	//i have a house. /g 2개 /i 공백까지
	
	if(id.length < 4 || id.length > 12){
		alert("아이디는 4~12자까지 가능합니다");
		form.id.select();
		return false;
	}
	if(!regExPw.test(pw1) || chk_num < 0 || chk_eng < 0){ //test() = .equals랑 비슷하고 있으면true
		alert("비밀번호는 영문자와 숫자 조합으로 8~20자까지 가능합니다 한글x");
		form.pswd1.select();
		return false;
	}
	if(pw1 != pw2){
		alert("비밀번호를 동일하게 입력해주세요")
		form.pswd2.select();
		return false;
	}
	if(name == ""){
		alert("이름을 입력해 주세요")
		form.name.focus();
		return false;
	}
	
	form.submit();
}//function