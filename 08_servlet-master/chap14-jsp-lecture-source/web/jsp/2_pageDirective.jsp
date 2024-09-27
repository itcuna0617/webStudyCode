<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Date, java.text.*" 
    errorPage="errorPage.jsp" %>	<!-- 세미콜론 찍으면 안됨, 콤마로 구분 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<!-- 
		페이지 지시자 태그에서 사용 가능한 속성들은 여러가지들이 있지만 현재 알고 있어야 하는 속성은
		크게 많지는 않다.
		errorPage: 현재 페이지에서 Exception이 발생하게 되면 속성 값에 설정한 jsp 경로로 Exception
		           객체를 던진다.
		isErrorPage: 현재 페이지가 Exception을 처리할 페이지인 경우 true로 지정한다.(기본은 false)
		             그러면 exception이라는 변수명으로 넘어온 Exception 객체를 활용할 수 있다.
	    import: java.lang 패키지 이외의 클래스를 현 jsp 파일에서 사용할 때 import 속성에
	            정의하게 되면 import 선언부를 작성하게 된다.(세미콜론 사용하면 안됨)
	            
	    또한 지시자 태그는 page 지시자 태그, include 지시자 태그, taglib 지시자 태그가 있다.
	    1. page 지시자 태그: 현 jsp 페이지에 대한 설정을 하는 지시자 태그
	    2. include 지시자 태그: 다른 jsp 파일을 포함하기 위해 사용하는 지시자 태그
	    3. taglib 지시자 태그: xml 문법 기반의 라이브러리인 JSTL을 이용하기 위해 선언하는 지시자 태그  
	 -->
	<%
		Date date = new Date();
		System.out.println(date);
	%>
	
	<%= date %>
	
	<%
		String str = "";
		char ch = str.charAt(2);
	%>
	
</body>
</html>


