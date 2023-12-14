<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>와글와글</title>
	<link rel="stylesheet" href="/resources/css/common/common.css">
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
	<div id="center">
		<div id="container">
			<!-- 배너 -->
			<div id="bannerArea">
				<div id="banner"></div>
			</div>
			
			<!-- 실시간 인기 상품 -->
			<div id="popularProductsArea">
				
			</div>
			
			<!-- 신규 등록 물품 -->
			<div id="newRegProductsArea">
			
			</div>

			<!-- 내 주변 상품 -->
			<div id="nearProductsArea">
			
			</div>
			
		</div>
	</div>
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>