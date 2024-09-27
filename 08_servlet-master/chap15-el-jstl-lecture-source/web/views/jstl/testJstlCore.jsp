<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1 align="center">JSTL Core Library Tag Test</h1>
	
	<h2>c:set 태그: 변수 선언(scope별 attribute 설정)</h2>
	
	<!-- jstl 문법을 쓰기 위해서는 jstl-1.2.jar라이브러리 뿐 아니라 taglib 지시자 태그까지 제대로 써야 한다. -->
	<c:set var="num1" value="100" scope="session"/>
	<c:set var="num2" value="200" scope="session"/>
	
	${ sessionScope.num1 }<br>
	${ sessionScope.num2 }<br>
	
	<!-- core 라이브러리의 set 태그를 쓸 때 scope를 쓰지 않으면 가장 작은 범위인 pageScope에 저장된다. -->
	<c:set var="sum" value="${ sessionScope.num1 + sessionScope.num2 }"/>
	
	${ pageScope.sum }<br>
	
	<hr>
	
	<h2>c:set 태그: 배열 또는 컬렉션으로 사용 할 문자열 선언</h2>
	<c:set var="colors">
		red, yellow, green, orange, blue, magenta
	</c:set>
	
	colors 배열 값 확인: ${ pageScope.colors }<br>	
	
	<!-- 자바스크립트에서도 EL문법을 사용할 수 있다.(싱글쿼테이션을 적용할 것) -->
	<script type="text/javascript">
		function testSet() {
			let colors = '${ pageScope.colors }'.split(", ");
			console.log(colors);
		}
		
		testSet();
	</script>
	
	<hr>
	
	<h2>c:remove 태그</h2>
	num1 변수 값: ${ sessionScope.num1 }<br>
	num2 변수 값: ${ sessionScope.num2 }<br>
	
	<!-- 삭제 시 해당 scope의 var 키값과 해당 value를 제거(scope 미 작성 시에는 page스코프를 의미) -->
	<c:remove var="num1" scope="session"/>
	<c:remove var="num2" scope="session"/>
	
	num1 변수 값: ${ sessionScope.num1 }<br>
	num2 변수 값: ${ sessionScope.num2 }<br>
	
	<!-- 
		escapeXml을 false로 지정하면 <, >로 둘러싸인 문자열을 태그로 인식하고,
		true로 지정하면 문자로 인식한다.
		(크로스사이트스크립팅(xss) 공격을 방지하기 위해서)
	 -->
	<h2>c:out 태그: 값을 클라이언트 웹브라우저 화면에 출력용(el 문법이나 표현식 태그와 비슷한 개념)</h2>
	<h2>이스케이프시퀀스: <, >, & 등의 특수문자를 &lt;와 &gt;와 &amp;로 바꿔서 인식한다.</h2>	
	<c:out value="core 라이브러리의 <out> 태그는 값을 화면에 출력하는 태그이다."/><br>
	
	<c:out value="<h2>데이터 출력</h2>" escapeXml="false"/><br>
	<c:out value="<h2>데이터 출력</h2>" escapeXml="true"/><br>	<!-- default가 true -->
	
	<c:out value="${ param.name }" default="김개똥씨"/><br>
	
	<hr>
	
	<h2>c:if 태그: 조건문</h2>
	<c:set var="value1" value="300" scope="page"/>
	<c:set var="value2" value="90" scope="page"/>
	value1의 값은 ${ value1 }이고,
	value2의 값은 <c:out value="${ value2 }"/>입니다.
	
	<!-- 
	  jstl의 core라이브러리에서 제공하는 if 태그의 test 속성에 el문법으로 조건식을 쓸 때 비교 대상이
	  숫자형으로 처리 될 수 있게 형변환을 유의해야 한다.
	 -->
	<c:if test="${ value1 + 0 gt value2 + 0 }">
		<h3>value1이 큽니다.: ${ value1 }</h3>
	</c:if>
	<c:if test="${ Integer.valueOf(value1) lt Integer.valueOf(value2) }">
		<h3>value2가 큽니다.: ${ value2 }</h3>
	</c:if>
	
	<hr>
	
	<h2>c:choose: switch문, c:when: case문, c:otherwise: default문</h2>
	<c:set var="num" value="3"/>
	
	num의 값은 <c:out value="${ num }"/>입니다.
	<c:choose>
		<c:when test="${ num + 0 eq 1 }"><h3>안녕하세요.</h3></c:when>
		<c:when test="${ num + 0 eq 2 }"><h3>반갑습니다.</h3></c:when>
		<c:otherwise><h3>환영합니다.</h3></c:otherwise>
	</c:choose>
	
	<h2>c:forEach: 배열 또는 컬렉션 연속 처리에 for-each문처럼 사용하는 법</h2>
	
	<c:forEach var="color" items="${ colors }" varStatus="st">
		<font color="${ color }">
			${ st.index } : 글자색 ${ color }
		</font>
	</c:forEach>
	
	<hr>
	
	<h2>c:forTokens 태그: 문자열을 토큰으로 분리 처리할 때 사용(자바의 StringTokenizer같은 것)</h2>
	<ul>
		<c:forTokens var="color" items="yellow blue pink red-green" delims=" -">
			<li>${ color }</li>
		</c:forTokens>
	</ul>
	
	<hr>
	
	<h2>c:url 태그: 링크 설정 정보 별도 저장 시 사용하는 태그</h2>
	
	<!-- 쿼리스트링을 가독성 좋게 만들어 줄 수 있는 url 및 param 태그 -->
	<c:url var="urlLink" value="testJstlCoreResult.jsp">
		<c:param name="num" value="77"/>
		<c:param name="name" value="박씨"/>
	</c:url>
	<a href="${ urlLink }">c:url을 통한 결과 화면 연결</a><br>
	<a href="testJstlCoreResult.jsp?num=77&name=박씨">쿼리스트링을 직접 작성해서 화면 연결</a>
	
</body>
</html>










