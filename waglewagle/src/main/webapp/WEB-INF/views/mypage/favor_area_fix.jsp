<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="/resources/css/mypage/favor_area_fix.css">
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</head>
<body>
	<div id="favorFixContainer">
	
		<div id="favorListBox">
			<div id="flbTitle">현재 관심지역</div>
			<div id="flbItems">
				<!-- 반복 -->
				<div class="favorArea">
					<div>연남동</div>
					<img src="/resources/images/close_icon.png" onclick="deleteArea(event)">
					<input type="hidden" value="">
				</div>
				<div class="favorArea">
					<div>동교동</div>
					<img src="/resources/images/close_icon.png" onclick="deleteArea(event)">
					<input type="hidden" value="">
				</div>
				<div id="addArea" onclick="addArea()">
					+
				</div>
				<!-- /반복 -->
			</div>
		</div>
		
		<div id="saveButton" onclick="saveAreas()">관심지역 저장</div>
		
	</div>
	<script src="/resources/js/mypage/favor_area_fix.js"></script>
</body>
</html>