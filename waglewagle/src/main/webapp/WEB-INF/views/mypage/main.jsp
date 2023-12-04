<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>My Page - main</title>
	<link rel="stylesheet" href="/resources/css/common/common.css">
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
	<div id="center">
		<div id="container">
			<div id="mypageTop">
				<div id="userInfoBox">
					<div id="userInfoLeft">
						<div id="userBox">
							<div id="user_name">레오나르도 망곰</div>
							<div>
								<img src="/resources/images/default_profile.png">
							</div>
							<p>호가 가능 포인트</p>
							<div>
							
							</div>
							<p>총 소지 포인트</p>
							<div>
								<img>
								<span></span>
							</div>
							<div><a href="#">충전하기</a></div>
						</div>
					</div>
					<div id="userInfoRight">
					
					</div>
				</div>
			</div>
			<div id="mypageBottom">
			
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>