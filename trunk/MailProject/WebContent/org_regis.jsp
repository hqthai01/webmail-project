<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
	%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Organization Register</title>
<link rel="stylesheet" type="text/css" href="css/org_register.css"/>
<script type="text/javascript" src="js/submit.js"></script>
<script type="text/javascript" src="js/showpopup.js"></script>
</head>
<body>
	<div id="div_main">
		<div id="div_left"></div>
		<div id="div_right">
			<form id="org_register" name="regisorg" action="DoRegisterOrg" method="post">
				<input id="form_input1" type="text" name="orgdomain" value="${sessionScope.orgdomain }"/>
				<input id="form_input2" type="text" name="orgname" value="${sessionScope.orgname }"/>
				<input id="form_input3" type="button" name="orgcertificate" onclick="showpopup('browse_file.jsp', 300, 200)" value="${sessionScope.filename }"/>
			</form>
		</div>	
		<div id="div_bottom">
			<input id="form_input4" type="submit" name="action" value="Register" form="org_register"/>
		</div>
		<center><h2>${sessionScope.flag}</h2></center>
		<%
			session.removeAttribute("flag"); 
			session.removeAttribute("orgdomain"); 
			session.removeAttribute("orgname"); 
		%>
	</div>

</body>
</html>