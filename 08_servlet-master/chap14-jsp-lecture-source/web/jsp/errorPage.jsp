<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String exceptionType = exception.getClass().getName();
		String exceptionMessage = exception.getMessage();
	%>
	<h1 align="center">에러 페이지</h1>
	<h1 align="center"><%= exceptionType %></h1>
	<h1 align="center"><% out.print(exceptionMessage); %></h1>
</body>
</html>