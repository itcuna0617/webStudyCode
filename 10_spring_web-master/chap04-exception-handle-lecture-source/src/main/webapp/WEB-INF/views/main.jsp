<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 
		스프링 MVC에서는 클래스 예외 처리용 뷰를 관리하는 기능(예외 핸들링)도 추가로 제공하고 있다.
		
		크게 두 가지 방식으로 살펴보자.
	 -->
	<h3>SimpleMappingExceptionResolver를 이용한 방식</h3>
	<button onclick="location.href='simple-null'">NullPointerException 테스트</button>
	<button onclick="location.href='simple-user'">사용자 정의 Exception 테스트</button>
	
	<!-- ExceptionHandlerController 하단부에 이 방식을 적용한 메소드 추가함 -->
	<h3>@ExceptionHandler 어노테이션을 이용한 방식</h3>
</body>
</html>



