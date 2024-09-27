<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1 align="center">jsp Action Tag</h1>
	<p><%= request.getContextPath() %></p>
	
	<!-- 
		url 요청을 보낼 때 마지막이 확장자로 끝나는 경우
		contextRoot명(/chap15)이 web폴더(root 폴더)를 가르키고 이후 경로가 web폴더 이후의
		파일의 저장 위치를 나타낸다.
		
		url 요청을 보낼 때 마지막이 확장자로 끝나지 않는 경우
		contextRoot명 이후 경로가 해당 요청을 받아줄 controller에게 요청하는 주소가 된다.
		(servlet에서는 해당 url 요청을 받아줄 서블릿에 해당 된다.) 
	 -->
	<h3><a href="<%= request.getContextPath() %>/views/action/testAction.jsp">
		Jsp Action Tag 테스트</a></h3>
		
	<br>
	
	<h2>EL test</h2>
	<%-- 
	<h3><a href="/chap15/test1">request.getAttribute() 테스트</a></h3>
	<h3><a href="<%= request.getContextPath() %>/test1">request.getAttribute() 테스트</a></h3> 
	--%>
	<h3><a href="${pageContext.servletContext.contextPath }/test1">request.getAttribute() 테스트</a></h3>			
	<h3><a href="${pageContext.servletContext.contextPath }/test2">request에 저장된 객체 출력 테스트</a></h3>
	<h3><a href="${pageContext.servletContext.contextPath }/views/el/testEl3.jsp?name=galaxy&price=95&no=5&no=6">request에 담긴 parameter 출력 테스트</a></h3>                        
	<h3><a href="${pageContext.servletContext.contextPath }/test4">requestScope와 sessionScope 테스트</a></h3>
	
	<br>
	
	<h2>JSTL test</h2>
	<h3><a href="${pageContext.servletContext.contextPath }/views/jstl/testJstlCore.jsp">Core Library Test</a></h3>
	<h3><a href="${pageContext.servletContext.contextPath }/views/jstl/testJstlFmt.jsp">Fmt Library Test</a></h3>
	<h3><a href="${pageContext.servletContext.contextPath }/views/jstl/testJstlFunction.jsp">Function Library Test</a></h3>
</body>
</html>






