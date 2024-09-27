<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String menuNameParam = request.getParameter("menuName");
		String amountParam = request.getParameter("amount");
		String menuName = (String)request.getAttribute("menuName");
		int amount = (Integer)request.getAttribute("amount");
		int orderPrice = (Integer)request.getAttribute("orderPrice");
	%>
	<p><%= menuNameParam %></p>
	<p><%= amountParam %></p>
	<h3>주문하신 음식: <%= menuName %></h3>
	<h3>주문하신 수량: <%= amount %></h3>
	<h3>결제하실 해당 메뉴의 금액: <%= orderPrice %></h3>
</body>
</html>