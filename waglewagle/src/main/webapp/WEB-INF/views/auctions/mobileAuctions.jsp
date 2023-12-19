<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
    <title>거래 확정 모바일 버전</title>
    <link rel="stylesheet" href="/resources/css/auctions/mobileAuctions.css">
</head>
<body>
    <div id="container">
        <div id="logo">
        </div>
        <div id="content">
            <div id="auction_img">

            </div>
            <div id="auction_goods">
                <div id="auction_title">
                    <div id="title">
                        판매 상품
                    </div>
                    <div>망고미 잠옷</div>
                </div>
                <div id="auction_price">
                    <div id="price">
                        낙찰 가격
                    </div>
                    <div>50000원</div>
                    
                </div>
                <div id="auction_comment">
                    해당 상품의 거래가 완료되었나요?
                </div>
            </div>
        </div>
        <div id="btns">
            <div id="auc_com">
                <div id="auc_com_space">
                    <img src="/resources/images/mobile/complete_img.png" id="com_img">
                    <div id="auc_com_btn">거래 완료하기</div>
                </div>
            </div>
            <div id="report">
                <div id="report_space" onClick="goReport()">
                    <img src="/resources/images/mobile/report_img.png" id="report_img">
                    <div id="auc_report_btn">거래 파기 신고하기</div>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript">
    	
    	// 신고하기 페이지로 이동
    	function goReport() {
    		const goods_id = 2;
    		location.href = "/auctions/${goods_id}/report";
    	}
    
    </script>
</body>
</html>