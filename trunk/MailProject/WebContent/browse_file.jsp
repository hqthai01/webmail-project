<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Browse Files</title>
<link rel="stylesheet" type="text/css" href="css/browse.css">
<script src="js/close_after_submit.js" type="text/javascript"></script>

	<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	session.removeAttribute("filename");
	%>
</head>

<body>
	<form action="DoVerify" method="post" enctype="multipart/form-data" name="form_upload"> 
		<div id="div_upload">
			<center>
				<input id="browse" type="file" name="files" /> <br/>
				<input type="button" name="action" value="Attach files" onclick="closeSelf('form_upload')"/>
			</center>
		</div>
	</form>
</body>
</html>