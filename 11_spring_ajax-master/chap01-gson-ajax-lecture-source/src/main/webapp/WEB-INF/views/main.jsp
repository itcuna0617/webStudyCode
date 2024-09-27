<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
</head>
<body>
	<h1 align="center">Gson을 이용한 Ajax</h1>
	
	<h3>1. stream을 이용한 전송</h3>
	<button id="gson1">정보 조회하기</button>
	<table id="memberInfo1" border="1">
		<thead>
			<tr>
				<th width="100">회원번호</th>
				<th width="150">이름</th>
				<th width="100">나이</th>
				<th width="200">가입일</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
	
	<script type="text/javascript">
		const $button1 = document.getElementById("gson1");
		$button1.onclick = function () {
			$.ajax({
				url: "gson1",
				
				/*
				
					1. java 측에서 application/json으로 넘어왔을 경우
				success: function(data) {
				*/
				
				/* 2. JS 측에서 JSON.parse로 해석하는 경우 */
				success: function(data1) {
					const data = JSON.parse(data1);		// text/plain으로 넘어온 데이터를 JSON으로 인식

					console.log("ajax 통신 성공!");
					console.log(data);
					console.table(data);
					
					const $table = document.querySelector("#memberInfo1 tbody");
					$table.innerHTML = "";	// tbody 리셋 구문	
					
					/* 넘어온 데이터로 tr태그들을 만들어 tbody 태그에 넣어보기 */
					for(var index in data) {
						$tr = document.createElement("tr");
						
						/* 태그를 문자열과 createElement로 만드는 각각의 방법 */
						/* 
						$noTd = document.createElement("td");
						$noTd.textContent = data[index].no; 
						*/
						$noTd = "<td>" + data[index].no + "</td>";
						
						$nameTd = document.createElement("td");
						$nameTd.textContent = data[index].name;
						$ageTd = document.createElement("td");
						$ageTd.textContent = data[index].age;
						$enrollDateTd = document.createElement("td");
						$enrollDateTd.textContent = data[index].enrollDate;
						
						/*
						$tr.appendChild($noTd);				
						*/
						$tr.insertAdjacentHTML('beforeend', $noTd);
						
						$tr.appendChild($nameTd);
						$tr.appendChild($ageTd);
						$tr.appendChild($enrollDateTd);
						
						$table.appendChild($tr);
					}
				},
				error: function(error) {
					console.log("ajax 통신 실패!");
				}
			});
		};
	</script>
	
	<h3>2. @ResponseBody를 이용한 전송</h3>
	<button id="gson2">정보 조회하기</button>
	<table id="memberInfo2" border="1">
		<thead>
			<tr>
				<th width="100">회원번호</th>
				<th width="150">이름</th>
				<th width="100">나이</th>
				<th width="200">가입일</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
	<script type="text/javascript">
		$("#gson2").click(function() {
			$.ajax({
				url:"gson2",
				type:"get",
				data: {					// JS의 객체를 실어보냄(쿼리스트링으로 해석 됨)
					name:'피카추',
					age:20					
				},
				
				/*
				success: function(data1) {
					const data = JSON.parse(data1);
				*/
				success: function(data) {
					console.log(data);
					console.table(data);
					
					/* 이후는 테이블 id만 바꿔서 첫번째 예제의 DOM 추가하는 부분 복붙해서 확인하자. */
				},
				error: function(error) {
					console.log(error);
				}
			});	
		});
	</script>
	
	<h3>3. jsonView를 이용한 BeanNameViewResolver로 전송</h3>
	<button id="gson3">정보 조회하기</button>
	<table id="memberInfo3" border="1">
		<thead>
			<tr>
				<th width="100">회원번호</th>
				<th width="150">이름</th>
				<th width="100">나이</th>
				<th width="200">가입일</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
	<script>
		$("#gson3").click(function() {
			$.ajax({
				url:"gson3",
				type: "post",
				headers: {
					'Content-type' : 'application/json'
				},
				data: JSON.stringify({name: '피카추', age: 15}),	// 화면단에서 JSON 문자열 넘기기
				success: function(data) {
					console.log(JSON.parse(data.memberList));
					console.table(JSON.parse(data.memberList));
				},
				error: function(error) {
					console.log(error)
				}
			});			
		});
	</script>
</body>
</html>








