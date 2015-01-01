<%@page import="java.util.Iterator"%>
<%@page import="java.sql.Date"%>
<%@page import="model.Mail"%>
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
	MailBox mb = null;
	session.removeAttribute("items");
	if (session.getAttribute("username") == null || session.getAttribute("username") == "") {
%>
<jsp:forward page="/index.jsp"></jsp:forward>
<%
	} else {
%>
<%
	mb = (MailBox) session.getAttribute("mailbox");
	}
%>
<title>[${sessionScope.username } : Mail Sent]</title>
<link rel="stylesheet" type="text/css" href="css/sentmail.css">
</head>

<body>
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
		<div id="div2">
			<div id="div_top">
				<table width="99%" border="0">
					<tr>
						<th id="from_width">From</th>
						<th id="subject_width">Subject</th>
						<th id="attach_width">Attachment</th>
						<th id="date_width">Date</th>
					</tr>
				</table>
				<div class="scrollit">
					<table width="100%" border="0">
						<tbody>
							<%
								if (mb != null && mb.getMails().size() != 0) {
									Iterator<Mail> iter = mb.getMails().iterator();
									while(iter.hasNext()) {
										Mail mail = iter.next();
										if (mail.getFlag() == Mail.FLAG_SENT) {
							%>
							<tr>
								<td id="from_width"><a href="DoReadMail?pos=<%=mail.getId()%>"><%=mail.getMail_From()%></a></td>
								<td id="subject_width"><a href="DoReadMail?pos=<%=mail.getId()%>"><%=mail.getSubject()%></a></td>
								<td id="attach_width"><a href="DoReadMail?pos=<%=mail.getId()%>"><%=mail.getAttachments().size()%></a></td>
								<td id="date_width"><a href="DoReadMail?pos=<%=mail.getId()%>"><%=new Date(mail.getDate().getTime())%></a></td>
							</tr>

							<%
								}
							%>
							<%
								}
							%>
							<%
								}
							%>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>