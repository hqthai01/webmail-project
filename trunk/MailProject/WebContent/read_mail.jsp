<%@page import="model.MailBox"%>
<%@page import="model.Mail"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	MailBox mb = null;
	Mail mail = null;
	if (session.getAttribute("username") == null || session.getAttribute("username")=="") {
		%>
		<jsp:forward page="/index.jsp"></jsp:forward>	
		<%} else{%>
		<%
		mb = (MailBox)session.getAttribute("mailbox");
		mail = mb.getListMail().get(Integer.parseInt((String)request.getAttribute("pos")));
	} %>
<title>[${sessionScope.username } : Read Mail]</title>
<link rel="stylesheet" type="text/css" href="css/readmail.css">
</head>

<body>
	<form action="" method="post">
		<div id="div_main">
			<div id="div0"></div>
			<div id="div1">
				<input style="width:100%" name="action" type="submit" value="Compose Mail" />
				<input style="width:100%" name="action" type="submit" value="Inbox" />
				<input style="width:100%" name="action" type="submit" value="Sent Mail" />
			</div>
			<div id="div2">
				<div id="div_top">
					<div id="div_top_left">
						<input id="tf_from" disabled="disabled" name="from" type="text" /><br />
						<input id="tf_to" disabled="disabled" name="to" type="text" /><br />
						<input id="tf_subject" disabled="disabled" name="subject" type="text" />
					</div>
					<div id="div_top_mid">
						<select id="attach" name="attach" multiple>
							<option value="item1">Test hang</option>
						</select>
					</div>
					<div id="div_top_right">
						<input id="downloadall" name="downloadall" type="submit" value="Download All" />
						<input id="downloads" name="downloads" type="submit" value="Download Selected" />
					</div>
				</div>
				<div id="div_bottom">

				</div>
			</div>
		</div>
	</form>

</body>
</html>