<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>거래 확정 신고 모바일 버전</title>
    <link rel="stylesheet" href="/resources/css/auctions/mobileAuctionsReport.css">
</head>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	
	// 신고 사유 토글 보였다 안보였다 하는 함수
	function clickToggle() {
		const menu = window.getComputedStyle(document.getElementById('menu')).getPropertyValue('height');
		menu.style.height = 0;
		menu.style.minHeight = 0;
		menu.style.opacity = 0;
		console.log(menu);
		// $('#menu').css(window.getComputedStyle(menu).getPropertyValue('height'), 0);
		//$('#menu').css("min-height", 0);
		//$('#menu').css("opacity", 0);
	}
	
	// 선택한 신고 사유 보이도록 하는 함수
	function clickReason(e) {
		//console.log(document.getElementByClassName('menu-item').value);
		console.log(e.target.value);
		const reason = e.target.value;
		if (reason == 1) {
			$("#after_select_1").show();
			$("#before_select").hide();
			$("#after_select_2").hide();
			$("#after_select_3").hide();
			$("#toggle_img").hide();
		} else if (reason == 2) {
			$("#after_select_2").show();
			$("#before_select").hide();
			$("#after_select_1").hide();
			$("#after_select_3").hide();
			$("#toggle_img").hide();
		} else {
			$("#after_select_3").show();
			$("#before_select").hide();
			$("#after_select_1").hide();
			$("#after_select_2").hide();
			$("#toggle_img").hide();
		}
		const menu = window.getComputedStyle(document.getElementById('menu')).getPropertyValue('height');
		menu.style.height = 0;
		menu.style.minHeight = 0;
		menu.style.opacity = 0;
	}
	
</script>
<body>
    <div id="container">
        <div id="logo">
        </div>
        <div id="content">
            <div id="report_reasons">
                <input type="checkbox" id="toggle" hidden>
                <label for="toggle" id="label" onClick="clickToggle()">
                    <img src="/resources/images/mobile/toggle_img.png" id="toggle_img">
                    <p id="before_select">신고 사유 선택하기</p>
                    <p id="after_select_1">"이미지와 실제 상품이 너무 다름"</p>
                    <p id="after_select_2">"판매자가 거래 장소에 나오지 않음"</p>
                    <p id="after_select_3">"기타 사유"</p>
                </label>
                <ul id="menu">
                    <li class="menu-item" onClick="clickReason(event)" value="1">이미지와 실제 상품이 너무 다름</li>
                    <li class="menu-item" onClick="clickReason(event)" value="2">판매자가 거래 장소에 나오지 않음</li>
                    <li class="menu-item" onClick="clickReason(event)" value="3">기타 사유</li>
                </ul>
            </div>
            <textarea id="report_detail" placeholder="신고 사유를 &#13;&#10; 상세히 작성해주세요."></textarea>
        </div>
        <div id="btns">
            <div id="report_com">
                <div id="report_com_space">
                    <img src="/resources/images/mobile/complete_img.png" id="com_img">
                    <div id="report_com_btn">신고하기</div>
                </div>
            </div>
            <div id="back">
                <div id="back_space">
                    <img src="/resources/images/mobile/back_img.png" id="back_img">
                    <div id="back_btn">돌아하기</div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>