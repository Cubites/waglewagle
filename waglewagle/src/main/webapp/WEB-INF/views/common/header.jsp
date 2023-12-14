<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<header>
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
</header>
<body>
	<div id="header">
		<div id="headerContainer">
			<div id="headerTop">
				<a href="#" id="headerTopLeft">
					<img alt="logo" src="/resources/images/logo.png">
				</a>
				<div id="headerTopCenter" class="text_center">
					<div id=searchBox>
						<img alt="search" src="/resources/images/search.png">
						<input type="text" placeholder="상품명 혹은 지역명을 검색해보세요!">
					</div>
				</div>
				<div id="headerTopRight">
					<c:if test='${!empty sessionScope.users_info.users_nick}'>
						<div>${sessionScope.users_info.users_nick} 님</div>
					</c:if>
					<c:if test='${empty sessionScope.users_info.users_nick}'>
						<div class="text_center">
							<a href="#" class="text_center">로그인</a>
						</div>
					</c:if>
					<a href="#" class="text_center">
						<img src="/resources/images/mypageIcon.png">
					</a>
				</div>
			</div>
			
			<div id="headerBottom">
				<div id="headerBottomLeft">
					<img src="/resources/images/menu.png">
					<ul>
						<li>
							<a href="#">패션 / 뷰티</a>
							<ul>
								<li><a href="#">여성의류</a></li>
								<li><a href="#">남성의류</a></li>
								<li><a href="#">신발</a></li>
								<li><a href="#">어린이</a></li>
								<li><a href="#">신발</a></li>
								<li><a href="#">가방 / 지갑</a></li>
								<li><a href="#">시계</a></li>
								<li><a href="#">주얼리</a></li>
								<li><a href="#">미용</a></li>
							</ul>
						</li>
						<li>
							<a href="#">전자기기</a>
							<ul>
								<li><a href="#">노트북</a></li>
								<li><a href="#">데스크탑</a></li>
								<li><a href="#">웨어러블</a></li>
								<li><a href="#">휴대폰</a></li>
								<li><a href="#">태블릿</a></li>
								<li><a href="#">컴퓨터 주변기기</a></li>
								<li><a href="#">카메라</a></li>
								<li><a href="#">콘솔 게임기</a></li>
							</ul>
						</li>
						<li>
							<a href="#">반려동물</a>
							<ul>
								<li><a href="#">소모품</a></li>
								<li><a href="#">옷</a></li>
								<li><a href="#">장난감</a></li>
							</ul>
						</li>
						<li>
							<a href="#">생활 / 가전</a>
						</li>
						<li>
							<a href="#">도서 / 음반</a>
						</li>
						<li>
							<a href="#">기타</a>
						</li>
					</ul>
					<!-- 
					<ul>
						<c:forEach var="cate1" items="${categoryFirst}" varStatus="status1">
							<li>
								<a href="#">${cate1.value}</a>
								<ul>
									<c:forEach var="cate2" items="${categorySecond[cate1.key]}">
										<li><a href="#">${cate2}</a>
									</c:forEach>
								</ul>
							</li>
						</c:forEach>
					</ul>
					-->
				</div>
				<div id="headerBottomCenter">
					<a href="#" class="text_center">공지사항</a>
					<a href="#" class="text_center">문의하기</a>				
				</div>
				<a href="#" id="headerBottomRight">
					<img src="/resources/images/shopping.png">
					<span>상품등록</span>
				</a>
			</div>
		</div>
	</div>
</body>
</html>