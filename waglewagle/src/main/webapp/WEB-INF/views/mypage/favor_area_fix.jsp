<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="/resources/css/mypage/favor_area_fix.css">
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</head>
<body>
	<div id="favorFixContainer">
	
		<div id="favorListBox">
			<div id="flbTitle">현재 관심지역</div>
			<div id="flbItems">
				<!-- 반복 -->
				<c:if test="${favor_areas_count >= 3 }">
					<c:forEach var="item" items="${favor_areas }">
						<c:set var="addrStrs" value="${fn:split(item, ' ')}"/>
						<div class="favorArea">
							<div>${addrStrs[fn:length(addrStrs)-1]}</div>
							<img src="/resources/images/close_icon.png" onclick="deleteArea(event)">
							<input type="hidden" value="${item}">
						</div>
					</c:forEach>
				</c:if>
				
				<c:if test="${favor_areas_count < 3 }">
					<c:if test="${favor_areas_count > 0}">
						<c:forEach var="item" items="${favor_areas }">
							<c:set var="addrStrs" value="${fn:split(item, ' ')}"/>
							<div class="favorArea">
								<div>${addrStrs[fn:length(addrStrs)-1]}</div>
								<img src="/resources/images/close_icon.png" onclick="deleteArea(event)">
								<input type="hidden" value="${item}">
							</div>
						</c:forEach>
					</c:if>
					<div id="addArea" onclick="addArea()">
						+
					</div>
				</c:if>
				<!-- /반복 -->
			</div>
		</div>
		
		<div id="saveButton" onclick="saveAreas()">관심지역 저장</div>
		
	</div>
	<script src="/resources/js/mypage/favor_area_fix.js"></script>
</body>
</html>