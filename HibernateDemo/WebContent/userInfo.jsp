<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.hibernate.demo.User" %>
<%
	User aUser = (User)request.getAttribute("aUser");
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table>
	<tr>
		<td>用户名：</td>
		<td><%=aUser.getUserName() %></td>
	</tr>
	<tr>
		<td>密码：</td>
		<td><%=aUser.getPassword() %></td>
	</tr>
	<tr>
		<td>邮箱:</td>
		<td><%=aUser.getMail() %></td>
	</tr>
</table>
</body>