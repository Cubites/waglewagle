<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="/resources/css/mypage/auctions.css">
	<link rel="stylesheet" href="/resources/css/mypage/auctions_end.css">
</head>
<body>
	<div id="auctionsContainer">
		<div id="acTitle">'<span>레오나르도 망곰</span>'님의 유찰 상품 목록</div>
		<div id="itemsList">
			<!-- 반복 -->
			<c:forEach var="item" items="${ListData}">
				<a class="itemBox" href="#">
					<img class="wayMark" src='/resources/images/sell_icon.png'>
					<div 
						class="thumbnailBox"
						style="background-image: url('/resources/images/${item.goods_th_img}')"
					>
						<img class="stampImg" src="/resources/images/auctions_fail_icon.png">
					</div>
					<div class="itemTitle">${item.goods_title}</div>
					<div class="varPriceBox"></div>
					<div class="staticValuesBox">
						<p>시작가 : <fmt:formatNumber value="${item.goods_start_price}" pattern="#,###" />원</p>
						<p>시작일 : <fmt:formatDate value="${item.post_date}" pattern="yyyy.MM.dd"/></p>
						<p>마감일 : <fmt:formatDate value="${item.goods_exp}" pattern="yyyy.MM.dd"/></p>
					</div>
				</a>
			</c:forEach>
			<!-- /반복 -->
		</div>
		<!-- pagination -->
		<div id="pagingBox">
			<ul>
				<c:if test="${pageNum10 != 0}">
					<li><a href="/mypage/auctions_fail?page=${pageNum10}&scroll=0">&#60;</a></li>
				</c:if>
				<c:forEach var="num" begin="${pageNum10 + 1}" end="${(pageNumMax - pageNum10) < 10 ? pageNumMax : pageNum10 + 10}" step="1">
					<c:if test="${pageNum1 == num}">
						<li class="nowPage">
							<a href="/mypage/auctions_fail?page=${num}&scroll=0">${num}</a>
						</li>
					</c:if>
					<c:if test="${pageNum1 != num}">
						<li>
							<a href="/mypage/auctions_fail?page=${num}&scroll=0">${num}</a>
						</li>
					</c:if>
				</c:forEach>
				<c:if test="${(pageNumMax - pageNum10) > 10}">
					<li><a href="/mypage/auctions_fail?page=${pageNum10 + 11}&scroll=0">&#62;</a></li>
				</c:if>
			</ul>
		</div>
		<!-- /pagination -->
	</div>
	<script>
		window.scrollTo({top: "${scrollY}"});
		window.addEventListener("scroll", () => {
			let scrollVal = window.scrollY;
			let aTags = document.getElementById("pagingBox").querySelectorAll("a");
			for(let aa of aTags){
			    aa.href = aa.href.substr(0, aa.href.indexOf("&scroll=")) + "&scroll=" + scrollVal;
			}
		});
	</script>
</body>
</html>