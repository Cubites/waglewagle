<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="/resources/css/common/common.css">
	<link rel="stylesheet" href="/resources/css/mypage/main.css">
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
	<div id="center">
		<div id="container">
			<div id="mypageTop">
				<div id="userInfoBox">
					<!-- 유저 정보 -->
					<div id="userInfoLeft" class="text-center">						
						<div id="userBox">
							<div id="user_name" class="text_center">${users_info.users_nick}</div>
							<div id="profile">
								<img src="/resources/images/default_profile.png">
							</div>
							<div id="pointBox" class="text-center">
								<div id="pointTop">
									<p class="text_center">호가 가능 포인트</p>
									<div id="pointDetail">
										<img src="/resources/images/info_icon.png">
										<div id="pointInfo">
											<div>
												현재 경매에 입찰을 할 수 있는<br> 최대 포인트입니다.
											</div>
										</div>
									</div>
								</div>
								<div id="pointBottom">
									<img src="/resources/images/coin.png">
									<div id="point"></div>
								</div>
							</div>
							<div id="pointTotalBox">
								<p class="text_center">총 소지 포인트</p>
								<div></div>
							</div>
							<div id="rechargeButton" class="text_center">
								<a href="#">충전하기</a>
							</div>
						</div>
					</div>
					<!-- /유저 정보 -->
					<div id="userInfoRight">
						<!-- 친밀도 -->
						<div id="favorBox">
							<div>'<span>${users_info.users_nick}</span>'님과 와글이의 사이</div>
							<div id="pointArea">
								<div id="pointAreaLeft">
									<div id="favorBar">
										<div id="favorRate" style="width: 20%"></div>
										<div id="favorPin" style="left: calc(20% - 100px);">
											<div>현재 와글이와의 사이</div>
											<div>${users_info.users_rel}</div>
											<img src="/resources/images/pin.png">
										</div>
										<div id="startEnd">
											<p>0</p>
											<p>79</p>
										</div>
									</div>
								</div>
								<div id="pointAreaRight">
									<img src="/resources/images/default_profile.png">
									<img src="/resources/images/info_icon.png">
									<div id="favorDetail">
										우리는 79 42 ^0^<br>
										우리는 조금 어색한 42<br>
										우리는 아는 42
									</div>
								</div>
							</div>
							<div>
								사용자님의 거래 신뢰도가 높아질 수록 와글이와 가까워져요!
							</div>
						</div>
						<!-- /친밀도 -->
						<!-- 거래수 -->
						<div id="logBox">
							<div>'<span>레오나르도 망곰</span>'님의 거래 현황</div>
							<div id="logArea">
								<div id="logAreaLeft" class="countSet">
									<div class="countTitle text-center">전체 거래수</div>
									<div class="countBox text-center">${countAuctions.auctions_total_count}</div>
								</div>
								<div id="logAreaCenter">
									<div class="countSet">
										<div class="countTitle text-center">경매 중</div>
										<div class="countBox text-center">${countAuctions.auctions_count}</div>
									</div>
									<img src="/resources/images/arrow.png">
									<div class="countSet">
										<div class="countTitle text-center">거래 중</div>
										<div class="countBox text-center">${countAuctions.auctions_ing_count}</div>
									</div>
									<img src="/resources/images/arrow.png">
									<div class="countSet">
										<div class="countTitle text-center">거래 완료</div>
										<div class="countBox text-center">${countAuctions.auctions_end_count}</div>
									</div>
								</div>
								<div id="logAreaRight" class="countSet">
									<div class="countTitle text-center">유찰 수</div>
									<div class="countBox text-center">${countAuctions.auctions_fail_count}</div>
								</div>
							</div>
						</div>
						<!-- /거래수 -->
					</div>
				</div>
			</div>
			<div id="mypageBottom">
				<div id="mypageMenu">
					<div class="mypageDetailMenu">
						<div>상품</div>
						<ul>
							<li><a href="/mypage/auctions">경매 중</a></li>
							<li><a href="/mypage/auctions_ing">거래 중</a></li>
							<li><a href="/mypage/auctions_end/sold">판매 완료</a></li>
							<li><a href="/mypage/auctions_end/bought">구매 완료</a></li>
							<li><a href="/mypage/auctions_break">파기 상품</a></li>
							<li><a href="/mypage/auctions_fail">유찰 상품</a></li>
							<li><a href="/mypage/favors_list">찜 상품</a></li>
						</ul>
					</div>
					<div class="mypageDetailMenu">
						<div>내 정보</div>
						<ul>
							<li><a href="/mypage/favors_list/fix">관심 지역 수정</a></li>
							<li><a href="/mypage/pwd/change">비밀번호 수정</a></li>
							<li><a href="#">문의 내역</a></li>
						</ul>
					</div>
					<div id="cancelMenu">
						<a href="#">회원 탈퇴</a>
					</div>
				</div>
				<div id="mypageList">
					<c:if test='${menuTab == 0 && menuNum == 0}'>
						<%@ include file="/WEB-INF/views/mypage/auctions.jsp" %>
					</c:if>
					<c:if test='${menuTab == 0 && menuNum == 1}'>
						<%@ include file="/WEB-INF/views/mypage/auctions_ing.jsp" %>
					</c:if>
					<c:if test='${menuTab == 0 && menuNum == 2}'>
						<%@ include file="/WEB-INF/views/mypage/auctions_end_sold.jsp" %>
					</c:if>
					<c:if test='${menuTab == 0 && menuNum == 3}'>
						<%@ include file="/WEB-INF/views/mypage/auctions_end_bought.jsp" %>
					</c:if>
					<c:if test='${menuTab == 0 && menuNum == 4}'>
						<%@ include file="/WEB-INF/views/mypage/auctions_fail.jsp" %>
					</c:if>
					<c:if test='${menuTab == 0 && menuNum == 5}'>
						<%@ include file="/WEB-INF/views/mypage/auctions_break.jsp" %>
					</c:if>
					<c:if test='${menuTab == 0 && menuNum == 6}'>
						<%@ include file="/WEB-INF/views/mypage/favors_list.jsp" %>
					</c:if>
					<c:if test='${menuTab == 1 && menuNum == 0}'>
						<%@ include file="/WEB-INF/views/mypage/favor_area_fix.jsp" %>
					</c:if>
					<c:if test='${menuTab == 1 && menuNum == 1}'>
						<%@ include file="/WEB-INF/views/mypage/pwd_change.jsp" %>
					</c:if>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
	<script>
		// 좌측 메뉴 목록에서 현재 메뉴에 하이라이트
		const menu = document.getElementsByClassName("mypageDetailMenu");
		let targetTag = menu[${menuTab}].querySelectorAll("ul>li")[${menuNum}]
		targetTag.querySelector("a").style.color = "#CF5C5C";
		targetTag.querySelector("a").style.fontWeight = "bold";

		// 포인트 값 추가
        let pointVal = ${pointInfo.point_usable};
        pointVal = pointVal.toLocaleString("ko-KR");
		let totalPointVal = ${pointInfo.point_total};
		totalPointVal = totalPointVal.toLocaleString("ko-KR");

        document.querySelector("#pointBottom>#point").innerHTML = pointVal;
		document.querySelector("#pointTotalBox>div").innerHTML = totalPointVal;

		// 친밀도 적용
		let favorVal = ${users_info.users_rel};
		favorVal = Math.floor(favorVal / 79 * 100);
		document.getElementById("favorRate").style.width = `${"${favorVal}"}%`;
		document.getElementById("favorPin").style.left = `calc(${"${favorVal}"}% - 100px)`;
	</script>
</body>
</html>