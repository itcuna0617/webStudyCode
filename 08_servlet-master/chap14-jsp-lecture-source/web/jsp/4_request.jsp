<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1 align="center">메뉴 주문</h1>
	
	<!-- 
		jsp에서 servlet으로 요청하는 경로를 '/'가 없는 상대경로를 하게 되면 현재 jsp 파일의 위치에 따라
		앞에 web폴더 하위의 폴더들이 추가될 수 있다.
		ex) jsp폴더 아래의 4_request.jsp라면...
		    /chap14/jsp/menu/order
		    jsp폴더 아래의 menu폴더 아래의 4_request.jsp라면...
		    /chap14/jsp/menu/menu/order
		
		'/'만 주고 절대경로로 하게 되면 context root명이 생략된 요청을 하게 된다.
		
		결론은, context root명을 포함한 절대 경로로 요청해야 한다.
	 -->
	<form action="/chap14/menu/order" method="post">
		<select name="menuName">
			<option value="햄버거">햄버거</option>
			<option value="짜장면">짜장면</option>
			<option value="짬뽕">짬뽕</option>
			<option value="순대국">순대국</option>
		</select>
		<input type="number" min="1" max="10" step="1" name="amount">
		<input type="submit" value="선택완료">
	</form>
	
</body>
</html>





