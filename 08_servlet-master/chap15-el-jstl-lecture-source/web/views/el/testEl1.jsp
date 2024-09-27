<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%-- <%@ page import="java.util.*" %> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>전달 된 request 객체에서 저장된 정보 출력하기</h2>
	<%-- <%
		String name = (String)request.getAttribute("name");
		int age = (Integer)request.getAttribute("age");
		String phone = (String)request.getAttribute("phone");
	%>
	
	name: <%= name %><br>
	age: <%= age %><br>
	phone: <%= phone %><br> --%>
	
	<!-- EL 문법을 활용해 보자. -->
	<!-- 넘겨받은 데이터의 스코프에 맞게 attribute를 꺼내기 -->
	name: ${ requestScope.name }<br>
	age: ${ requestScope.age }<br>
	phone: ${ requestScope.phone }<br>
	
	<!-- 스코프 범위는 생략이 가능함.(page -> request -> session -> application) -->
	name: ${ name }<br>
	age: ${ age }<br>
	phone: ${ phone }<br>
	
	<!-- 같은 키값이 낮은 스코프에도 있다면 높은 스코프에 있는 값은 스코프를 명시해서 꺼내야 한다. -->
	name: ${ sessionScope.name }<br>
	name: <%= request.getSession().getAttribute("name") %><br> <!-- 표현식 태그를 사용한다면.. -->
	age: ${ age }<br>
	phone: ${ phone }<br>
	
	<br>
	
	<h2>items 이름으로 저장 된 리스트 정보 출력하기</h2>
	<%-- <%
		List<String> items = (ArrayList<String>)request.getAttribute("items");
	%>
	<%= items.get(0) %><br>
	<%= items.get(1) %><br>
	<%= items.get(2) %><br> --%>
	
	<br>
	
	${ items[0] } <br>
	${ items[1] } <br>
	${ items[2] } <br>
	
</body>
</html>




