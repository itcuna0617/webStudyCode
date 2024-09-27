window.onload = function() {
	
	/* 화면에 랜더링 된 태그들이 존재하지 않는 경우 에러 발생 가능성이 있어서 if문으로 태그가 존재하는지부터
	확인하고 이벤트를 연결한다. */
	if(document.getElementById("regist")){
		const $regist = document.getElementById("regist");
		$regist.onclick = function(){
			location.href = "/jsp/member/regist";
		}
	}
	
	if(document.getElementById("logout")){
		const $logout = document.getElementById("logout");
		$logout.onclick = function(){
			location.href = "/jsp/member/logout";
		}
	}
	
	if(document.getElementById("update")){
		const $update = document.getElementById("update");
		$update.onclick = function(){
			location.href = "/jsp/member/update";
		}
	}
	
	if(document.getElementById("writeNotice")){
		const $writeNotice = document.getElementById("writeNotice");
		$writeNotice.onclick = function(){
			location.href = "/jsp/notice/insert";
		}
	}
	
	if(document.getElementById("writeBoard")){
		const $writeBoard = document.getElementById("writeBoard");
		$writeBoard.onclick = function(){
			location.href = "/jsp/board/insert";
		}
	}
	
	if(document.getElementById("writeThumbnail")){
		const $writeThumbnail = document.getElementById("writeThumbnail");
		$writeThumbnail.onclick = function(){
			location.href = "/jsp/thumbnail/insert";
		}
	}

}




















