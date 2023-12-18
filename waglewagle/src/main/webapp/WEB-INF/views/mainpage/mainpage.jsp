<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>와글와글</title>
	<link rel="stylesheet" href="/resources/css/common/common.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />
	<link rel="stylesheet" href="/resources/css/mainpage/mainpage.css">
	<script src="https://code.jquery.com/jquery-latest.min.js"></script>
	<!-- Swiper JS -->
  	<script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>

	<!-- Initialize Swiper -->
	<script>
	
	$(function(){
    var swiper = new Swiper('.swiper', {
        slidesPerView: 5,
        spaceBetween: 30,
        loop: true,
        loopAdditionalSlides: 1,
        navigation: {
          nextEl: '.swiper-button-next',
          prevEl: '.swiper-button-prev',
        },
      });
		
	})
	$(function(){
    var swiper = new Swiper('.swiper.banner', {
        slidesPerView: 1,
        loop: true,
        loopedSlides: 1,
        autoplay:{
        	delay:3000,
        	disableOnInteraction: false,
        },
        navigation: {
          nextEl: '.swiper-button-next',
          prevEl: '.swiper-button-prev',
        },
      });
		
	})
		
	$(function(){
		$(".swiper-slide").mouseover(function(){
			let now = new Date();
			let expDate = new Date($(this).find(".popProduct").find(".avgPrice_expDateArea").find(".expArea").find(".productExp").text());
			let leftTime = expDate - now;
			
			const days = Math.floor(leftTime/(1000*60*60*24));
	     	const hours = ("0"+Math.floor(leftTime %(1000*60*60*24)/(1000*60*60))).slice(-2);

			
			$(".leftTime").text(days+"일 "+hours+"시간");
			$(this).find('.popProduct').find(".leftTime").removeClass("hide");
		})
		$(".swiper-slide").mouseout(function(){
			$(this).find('.popProduct').find(".leftTime").addClass("hide");
		})
	})

	</script>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
	<div id="center">
		<div id="container">
			<!-- 배너 -->
			<div id="bannerArea">
				<div id="banner">
					<!-- swiper -->
				<div class="swiper banner mySwiper">
					<div class="swiper-wrapper">
						<div class="swiper-slide">
							<img src="/resources/images/bannertest.png" >
						</div>
						<div class="swiper-slide">
							<img src="/resources/images/bannertest.png" >
						</div>
						<div class="swiper-slide">
							<img src="/resources/images/bannertest.png" >
						</div>
						<div class="swiper-slide">
							<img src="/resources/images/bannertest.png" >
						</div>
						<div class="swiper-slide">
							<img src="/resources/images/bannertest.png" >
						</div>
						<div class="swiper-slide">
							<img src="/resources/images/bannertest.png" >
						</div>
					</div>
					<div class="swiper-button-next"></div>
					<div class="swiper-button-prev"></div>
				</div>
				</div>
			</div>
			
			<!-- 실시간 인기 상품 -->
			<div id="popularProductsArea">
				<div id="popularTitle">
					실시간 인기 상품
				</div>
				<!-- swiper -->
				<div class="swiper mySwiper">
					<div class="swiper-wrapper hoverEl relative">
						<c:forEach var="item" items="${popular_list }">
							<div class="swiper-slide">
			                    <div class="popProduct">
				                    <div class="productImg">
				                   		<img src="/resources/images/default_profile.png">
				                    </div>
				                    <div class="productTitle">${item.goods_title }</div>
				                    <div class="avgPrice_expDateArea">
			                        	<div class="avgPriceArea">
			                        		<div class="avgPriceTitle">평균 입찰가</div>
				                        	<div class="productAvgPrice">${item.goods_avg_price }원</div>
				                        </div>
				                        <div class="expArea">
				                        	<div class="expTitle">입찰 마감일</div>
					                        <div class="productExp">
					                        	${item.goods_exp }
					                        </div>
				                        </div>
			                        </div>
				                    <div class="productAddr">
				                    	${item.goods_address }
				                    </div>
				                    <div class="leftTime hide">
				                    </div>
			                    </div>      
							</div>
		                 </c:forEach> 
					</div>
					<div class="swiper-button-next"></div>
					<div class="swiper-button-prev"></div>
				</div>
			</div>
			
			<!-- 신규 등록 물품 -->
			<div id="newRegProductsArea">
				<div id="newRegProductsTitle">
					신규 등록 물품
				</div>
				<!-- swiper -->
				<div class="swiper mySwiper">
					<div class="swiper-wrapper hoverEl">
						<c:forEach var="item" items="${new_list }">
							<div class="swiper-slide smallEl">
			                     <div class="popProduct">
			                        <div class="productImg">
			                           <img src="/resources/images/default_profile.png">
			                        </div>
			                        <div class="productTitle">${item.goods_title }</div>
			                        <div class="avgPrice_expDateArea">
			                        	<div class="avgPriceArea">
			                        		<div class="avgPriceTitle">평균 입찰가</div>
				                        	<div class="productAvgPrice">${item.goods_avg_price }원</div>
				                        </div>
				                        <div class="expArea">
				                        	<div class="expTitle">입찰 마감일</div>
					                        <div class="productExp">
					                        	${item.goods_exp }
					                        </div>
				                        </div>
			                        </div>
			                        <div class="leftTime hide">
				                    </div>
			                     </div>      
							</div>
		                 </c:forEach> 
					</div>
					<div class="swiper-button-next"></div>
					<div class="swiper-button-prev"></div>
				</div>
			</div>

			<!-- 내 주변 상품 -->
			<div id="nearProductsArea">
				<div id="nearProdHeader">
					<div id="nearProductsTitle">
						내 주변 상품
					</div>
					<div id="location">
						<div id="locImg"><img src="/resources/images/loc.png"></div>
						<div id="locNameArea">
							<c:forEach var="dong" items="${dong_list }">
								<div class="locName">
									${dong }
									 
								</div>
							</c:forEach>	
						</div>
					</div>
				</div>
				<!-- swiper -->
				<div class="swiper mySwiper">
					<div class="swiper-wrapper hoverEl">
						<c:forEach var="item" items="${near_list }">
							<div class="swiper-slide">
			                     <div class="popProduct">
			                        <div class="productImg">
			                           <img src="/resources/images/default_profile.png">
			                        </div>
			                        <div class="productTitle">${item.goods_title }</div>
			                        	<div class="avgPrice_expDateArea">
				                        	<div class="avgPriceArea">
				                        		<div class="avgPriceTitle">평균 입찰가</div>
					                        	<div class="productAvgPrice">${item.goods_avg_price }원</div>
					                        </div>
					                        <div class="expArea">
					                        	<div class="expTitle">입찰 마감일</div>
						                        <div class="productExp">
						                        	${item.goods_exp }
						                        </div>
					                        </div>
				                        </div>
				                        <div class="leftTime hide">
				                   	 	</div>
			                     </div>      
							</div>
		                 </c:forEach> 
					</div>
					<div class="swiper-button-next"></div>
					<div class="swiper-button-prev"></div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>