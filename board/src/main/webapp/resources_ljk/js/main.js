/*콜백 함수*/
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

function checkId() {
    var idPattern = /[a-zA-Z0-9_-]{5,20}/;
    if(id.value === "") {
        error[0].innerHTML = "필수 정보입니다.";
        error[0].style.display = "block";
    } else if(!idPattern.test(id.value)) {
        error[0].innerHTML = "5~20자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.";
        error[0].style.display = "block";
    } else {
        error[0].innerHTML = "멋진 아이디네요!";
        error[0].style.color = "#08A600";
        error[0].style.display = "block";
    }
}

function checkPw() {
    var pwPattern = /[a-zA-Z0-9~!@#$%^&*()_+|<>?:{}]{8,16}/;
    if(pw1.value === "") {
        error[1].innerHTML = "필수 정보입니다.";
        pwMsg.style.display = "block";
        pwMsgArea.style.paddingRight = "40px";
        pwImg1.src = "m_icon_pass.png";
        error[1].style.display = "block";
    } else if(!pwPattern.test(pw1.value)) {
        error[1].innerHTML = "8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.";
        pwMsg.innerHTML = "사용불가";
        pwMsgArea.style.paddingRight = "93px";
        error[1].style.display = "block";
        pwMsg.style.color = "red";
        pwMsg.style.display = "block";
        pwImg1.src = "m_icon_not_use.png";
    } else {
        error[1].style.display = "none";
        pwMsg.innerHTML = "안전";
        pwMsgArea.style.paddingRight = "93px";
        pwMsg.style.color = "#03c75a";
        pwMsg.style.display = "block";
        pwImg1.src = "m_icon_safe.png";
    }
}

function comparePw() {
    if(pw2.value === pw1.value) {
        pwImg2.src = "m_icon_check_enable.png";
        error[2].style.display = "none";
    } else if(pw2.value !== pw1.value) {
        pwImg2.src = "m_icon_check_disable.png";
        error[2].innerHTML = "비밀번호가 일치하지 않습니다.";
        error[2].style.display = "block";
    } 

    if(pw2.value === "") {
        error[2].innerHTML = "필수 정보입니다.";
        error[2].style.display = "block";
    }
}

function checkName() {
    var namePattern = /[a-zA-Z가-힣]/;
    if(userName.value === "") {
        error[3].innerHTML = "필수 정보입니다.";
        error[3].style.display = "block";
    } else if(!namePattern.test(userName.value) || userName.value.indexOf(" ") > -1) {
        error[3].innerHTML = "한글과 영문 대 소문자를 사용하세요. (특수기호, 공백 사용 불가)";
        error[3].style.display = "block";
    } else {
        error[3].style.display = "none";
    }
}


function isBirthCompleted() {
    var yearPattern = /[0-9]{4}/;

    if(!yearPattern.test(yy.value)) {
        error[4].innerHTML = "태어난 년도 4자리를 정확하게 입력하세요.";
        error[4].style.display = "block";
    } else {
        isMonthSelected();
    }


    function isMonthSelected() {
        if(mm.value === "월") {
            error[4].innerHTML = "태어난 월을 선택하세요.";
        } else {
            isDateCompleted();
        }
    }

    function isDateCompleted() {
        if(dd.value === "") {
            error[4].innerHTML = "태어난 일(날짜) 2자리를 정확하게 입력하세요.";
        } else {
            isBirthRight();
        }
    }
}



function isBirthRight() {
    var datePattern = /\d{1,2}/;
    if(!datePattern.test(dd.value) || Number(dd.value)<1 || Number(dd.value)>31) {
        error[4].innerHTML = "생년월일을 다시 확인해주세요.";
    } else {
        checkAge();
    }
}

function checkAge() {
    if(Number(yy.value) < 1920) {
        error[4].innerHTML = "정말이세요?";
    } else if(Number(yy.value) > 2019) {
        error[4].innerHTML = "미래에서 오셨군요. ^^";
    } else if(Number(yy.value) > 2005) {
        error[4].innerHTML = "만 14세 미만의 어린이는 보호자 동의가 필요합니다.";
    } else {
        error[4].style.display = "none";
    }
}


function isEmailCorrect() {
    var emailPattern = /[a-z0-9]{2,}@[a-z0-9-]{2,}\.[a-z0-9]{2,}/;

    if(email.value === ""){ 
        error[6].style.display = "none"; 
    } else if(!emailPattern.test(email.value)) {
        error[6].style.display = "block";
    } else {
        error[6].style.display = "none"; 
    }

}

function checkPhoneNum() {
    var isPhoneNum = /([01]{2,})([01679]{1,})([0-9]{3,4})([0-9]{4})/;

    if(mobile.value === "") {
        error[7].innerHTML = "필수 정보입니다.";
        error[7].style.display = "block";
    } else if(!isPhoneNum.test(mobile.value)) {
        error[7].innerHTML = "형식에 맞지 않는 번호입니다.";
        error[7].style.display = "block";
    } else {
        error[7].style.display = "none";
    }

    
}
//idcheck.java 서블렛으로 이동하여 usable가져오기
//컨트롤러 /idcheck 로 이동해서 작성
function idCheck() {
    var t_id = $("#t_id").val(); //입력상자의 id값	
    
    if(t_id==""){
        alert("ID를 입력해주세요");
        return false;
    }
    $.ajax({
        type:"post",
        dataType:"text",
        async:false,
        url: "http://localhost:8083/jweb/idcheck",
        data: {id: t_id}, //id(t_id)를 서블렛에 보냄
        success: function(data, textStatus){
            //코딩
            if(data == 'usable'){
                alert('사용할수 있는 아이디입니다');
                
            }else{
                alert('사용불가');
            }
        },
        error: function(){
            alert("에러 발생");
        },
    }); //ajax 닫기
}//idCheck() 닫기