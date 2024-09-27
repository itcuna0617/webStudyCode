<jsp:directive.page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- <%@ include file="common.jsp" %> --%>
	<jsp:include page="common.jsp"/>	<!-- JSP Action 태그를 활용한 include -->
	<h1>여기서부터 내용입니다.</h1>
	
	<% request.setAttribute("name", "홍길동"); %>
	
	<jsp:forward page="testForward.jsp"/>
</body>
</html>










