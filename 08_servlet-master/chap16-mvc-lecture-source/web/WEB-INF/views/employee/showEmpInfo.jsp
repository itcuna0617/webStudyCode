<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="../common/header.jsp"/>
	<h3>사원번호: ${ selectedEmp.empId }</h3>
	<h3>사원명: ${ selectedEmp.empName }</h3>
	<h3>부서코드: ${ selectedEmp.deptCode }</h3>
	<h3>직급코드: ${ selectedEmp.jobCode }</h3>
	<h3>급여: ${ selectedEmp.salary }</h3>
</body>
</html>