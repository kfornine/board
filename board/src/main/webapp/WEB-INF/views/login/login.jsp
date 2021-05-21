<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href=/resources_ljk/css/style.css">
    <script src="js/jquery-3.6.0.min.js"></script>
    <script src="js/snowfall.jquery.js"></script>
</head>
<body>
	<section class="login-form">
		<h1>Virtual Air Travel</h1>
		<form action="">
			<div class="int-area">
				<input type="text" name="id" id="id"
				autocomplete="off" required="required">
				<label for="id">USER NAME</label>				
            </div>
			<div class="int-area">
				<input type="text" name="pw" id="pw"
				autocomplete="off" required="required">
				<label for="pw">PASSWORD</label>				
            </div>
            <div class="btn-area">
                <button id="btn" type="submit">LOGIN</button>
            </div>
        </form>
        <div class="caption">
            <p><a href="" id="f1">Forgot ID?&nbsp;</a>
            <span>|</span>
            <a href="" id="f2">Forgot Password?&nbsp;</a>
            <span>|</span>
            <a href="register.html" id="f3">회원가입하기</a></p>
        </div>

        <div class="tpa">
            <a href="#" id="btnLogin1">
            <img src="image/naver.jpg" width="55" height="55" alt="naver"></a>
            <a href="#" id="btnLogin2">
            <img src="image/goggle.jpg" width="55" height="55" alt="goggle"></a>
            <a href="#" id="btnLogin3">
            <img src="image/apple.jpg" width="55" height="55" alt="apple"></a>
            <a href="#" id="btnLogin4">
            <img src="image/facebok.jpg" width="55" height="55" alt="facebok"></a>

        </div>


	</section>
<script>
    let id = $('#id');
    let pw = $('#pw');
    let btn = $('#btn');

    $(btn).on('click',function(){
        if($(id).val()==""){
            $(id).next('label').addClass('warning');
            setTimeout(function(){
                $('label').removeClass('warning')
            },1500)
        }
        else if($(pw).val()==""){
            $(pw).next('label').addClass('warning');
            setTimeout(function(){
                $('label').removeClass('warning')
            },1500)
        }
    });

    $(document).snowfall({round : true, maxSize : 3});

</script>



</body>
</html>