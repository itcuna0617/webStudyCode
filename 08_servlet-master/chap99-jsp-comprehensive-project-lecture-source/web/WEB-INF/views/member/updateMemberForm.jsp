<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 뒤로가기로 회원가입 페이지 보는 것을 방지하기 위한 스크립트 -->
<script type = "text/javascript" >
		setTimeout(function(){
	    	window.history.forward();
	    }, 0);	
</script>
</head>
<body>
	<jsp:include page="../common/menubar.jsp"/>
	
	<div class="outer outer-member-insert">
		<br>
		<h2 align="center">회원 정보 수정</h2>
		
		<!-- 회원 정보 수정 폼 -->
		<form id="form" action="" method="post">
			<table align="center">
				<tr>
					<td width="200px">* 아이디</td>
					<td><input type="text" maxlength="13" name="memberId" id="memberId" readonly value="${ sessionScope.loginMember.id }"></td>
					<td width="100px"></td>
				</tr>	
				<tr>
					<td>* 비밀번호</td>
					<td><input type="password" maxlength="13" name="memberPwd" required></td>
					<td></td>
				</tr>
				<tr>
					<td>* 닉네임</td>
					<td><input type="text" maxlength="5" name="nickname" required value="${ sessionScope.loginMember.nickname }"></td>
					<td></td>
				</tr>
				<tr>
					<td>연락처</td>
					<td><input type="tel" name="phone" value="${ sessionScope.loginMember.phone }"></td>
					<td></td>
				</tr>
				<tr>
					<td>이메일</td>
					<td><input type="email" name="email" value="${ sessionScope.loginMember.email }"></td>
					<td></td>
				</tr>
				<tr>
					<td>우편번호</td>
					<td><input type="text" name="zipCode" id="zipCode" readonly></td>
					<td><input type="button" value="검색" class="btn btn-yg" id="searchZipCode"></td>
				</tr>
				<tr>
					<td>주소</td>
					<td><input type="text" name="address1" id="address1" readonly></td>
					<td></td>
				</tr>	
				<tr>
					<td>상세주소</td>
					<td><input type="text" name="address2" id="address2"></td>
					<td></td>
				</tr>
			</table>
			
			<br>
			
			<div class="btns" align="center">
				<input type="reset" value="메인으로" class="btn btn-yg" id="goMain" onclick="location.href='${ pageContext.servletContext.contextPath }'">
				<input type="button" value="수정하기" class="btn btn-or" onclick="postRequest('updateMember')">
				<input type="button" value="탈퇴하기" class="btn btn-danger" onclick="postRequest('deleteMember')">
			</div>			
		</form>
	</div> <!-- outer end -->
	
	<!-- 다음 우편번호 api -->
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
		const $searchZipCode = document.getElementById("searchZipCode");
		const $goMain = document.getElementById("goMain");
		
		$searchZipCode.onclick = function(){
			
			new daum.Postcode({
				oncomplete : function(data){
					document.getElementById("zipCode").value = data.zonecode;
					document.getElementById("address1").value = data.address;
					document.getElementById("address2").focus();
				}
			}).open();
		}
		
		$goMain.onclick=function(){
			location.href = "${ pageContext.servletContext.contextPath}";
		}
	</script>
	<script>
		function postRequest(intent){
			var $form = document.getElementById("form");
			var passwordValue = document.getElementsByName("memberPwd")[0].value;
			
			// "", null, undefined, 0, NaN에 해당되면 false
			if(!passwordValue || passwordValue === "") {	// 사용자가 비번을 입력하지 않았을 때
				alert("비밀번호는 반드시 입력해야 합니다.");
				document.getElemetsByName("memberPwd")[0].focus();
			}
			
			requestPath = "<%=request.getContextPath()%>";
			
			switch(intent){
				case "updateMember" : requestPath += "/member/update"; break;
				case "deleteMember" : requestPath += "/member/delete"; break;
			}
			
			$form.action = requestPath;
			$form.submit();
		}
	</script>
	
</body>
</html>
















