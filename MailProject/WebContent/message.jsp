<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>[${sessionScope.username} : Sent successful]</title>
</head>
<body>
<h1 style="text-align: center;">${requestScope.flag}</h1>
<%
session.removeAttribute("items");
response.setHeader("Refresh","5;url=inbox.jsp"); %>
</body>
</html>