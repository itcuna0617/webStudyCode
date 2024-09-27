<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1 align="center">핸들러 메소드의 파라미터와 어노테이션</h1>
	
	<h3>1. HttpServletRequest로 요청 파라미터 전달 받기</h3>
	<button onclick="location.href='${pageContext.servletContext.contextPath}/first/regist'">
		파라미터 전달하기
	</button>
	
	<h3>2. @RequestParam 이용하여 파라미터 전달 받기</h3>
	<button onclick="location.href='${pageContext.servletContext.contextPath}/first/modify'">
		@RequestParam 이용하기
	</button>
	
	<h3>3. @ModelAttribute를 이용하여 파라미터 전달 받기</h3>
	<button onclick="location.href='${pageContext.servletContext.contextPath}/first/search'">
		@ModelAttribute 이용하기
	</button>
	
	<h3>4. HttpSession 이용하기</h3>
	<button onclick="location.href='${pageContext.servletContext.contextPath}/first/login'">
		session에 정보 담기
	</button>
	
	<h3>5. @RequestBody를 이용하여 파라미터 전달 받기(feat. @RequestHeader, @CookieValue)</h3>
	<button onclick="location.href='${pageContext.servletContext.contextPath}/first/body'">
		@RequestBody 이용하기
	</button>
	
	<!-- @RequestBody를 주로 사용하는 예시를 위한 추가 예제 -->
	<button onclick="test()">json 문자열 fetch api로 비동기 방식으로 요청하고 뽑아보기</button>
	<script type="text/javascript">
		function test() {
			console.log('눌렸나');
			
			fetch('${pageContext.servletContext.contextPath}/first/test',{
				method: 'POST',
				headers: {
				      'Content-Type': 'application/json'
				    },
				body: JSON.stringify({message: '메세지 내용'})		// js의 객체를 json문자열 형태로 바꿈
			})
		}
	</script>
	
	<!-- PathVariable 형태의 요청값 받아보는 추가 예제(Rest요청 시 사용할 수 있음) -->
	<h3>GET: /delete/{orderNo}</h3>
	<button onclick="location.href='${pageContext.servletContext.contextPath}/first/delete/3'"> 
		GET 주문 삭제하기 요청
	</button>
	
</body>
</html>












