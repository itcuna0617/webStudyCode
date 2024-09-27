<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<h1 align="center">jackson 라이브러리 이용하기</h1>
	
	<h3>1. stream을 이용한 전송</h3>
	<button id="jackson1">정보 조회하기</button>
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
	<script>
		const $button1 = document.getElementById("jackson1");
		$button1.onclick = function () {

			$.ajax({
				url:"jackson1",
				success:function(data){
					console.log(data);
					console.table(data);
										
					const $table = document.querySelector("#memberInfo1 tbody");
					$table.innerHTML = "";
					
					for(var index in data){
						$tr = document.createElement("tr");
						
						$noTd = "<td>" + data[index].no + "</td>";
						
						$nameTd = document.createElement("td");
						$nameTd.textContent = data[index].name;
						$ageTd = document.createElement("td");
						$ageTd.textContent = data[index].age;
						$enrollDateTd = document.createElement("td");
						$enrollDateTd.textContent = data[index].enrollDate;
						
						$tr.insertAdjacentHTML('beforeend', $noTd);
						
						$tr.appendChild($nameTd);
						$tr.appendChild($ageTd);
						$tr.appendChild($enrollDateTd);
						
						$table.appendChild($tr);
					}
				},
				error:function(error){
					console.log(error);
				}
			});
		};
	</script>
	
	<h3>2. @ResponseBody를 이용한 응답</h3>
	<button id="jackson2">정보 조회하기</button>
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
	<script>
		const $button2 = document.getElementById("jackson2");
		$button2.onclick = function () {

			$.ajax({
				url:"jackson2",
				success:function(data){
					console.log(data);
					console.table(data);
										
					const $table = document.querySelector("#memberInfo2 tbody");
					$table.innerHTML = "";
					
					for(var index in data){
						$tr = document.createElement("tr");
						
						$noTd = "<td>" + data[index].no + "</td>";
						
						$nameTd = document.createElement("td");
						$nameTd.textContent = data[index].name;
						$ageTd = document.createElement("td");
						$ageTd.textContent = data[index].age;
						$enrollDateTd = document.createElement("td");
						$enrollDateTd.textContent = data[index].enrollDate;
						
						$tr.insertAdjacentHTML('beforeend', $noTd);
						
						$tr.appendChild($nameTd);
						$tr.appendChild($ageTd);
						$tr.appendChild($enrollDateTd);
						
						$table.appendChild($tr);
					}
				},
				error:function(error){
					console.log(error);
				}
			});
		};
	</script>
	
	<h3>3. @ResponseBody와 MessageConverter를 사용해서 자동 변환 후 응답</h3>
	<button id="jackson3">정보 조회하기</button>
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
		const $button3 = document.getElementById("jackson3");
		$button3.onclick = function () {

			$.ajax({
				url:"jackson3",
				success:function(data){
					console.log(data);
					console.table(data);
										
					const $table = document.querySelector("#memberInfo3 tbody");
					$table.innerHTML = "";
					
					for(var index in data){
						$tr = document.createElement("tr");
						
						$noTd = "<td>" + data[index].no + "</td>";
						
						$nameTd = document.createElement("td");
						$nameTd.textContent = data[index].name;
						$ageTd = document.createElement("td");
						$ageTd.textContent = data[index].age;
						$enrollDateTd = document.createElement("td");
						$enrollDateTd.textContent = data[index].enrollDate;
						
						$tr.insertAdjacentHTML('beforeend', $noTd);
						
						$tr.appendChild($nameTd);
						$tr.appendChild($ageTd);
						$tr.appendChild($enrollDateTd);
						
						$table.appendChild($tr);
					}
				},
				error:function(error){
					console.log(error);
				}
			});
		};
	</script>
	
	<h3>4. jsonView를 이용한 ModelAndView 응답</h3>
	<button id="jackson4">정보 조회하기</button>
	<table id="memberInfo4" border="1">
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
		const $button4 = document.getElementById("jackson4");
		$button4.onclick = function () {

			$.ajax({
				url:"jackson4",
				success:function(data){
					
					/*
						ModelAndView에서 memberList란 이름의 Model객체로 한번 더 감싸져서 넘어왔기 때문에
						data.memberList로 접근 후 파싱 처리를 한번 더 해 주어야 한다.
					*/
					console.log(JSON.parse(data.memberList));
					console.table(JSON.parse(data.memberList));
									
					const memberList = JSON.parse(data.memberList);
					const $table = document.querySelector("#memberInfo4 tbody");
					$table.innerHTML = "";
					
					for(var index in memberList){
						$tr = document.createElement("tr");
						
						$noTd = "<td>" + memberList[index].no + "</td>";
						
						$nameTd = document.createElement("td");
						$nameTd.textContent = memberList[index].name;
						$ageTd = document.createElement("td");
						$ageTd.textContent = memberList[index].age;
						$enrollDateTd = document.createElement("td");
						$enrollDateTd.textContent = memberList[index].enrollDate;
						
						$tr.insertAdjacentHTML('beforeend', $noTd);
						
						$tr.appendChild($nameTd);
						$tr.appendChild($ageTd);
						$tr.appendChild($enrollDateTd);
						
						$table.appendChild($tr);
					}
				},
				error:function(error){
					console.log(error);
				}
			});
		};
	</script>
</body>
</html>