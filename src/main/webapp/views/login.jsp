<%@include file="/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <c:url var="APIurl" value="/api-admin-user"/>
    <c:url var="LoginUrl" value="/dang-nhap"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dang nhap</title>
</head>
<body>
<h1>WELLCOME</h1>

	<div class="w3layoutscontaineragileits">
	<h2>Login here</h2>
		<form action="<c:url value = '/dang-nhap' />" method="post">
			<input type="email" Name="username" placeholder="EMAIL" required="">
			<input type="password" Name="password" placeholder="PASSWORD" required="">
			<ul class="agileinfotickwthree">
				<li>
					<input type="checkbox" id="brand1" value="">
					<label for="brand1">
						<c:if test="${not empty message}">
							<div class="alert alert-<c:out value='${alert}'/>">
									<c:out value='${message}'/>
							</div>
						</c:if>
					</label>
					<a href="#">Forgot password?</a>
				</li>
			</ul>
			<div class="aitssendbuttonw3ls">
				<input type="submit" id="btnLogin" value="LOGIN">
				<p> To register a new account<span></span> <a class="w3_play_icon1" href="#small-dialog1"> Click Here</a></p>
				<div class="clear"></div>
			</div>
		</form>
	</div>
	
	<!-- for register popup -->
	<div id="small-dialog1" class="mfp-hide">
		<div class="contact-form1">
			<div class="contact-w3-agileits">
				<h3>Register Form</h3>
				
						<div class="form-sub-w3ls">
							<input placeholder="Full Name"  type="text" id="fullname"  required=""> 
							<div class="icon-agile">
								<i class="fa fa-user" aria-hidden="true"></i>
							</div>
						</div>
						<div class="form-sub-w3ls">
							<input placeholder="Email" class="mail" type="email" id="username" required="">
							<div class="icon-agile">
								<i class="fa fa-envelope-o" aria-hidden="true"></i>
							</div>
						</div>
						<div class="form-sub-w3ls">
							<input placeholder="Password"  type="password" id="password" required="">
							<div class="icon-agile">
								<i class="fa fa-unlock-alt" aria-hidden="true"></i>
							</div>
						</div>
						<div class="form-sub-w3ls">
							<input placeholder="Confirm Password"  type="password" id="password1" required="">
							<div class="icon-agile">
								<i class="fa fa-unlock-alt" aria-hidden="true"></i>
							</div>
						</div>
					
					<div class="submit-w3l">
						<button id="btnRegister">Register</button>
						<!-- <input type="submit" value="Register"> -->
					</div>

					
				
			</div>
		</div>	
	</div>
	<!-- //for register popup -->
	
	<div class="w3footeragile">
		<p> &copy; 2017 Existing Login Form. All Rights Reserved | Design by <a href="http://w3layouts.com" target="_blank">W3layouts</a></p>
	</div>

	
	<script type="text/javascript" src="<c:url value = '/template/login/js/jquery-2.1.4.min.js' />"></script>

	<!-- pop-up-box-js-file -->  
		<script src="<c:url value = '/template/login/js/jquery.magnific-popup.js'/>" type="text/javascript"></script>
	<!--//pop-up-box-js-file -->
	<script>
		$(document).ready(function() {
		$('.w3_play_icon,.w3_play_icon1,.w3_play_icon2').magnificPopup({
			type: 'inline',
			fixedContentPos: false,
			fixedBgPos: true,
			overflowY: 'auto',
			closeBtnInside: true,
			preloader: false,
			midClick: true,
			removalDelay: 300,
			mainClass: 'my-mfp-zoom-in'
		});													
		});

		$('#btnRegister').click(function(){
			
			
			if(xuLyNgoaiLe()){
				var data = {
					"fullname": $('#fullname').val(),
					"username": $('#username').val().split("@")[0],
					"password": $('#password').val(),
					"status":1,
    				"roleId":2
				};
				addUser(data);
			}
			
		});				

		function addUser(data) {
        $.ajax({
            url: '${APIurl}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
            	window.location.href = "${LoginUrl}?action=login&message=Dang ky thanh cong";
            },
            error: function (error) {
            	alert("Loi");
            }
        });
    }

    function xuLyNgoaiLe(){
    	var fullname = $('#fullname').val();
    	var username = $('#username').val();
    	var password = $('#password').val();
    	var password1 = $('#password1').val();

    	if(fullname == ""){
    		alert("Ban phai dien Fullname!");
    		return false;
    	}
    	if(username == ""){
    		alert("Ban phai dien Username!");
    		return false;
    	}
    	if(password == ""){
    		alert("Ban phai dien Password!");
    		return false;
    	}
    	if(password1 == ""){
    		alert("Ban phai dien vao Password xac nhan");
    		return false;
    	}

    	if(password != password1){
    		alert("Hai Password phai giong nhau");
    		return false;
    	}

    	return true;

    }

   
	</script>
</body>
</html>


