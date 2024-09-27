<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1 align="cneter">JSTL Fmt Library Tag Test</h1>
	
	<h2>fmt:formatNumber 태그: 숫자에 포멧 적용하는 태그</h2>
	
	<c:set var="number" value="123456789"/>
	
	<!-- ','와 같은 각 숫자 단위의 구분자 표시 -->
	<fmt:formatNumber value="${ number }" groupingUsed="true" /><br>
	
	<fmt:formatNumber value="1.234567" pattern="#.###" /><br>
	
	<fmt:formatNumber value="1.2" pattern="#.##" /><br>
	
	<fmt:formatNumber value="1.2" pattern="#.00" /><br>
	 
	<fmt:formatNumber value="0.12" type="percent" /><br>
	
	<fmt:formatNumber value="${ number }" type="currency" /><br>
	<fmt:formatNumber value="${ number }" type="currency" currencySymbol="$" /><br>
	
	<hr>
	
	<h2>fmt:formatDate 태그: 날짜와 시간에 포멧 적용하는 태그</h2>
	<c:set var="today" 
	value="<%= new java.util.Date(new java.util.GregorianCalendar(2021, 3-1, 15, 2, 14, 30).getTimeInMillis()) %>"/>
	today의 값: ${ today }<br>
	날짜: <fmt:formatDate value="${ today }" type="date"/><br>
	시간: <fmt:formatDate value="${ today }" type="time"/><br>
	날짜와 시간: <fmt:formatDate value="${ today }" type="both"/><br>
	
	<h2>날짜와 시간에 제공되는 포멧을 적용한 경우</h2>
	<!-- medium이 default 값이다 -->
	[default]: 
	<fmt:formatDate value="${ today }" type="both" dateStyle="default" timeStyle="default"/><br>
	[short]:
	<fmt:formatDate value="${ today }" type="both" dateStyle="short" timeStyle="short"/><br>
	[medium]:
	<fmt:formatDate value="${ today }" type="both" dateStyle="medium" timeStyle="medium"/><br>
	[long]:
	<fmt:formatDate value="${ today }" type="both" dateStyle="long" timeStyle="long"/><br>
	[full]:
	<fmt:formatDate value="${ today }" type="both" dateStyle="full" timeStyle="full"/><br>
	
	<h3>원하는 포멧으로 pattern 적용한 경우</h3>
	날짜: <fmt:formatDate value="${ today }" type="date" pattern="yyyy/MM/dd (E)"/><br>
	시간: <fmt:formatDate value="${ today }" type="time" pattern="(a) hh:mm:ss"/><br>
	
</body>
</html>







