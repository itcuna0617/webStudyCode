<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1 align="center">include Directive</h1>
	
	<div align="right"><%@ include file="today.jsp" %></div>
	
	<%
		/*
			인용한 페이지에서 선언했던 변수 이름을 사용했기 때문에 컴파일 에러가 발생한다.
			(인용한 페이지가 현재 페이지에 영향을 준다!)
		*/
		// String output = "";
	%>
</body>
</html>


