<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 1. 페이지 지시자 태그 -->
<!-- 
	페이지에 대한 설정을 하는 태그이다.
	현재 페이지에 스크립틀릿(scriptlet) 태그를 이용하여 작성하는 문법은 자바라는 의미이며,
	response의 header에 응답을 위한 설정을 하는 것도 할 수 있다.
	content-type이라는 헤더에 MIME 타입과 인코딩 방식을 지정해 두었다.
 -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- <h1>첫 jsp!!!</h1>
	<% int num = 1; %>
	<h1><% out.print(num + 38); %></h1>
	<h1><%= num + 38 %></h1> --%>
	
	<!-- 
		JSP는 표면상으로는 HTML문서와 유사하다.
		하지만 JSP컨테이너가 최초 JSP로 요청할 시 JSP를 서블릿으로 변환시킨 후,
		서블릿 컨테이너가 변환된 서블릿을 이용해 인스턴스를 생성하고 호출한다.
		JSP는 매 요청 시마다 기존 JSP 파일에 변화가 생겼는지를 확인하여 변경이 없는 경우
		기존에 생성해 둔 인스턴스를 사용하고, 변경이 있는 경우 translate 과정을 거쳐 인스턴스를
		다시 생성한다.
		JSP가 동적으로 변환되는 순서는
		translate -> compile -> load -> instance 생성(initialization) -> run의 순서이다.
	 -->
	 
	 <!-- 
	 	JSP는 HTMl 기반의 문서에서 자바 문법을 사용할 수 있도록 지원한다.
	 	JSP의 태그 엘리먼트를 이용하여 사용 목적별로 자바 코드를 이용할 수 있도록 지원한다.
	 	JSP의 태그 엘리먼트는 directive, declare, scriptlet, expression, comment가 있다.
	  -->
	  
	 <!-- 2. jsp 주석 태그 -->
	 <%-- html 주석은 클라이언트에 노출 되지만, jsp 주석은 클라이언트에게 노출되지 않는다. --%>
	 
	 <!-- 3. 선언 태그 -->
	 <%!
	 	private String name = "홍길동";
	 	private int age = 20;
	 %>
	 
	 <h1>a</h1>
	 <!-- 4. 스크립틀릿 태그 -->
	 <%
	 	/*
	 		간단한 자바 코드를 작성할 수 있는 부분이다.
	 		스크립틀릿 태그 내에서의 주석은 자바 주석과 동일하다.
	 		선언 태그에서 작성한 내용을 초기화 하고 출력할 수도 있으며, 간단한 로직 처리도 가능하다.
	 		여기서 작성하는 코드는 자바 코드이기 때문에 ;(세미콜론)을 작성하지 않으면 compile 과정에서
	 		에러가 발생한다.
	 	*/
	 	name = "유관순";
	 	System.out.println(name);
	 	out.println(name);
	 	for(int i = 0; i < 5; i++) {
	 %>
	 <h1>b</h1>
	 <%
	 	}
	 %>
	 
	 <!-- 5. 표현식 태그 -->
	 <!-- 
	 	expression 태그 내에 세미콜론을 작성하게 되면 out.print(name;);의 형태가 되기 때문에
	 	compile 에러가 발생한다.
	  -->
	 <%= name %>
	 
	 <label>name: </label><h2><%= name %></h2><br>
	 <label>age: </label><h2><%= age %></h2><br>
	 
	 <% for(int i = 0; i < 10; i++) { %>
	 	<h2>text - <%= i %></h2>
	 <% } %>
	 
</body>
</html>











