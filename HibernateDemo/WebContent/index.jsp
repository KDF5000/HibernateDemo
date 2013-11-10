<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="UserQuery" method="post" target="userInfo">
	<label name="uLabel" for="userName">请输入用户名</label>
	<input type="text" name="userName" value="" />
	<input type="submit" value="查询"/>
</form>
<iframe frameborder="0" name="userInfo" >
</iframe>
</body>
</html>