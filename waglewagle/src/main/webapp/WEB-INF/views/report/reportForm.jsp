<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sample</title>
<link rel="stylesheet" href="/resources/css/common/common.css">
<link rel="stylesheet" href="/resources/css/report/report.css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	
	let reportResult = "${result}";
	
	$(function() {

		$("#submit").click(function() {

			showmodal("정말로 신고하시겠습니까?","report");

		});
		
		if (reportResult !== "") {
			
			showmodal(reportResult,"result");
		}
		
		
		$("basicModal").click

	})

	function showmodal(text, status) {
		
		console.log(status);
		if (status === "result") {

			let modalContent = $("#basicModal-Content");
			modalContent.text(text);
			$("#basicModal").fadeIn();
			
			setTimeout(()=>{
				$("#basicModal").fadeOut();
			},2000)
			
			
			setTimeout(() => {
				location.href="/goods/${goods_id}";				
			}, 1000);
		}else{

		let submitModal = $("#submitModalText");
		submitModal.html(text);
		$("#submitModal").fadeIn();
		}

	}

	function isOk(status) {

		if (status === true) {

			$("#reportForm").submit();

		}

		$("#submitModal").fadeOut();
	}
</script>

<style type="text/css">
.modal {
	position: absolute;
	width: 100%;
	height: 100%;
	min-height: 720px;
	left: 0;
	top: 0;
	display: none;
}

.modal-Content {
	border: 1px solid black;
	width: 400px;
	height: 200px;
	background: #fff;
	border-radius: 10px;
	position: relative;
	top: 45%;
	left: 50%;
	margin-top: -100px;
	margin-left: -200px;
	text-align: center;
	box-sizing: border-box;
	padding: 80px 0;
	line-height: 23px;
	color: #ed5565;
	z-index: 20;
}

#submitModal {
	display: flex;
	flex-direction: column;
	display: none;
}

#submitModalText+div {
	display: flex;
	flex-direction: row;
	width: auto;
	height: 100%;
	line-height: 30px;
	justify-content: space-evenly;
}

.submitFont {
	margin-top: 20px;
	color: black;
	font-weight: bold;
	border-color: 1px;
	display: inline-block;
	height: 30px;
	width: 70px;
	border-radius: 7px;
	cursor: pointer;
}

#submitModalText {
	color: #ed5565;
	font-weight: bold;
}

#submitModal-Content {
	padding: 60px 0;
}
</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp"%>
	<div id="center">
		<div id="container">
			<div id="wrap">
				<form id="reportForm" action="/report/goods" method="post"
					enctype="application/x-www-form-urlencoded">
					<input type="hidden" value="${goods.users_id}" name="users_id">
					<input type="hidden" value="${goods.goods_id}" name="goods_id">
					<input type="hidden" value="2" name="reports_type">
					<div id="reportBox">

						<div id="reportTop">${goods.goods_title}</div>
						<div id="reportMid">
							<textarea name="reports_content" id="textBox"
								placeholder="
            
            
        신고 사유를 자세하게 적어주세요

        - 부적절한 상품 

        - 과도한 가격

        - 사기 전과가 있는 판매자
            ">


            </textarea>

						</div>
				</form>
				<div id="reportFooter">
					<div id="submit">신고하기</div>
					<div id="cancle">취소하기</div>
				</div>
			</div>
		</div>
	</div>
	</div>
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
	<!-- 모달창 -->
	<div id="basicModal" class="modal">
		<div id="basicModal-Content" class="modal-Content"></div>
	</div>
	<!-- 확인 취소 모달 -->
	<div id="submitModal" class="modal">
		<div id="submitModal-Content" class="modal-Content">
			<div id="submitModalText"></div>
			<div>
				<div id="modalOk" onclick="isOk(true)">
					<span class="submitFont">확인</span>
				</div>
				<div id="modalCancle" onclick="isOk(false)">
					<span class="submitFont">취소</span>
				</div>
			</div>
		</div>
	</div>
</body>
</html>