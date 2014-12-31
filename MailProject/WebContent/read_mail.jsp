<%@page import="model.dao.MailDAO"%>
<%@page import="model.Attachment"%>
<%@page import="java.util.Iterator"%>
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
	Mail mail = (Mail)request.getAttribute("mail");
	if (session.getAttribute("username") == null || session.getAttribute("username")=="") {
		%>
		<jsp:forward page="/index.jsp"></jsp:forward>	
		<%} else{%>
		<%
		String pos = (String)request.getAttribute("pos");
		if(pos!= null){
			/* mail = MailDAO.getMail(Integer.parseInt(pos)); */
		}
	} %>
<title>[${sessionScope.username } : Read Mail]</title>
<link rel="stylesheet" type="text/css" href="css/readmail.css">
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
				<div id="div_top_left">
					<input id="tf_from" readonly="readonly" name="from" type="text" value="${requestScope.mail.getMail_From() }" >
					<input id="tf_to" readonly="readonly" name="to" type="text" value="${requestScope.mail.getMail_To() }" />
					<input id="tf_subject" readonly="readonly" name="subject" type="text" value="${requestScope.mail.getSubject() }"/>
				</div>
				<div id="div_top_mid">
					<select id="attach" name="attach" multiple>
					<%if(mail != null) {%>
						<%if(mail.getAttachments()!= null && mail.getAttachments().size()!=0) {%>
						<%Iterator<Attachment> iter =  mail.getAttachments().iterator();%>
							<%while(iter.hasNext()){ 
								Attachment att = iter.next();
							%>
								<option value="${att.getId() }"><%=att.getFileName() %></option>
							<%} %>
						<%} %>
					<%} %>
					</select>
				</div>
				<div id="div_top_right">
					<input id="downloadall" name="downloadall" type="submit" value="Download All" />
					<input id="downloads" name="downloads" type="submit" value="Download Selected" />
				</div>
			</div>
			<div id="div_bottom">
				<div class="scrollit">
					<%if(mail!= null) %>
						${requestScope.mail.getMessage() }
				</div>
			</div>
		</div>
	</div>
</body>
</html>