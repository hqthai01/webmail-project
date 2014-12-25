<%@page import="model.User"%>
<%@page import="model.MailBox"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	if (session.getAttribute("username") == null) {

	}
%>
<title>${requestScope.username }" : " Inbox</title>
<link rel="stylesheet" type="text/css" href="css/inbox.css">
</head>
<body>
	<form action="DoCheckMail" method="post">
		<div id="div_main">
			<div id="div0"></div>
			<div id="div1">
				<input style="width: 100%" name="newmail" type="submit"
					value="Compose Mail" /> <input style="width: 100%" name="newmail"
					type="submit" value="Inbox" /> <input style="width: 100%"
					name="newmail" type="submit" value="Sent Mail" />
			</div>
			<div id="div2">
				<div id="div_top">
					<table width="99%" border="1">
						<tr>
							<th id="from_width">From</th>
							<th id="subject_width">Subject</th>
							<th id="attach_width">Attachment</th>
							<th id="date_width">Date</th>
						</tr>
					</table>
					<div class="scrollit">
						<tbody>
							<table width="100%" border="0">
								<tr>
									<td id="from_width">holdthehand@gmail.com</td>
									<td id="subject_width">Fuck you co ho'</td>
									<td id="attach_width">flase</td>
									<td id="date_width">24/12/2014</td>
								</tr>
							</table>
						</tbody>
					</div>
				</div>
			</div>
			<div id="div_bottom">
				<table width="99%" border="1">
					<tr>
						<th id="from_width">From</th>
						<th id="subject_width">Subject</th>
						<th id="attach_width">Attachment</th>
						<th id="date_width">Date</th>
					</tr>
				</table>
				<div class="scrollit1">
					<tbody>
						<table width="100%" border="0">
							<tr>
								<td id="from_width">holdthehand@gmail.com</td>
								<td id="subject_width">Fuck you co ho'</td>
								<td id="attach_width">false</td>
								<td id="date_width">24/12/2014</td>
							</tr>
						</table>
					</tbody>
				</div>
			</div>
		</div>
	</form>
</body>
</html>
