<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- WEB-INF 이하 폴더는 직접 접근할 수 없다.(상대경로든 절대경로든) -->
	<!-- 
	<a href="WEB-INF/views/main/main.jsp">메인 페이지로 이동 try</a>	
	<a href="/chap16/WEB-INF/views/main/main.jsp">메인 페이지로 이동 try</a>
	-->
	
	<jsp:forward page="WEB-INF/views/main/main.jsp"/>
</body>
</html>