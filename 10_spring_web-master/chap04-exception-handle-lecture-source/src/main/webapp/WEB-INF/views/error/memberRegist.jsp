<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1 align="center">${exception} 발생!</h1>
	<h1 align="center">${exception.getClass()} 발생!</h1>
	<h1 align="center">${exception.getClass().getSimpleName()} 발생!</h1>
	
	<h1 align="center">${exceptionMessage}</h1>
</body>
</html>