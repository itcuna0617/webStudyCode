<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1 align="center">Spring MVC Mapping 테스트</h1>
	
	<h2>1. 메소드에 요청 매핑하기</h2>
	
	<!-- 요청 방식을 get과 post로 하여 같은 url 주소로 요청을 보냈을 때 하나의 핸들러 메소드로 처리해 보자. -->
	<h3>GET: /menu/regist</h3>
	<button onclick="location.href='${pageContext.servletContext.contextPath}/menu/regist'">
		GET 메뉴 등록 요청
	</button>
	
	<h3>POST: /menu/regist</h3>
	<form action="${pageContext.servletContext.contextPath}/menu/regist" method="post">
		<button type="submit">POST 메뉴 등록 요청</button>
	</form>
	
	<!-- get방식과 post 방식 요청 처리를 구분 -->
	<h3>GET: /menu/modify</h3>
	<button onclick="location.href='${pageContext.servletContext.contextPath}/menu/modify'">
		GET 메뉴 수정 요청
	</button>
	
	<h3>POST: /menu/modify</h3>
	<form action="${pageContext.servletContext.contextPath}/menu/modify" method="post">
		<button type="submit">POST 메뉴 수정 요청</button>
	</form>
	
	<!-- @GetMapping 또는 @PostMapping을 이용해 요청 처리를 구분 -->
	<h3>GET: /menu/delete</h3>
	<button onclick="location.href='${pageContext.servletContext.contextPath}/menu/delete'">
		GET 메뉴 삭제 요청
	</button>
	
	<h3>POST: /menu/delete</h3>
	<form action="${pageContext.servletContext.contextPath}/menu/delete" method="post">
		<button type="submit">POST 메뉴 삭제 요청</button>
	</form>
</body>
</html>








