<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin 2 - Bootstrap Admin Theme</title>

    <!-- Bootstrap Core CSS -->
    <link href="/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="/resources/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="/resources/dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="/resources/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#searchId").hide();
		$("#pwdSearch").hide();
		//$("#errorMsgArea").text('${msg}');
		
		//버튼 클릭
		$("#btnSearchId").on("click",function(){
			console.log("btnSearchId","click");
			
			// 폼의 이름과 이메일을 idVo(객체)에 넣어줌 - 자바스크립트 클래스만들기
			var idVo = {
				name : $("#name").val(),
				email : $("#email").val()
				
			};
			console.log("idVo",idVo);
			console.log("idVo(json)",JSON.stringify(idVo));

			//ajax
            $.ajax({
                url: '/searchId',
                method: 'post',
                dataType: 'json',
                
                data:JSON.stringify(idVo),
                contentType: "application/json; charset=UTF-8",
                success : function(result){ //콜백함수, 클릭했을때작동
                	console.log(result);
                    $("#errorMsgArea").html(result.msg); //맵에들어있는 msg
                },
                error: function(){
                    cosole.log("btnSearchId","ajax error");
                }
            });
			
			
		});
		//버튼 클릭
		$("#btnSearchPwd").on("click",function(){
			console.log("btnSearchPwd","click");
			var pwdVo = {
				id:$("#searchPwd_id").val(),
				email:$("#searchPwd_email").val()
			};
			
			//ajax
            $.ajax({
                url: '/searchPwd',
                method: 'post',
                dataType: 'json',
                
                data:JSON.stringify(pwdVo),
                contentType: "application/json; charset=UTF-8",
                success : function(result){
                	console.log(result);
                    $("#errorMsgArea").html(result.msg); //맵에들어있는 msg
                },
                error: function(error){
                    cosole.log(error);
                }
            }); //ajax

		}); //버튼클릭
		
	}); //문서준비

	function viewSearchId(){
		console.log("viewSearchId","실행");
		$("#login").hide();
		$("#searchId").show();
		$("#pwdSearch").hide();

	}
	
	function viewpwdSearch(){
		console.log("viewpwdSearch","실행");
		$("#login").hide();
		$("#pwdSearch").show();
		$("#searchId").hide();

	}
	
	function viewLogin(){
		console.log("viewLogin","실행");
		$("#login").show();
		$("#pwdSearch").hide();
		$("#searchId").hide();

	}
	
</script>
</head>

<body>

    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title"><a href="/login4">Please Sign In 로그인</a></h3>
                    </div>
                    <div class="panel-body">
                        <form role="form" action="/loginProcess" method="post">
                            <fieldset id="login">
                                <div class="form-group">
                                    <input class="form-control" placeholder="id" name="id" autofocus value="user01">
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Password" name="pwd" type="password" value="1234">
                                </div>
                                <div class="checkbox">
                                    <label>
                                        <input name="useCookie" type="checkbox" value="Remember Me">Remember Me
                                    </label>
                                </div>
                                <!-- Change this to a button or input when using this as a form -->
                                <button type="submit" class="btn btn-lg btn-success btn-block">Login</button>
                                
                            </fieldset>
                            <p id="errorMsgArea"></p>
                            <fieldset id="searchId">
                                <div class="form-group">
                                	<p id="errorMsgArea"></p>
                                    <input class="form-control" placeholder="name" name="name" id="name" autofocus value="일번">
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="email" name="email" id="email" value="1@naver.com">
                                </div>
                                <!-- Change this to a button or input when using this as a form -->
                                <button type="button" id="btnSearchId" class="btn btn-lg btn-success btn-block">아이디찾기</button>
                                
                            </fieldset>
                            <fieldset id="pwdSearch">
                                <div class="form-group">
                                	<p id="errorMsgArea"></p>
                                    <input class="form-control" placeholder="id" name="searchPwd_id" id="searchPwd_id" autofocus value="user01">
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="email" name="searchPwd_email" id="searchPwd_email" value="1@naver.com">
                                </div>
                                <!-- Change this to a button or input when using this as a form -->
                                <button type="button" id="btnSearchPwd" class="btn btn-lg btn-success btn-block">비밀번호찾기</button>
                                
                            </fieldset>
                            <p>
                            <a href="#" onclick="viewLogin()">로그인</a>&nbsp;&nbsp;
                            <a href="#" onclick="viewSearchId()">아이디찾기</a>&nbsp;&nbsp;
                            <a href="#" onclick="viewpwdSearch()">비밀번호찾기</a>
                            </p>
                            <a href="/member">회원가입</a>&nbsp;&nbsp;
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- jQuery -->
    <script src="/resources/vendor/jquery/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="/resources/vendor/bootstrap/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="/resources/vendor/metisMenu/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="/resources/dist/js/sb-admin-2.js"></script>

</body>

</html>
