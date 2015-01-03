<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="java.util.List"%>
<%@page import="model.Mail"%>
<%@page import="model.MailBox"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>[${sessionScope.username} : Compose Mail]</title>
	<link rel="stylesheet" type="text/css" href="css/compose-mail.css">
	<link href="ckeditor/contents.css" rel="stylesheet" type="text/css" />
	<script src="ckeditor/ckeditor.js"type="text/javascript"></script>
	<script src="ckeditor/config.js"type="text/javascript"></script>
	<script src="ckeditor/build-config.js"type="text/javascript"></script>
	<script src="ckeditor/style.js"type="text/javascript"></script>
	<script src="js/submit.js" type="text/javascript"></script>
	<!-- <script src="js/showpopup.js" type="text/javascript"></script> -->
	<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	MailBox mb = null;
	if (session.getAttribute("username") == null || session.getAttribute("username")=="") {
		%>
		<jsp:forward page="/index.jsp"></jsp:forward>	
		<%} %>
</head>
<body>
<center><h2> ${requestScope.flag}</h2></center>
	<div id="div_main">
		<div id="div0"></div>
		<div id="div1">
			<form action="DoComposeMail" method="post" >
				<input style="width: 100%" name="action" type="submit" value="Compose Mail" />
			</form> 
			<form action="DoCheckMail" method="post" >
				<input style="width: 100%" name="action" type="submit" value="Inbox" />
			</form>
			<form action="DoViewSentMail" method="post" > 
				<input style="width: 100%" name="action" type="submit" value="Sent Mail" />
			</form>
		</div>
		<form action="DoUpload" method="post" enctype="multipart/form-data" name="form_upload" id="upload">
		</form>
		<form action="DoSendMail" method="post">
		<div id="div2">
			<div id="div_top">
				<div id="div_address">
					<input id="tf_from" readonly="readonly" name="from" type="text" value="${sessionScope.username}@${sessionScope.organization}"/><br />
					<input id="tf_to" name="to" type="text" /><br />
					<input id="tf_subject" name="subject" type="text" />
				</div>
				<div id="div_attachment">
					<div id="div_att_left">
						<select id="attach" name="attachment" multiple>
						<%
							@SuppressWarnings("unchecked")
							List<FileItem> items = (List<FileItem>)session.getAttribute("items");
							if(items != null){
						%>
							<%for(int i = 0 ; i < items.size(); i++){ %>
								<option value="<%=i%>"><%=items.get(i).getName() %></option>
							<%} %>
						<%} %>
						</select>
					</div>
					<div id="div_att_right">
						<!--  <input id="attachfile" name="browse" type="file" value="Attach Files" onclick="return showpopup('browse_files.jsp', 250, 180)"/>-->
						<input id="attachfile" type="file" name="browse" value="Attach Files" form="upload" onchange="formSubmit('form_upload')" multiple="multiple"/>
						<input id="removes" name="action" type="submit" value="Remove Selected" />
					</div>
				</div>
				<div id="div_send">
					<input id="bt_send" name="action" type="submit" value="Send" />
				</div>
			</div>
			<div id="div_bottom">
				<textarea id="content" name="message" class="ckeditor">
				</textarea>
			</div>
		</div>
		</form>
	</div>
</body>
</html>