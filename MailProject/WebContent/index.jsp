<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<link rel="stylesheet" type="text/css" href="css/login.css">
</head>

<body>
<% request.setCharacterEncoding("UTF-8");
   response.setCharacterEncoding("UTF-8");
   session.invalidate();
%> 
	<form method="post" action="Register" id="register_form"></form>
	<form name="login_form" method="post" action="DoLogin">
		<div id="div_login">
			<input type="text" name="username" id="tf_username" />
			<input type="password" name="password" id="tf_password" />
			<input name="action" type="submit" id="sm_login" value="Login" />
			<input name="action" type="submit" id="sm_register" value="Register" form="register_form"/>
		</div>
		</form>
	
</body>
</html>