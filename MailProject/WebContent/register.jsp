<%@page import="model.dao.OrganizationDAO"%>
<%@page import="model.Organization"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Register</title>
	<link rel="stylesheet" type="text/css" href="css/register.css">
	
	<%
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
	%>
</head>

<body>
<h4 style="text-align: center">${requestScope.flag }</h4>
	<div id="div_body">
		<form action="DoRegister" method="post">
			<input name="account" type="text" id="tf_account" value="${requestScope.account }"/>
			<input name="password" type="password" id="tf_password"/>
			<input name="confirm" type="password" id="tf_confirm"/>

			<input name="fullname" type="text" id="tf_fullname" value="${requestScope.fullname }"/>
			<input name="country" type="text" id="tf_country" value="${requestScope.country }"/>
			<input name="phone" type="text" id="tf_phone" value="${requestScope.phone }"/>
			<select name="org" id="select_org">
			<%
				List<Organization> list = OrganizationDAO.getOrganizations();
				for(Organization org : list){
			%>
					<option value="<%=org.getOrgDomain()%>"><%="@"+org.getOrgDomain() %></option>
			<%} %>
			</select>
			<input id="sm_register" name="action" type="submit" value="Register" />
			<input id="rs_clearfields" type="reset" value="Clear field" />
		</form>
	</div>
</body>
</html>