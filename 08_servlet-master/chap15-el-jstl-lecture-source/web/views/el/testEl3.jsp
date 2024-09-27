<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>parameter 값 받아서 출력하기</h2>
	<%--
	<%
		String name = request.getParameter("name");
		int price = Integer.valueOf(request.getParameter("price"));
		String[] no = request.getParameterValues("no");		// 'no'라는 같은 키로 값이 두개 넘어옴
	%>
	
	상품명: <%= name %><br>
	가격: <%= price %><br>
	제품번호: <%= no[0] %>, <%= no[1] %><br>
	--%>
	
	상품명: ${ param.name }<br>
	가격: ${ param.price }<br>
	제품번호: ${ paramValues.no[0] }, ${ paramValues.no[1] }<br>
	옵션: ${ (empty param.option) ? "옵션없음" : param.option }
	
	
</body>
</html>


