<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>requestScope와 sessionScope 테스트</h2>
	<h3>requestScope값 출력하기</h3>
	이름: ${ requestScope.member.name }
	나이: ${ requestScope.member.age }
	전화번호: ${ requestScope.member.phone }
	이메일: ${ requestScope.member.email }
	<br>
	이름: ${ member.name }
	나이: ${ member.age }
	전화번호: ${ member.phone }
	이메일: ${ member.email }
	
	<h3>sessionScope값 출력하기</h3>
	이름: ${ sessionScope.member.name }
	나이: ${ sessionScope.member.age }
	전화번호: ${ sessionScope.member.phone }
	이메일: ${ sessionScope.member.email }
	
</body>
</html>




