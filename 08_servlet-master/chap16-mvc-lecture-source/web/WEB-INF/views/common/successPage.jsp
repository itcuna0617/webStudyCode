<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="header.jsp"/>
	<script>
		function resultMessage() {
			/* alert('${ requestScope.successCode }'); */
			
			var successCode = '${ requestScope.successCode }';
			
			var successMessage = '';
			var movePath = '';
			
			switch(successCode){
				case 'insertEmp' :
					successMessage = '신규 직원 등록 성공!';
					movePath = '${pageContext.servletContext.contextPath}';			
				break;
				case 'updateEmp' :
					successMessage = '직원 정보 수정 성공!';
					movePath = '${pageContext.servletContext.contextPath}';			
				break;
				case 'deleteEmp' :
					successMessage = '직원 정보 삭제 성공!';
					movePath = '${pageContext.servletContext.contextPath}';			
				break;
			}
			alert(successMessage);
			location.href = movePath;
		}
		
		resultMessage();
	</script>
</body>
</html>




