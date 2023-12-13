<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Sample</title>
	<link rel="stylesheet" href="/resources/css/common/common.css">
	<link rel="stylesheet" href="/resources/css/goods/goodsShow.css">
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>


	<script>
	var userlike = false;
	var expDate = "${goods.goods_exp}";
	$(function(){
		
		setInterval(()=>{
			displayRemainingTime()*1000;
		},1000);
		
		displayPrice();
		displayDong();
		 $("#likebox").click(function () {
		        if (window.userlike === false) {
		          $("#likeImage").attr("src", "/resources/images/goods/favorite.jpg");
		        } else {
		          $("#likeImage").attr("src", "/resources/images/goods/favorite_border.jpg");
		        }
		        userlike = !userlike;
		      })
	})
	function displayDong(){
		let jibun = "${goods.goods_address}".split(" ");
	
		let dong = jibun[jibun.length-2];
		dong = dong.indexOf('리') !== -1 ? dong: jibun[jibun.length-3];
		
		$("#dong").text(dong);
		
	}
	function displayPrice(){
		let price = $("#priceVal").text()
		price = price.replace(/\B(?=(\d{3})+(?!\d))/g, ",");
		
		$("#priceVal").text(price);
		
	}
	
	function displayRemainingTime(){
		
		//1일 05시간 30분
		const now = new Date();
		const end = new Date(window.expDate);
		const timeRemain = end - now;
		
		let timeString = "";
		if(timeRemain <=0){
			$("#timeVal").text("0일 0시간 0분");
			$("#timeVal").css({"color":"#838383"});
			return;
		}
		const days = Math.floor(timeRemain/(1000*60*60*24));
		const hours = ("0"+Math.floor(timeRemain %(1000*60*60*24)/(1000*60*60))).slice(-2);
		const minutes = ("0"+Math.floor(timeRemain %(1000*60*60)/(1000*60))).slice(-2);
		const seconds = Math.floor(timeRemain %(1000*60)/1000);
		
		
		
		$("#timeVal").text(days+"일 "+hours+"시간 "+minutes+"분" );
		
		
	}
	
	</script>
</head>

<body>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
	<div id="center">
		<div id="container">
			 <div id="wrap">

        <div id="top">
          <div id="topbox1" class="topbox">
            <div id="imagebox">
              <img id="left" class="direc" src="/resources/images/goods/left.png" alt="">
              <img id="right" class="direc" src="/resources/images/goods/right.png" alt="">
               <div id="imagewrap">
                <div id="slideList">
                <img class="imgs" src="/resources/images/goods/image 17.jpg"/>
                <img class="imgs" src="/resources/images/goods/image 17.jpg"/>
                <img class="imgs" src="/resources/images/goods/image 17.jpg"/>
                <img class="imgs" src="/resources/images/goods/image 17.jpg"/>
                <!--  
               	  <img class="imgs" src="/upload${goods.goods_th_img}"/>
               	  <c:forEach items="${images}" var="img" begin="0">
               	  	<c:if test="${img.images_loc != goods.goods_th_img}">
               	  	<img class="imgs" src="/upload${img.images_loc}"/>
               	  	</c:if>
               	  </c:forEach>
               	  -->	
                </div>
              </div>
              
            </div>
            <div id="likebox">

              <img id="likeImage" src="/resources/images/goods/favorite_border.jpg">
              <div id="jjim">찜하기</div>
            </div>
          </div>
          <div id="topbox2" class="topbox">
            <div class="topbox2c" id="title">
              <div id="titleFont">
              ${goods.goods_title}
             </div>
              <div id="reportFont">
                <span id="sirenfont" class="siren">신고하기</span>
                <img id="sirenimg" class="siren" src="/resources/images/goods/Siren Rounded.jpg">
              </div>
            </div>
            <div class="topbox2c" id="seller">
              <div id="sellerBox">
                <div id="sellerfont">판매자 : <span id="sellerNic">망고미</span></div>
                <div id="disBox">와글이와의 거리 "<span id="dis">42</span>"</div>
              </div>
              <div id="addrBox">
                <div id="addrfont">거래장소 : <span id="dong">마포구</span></div>
                <span>(정확한 장소는 입찰 후 공개됩니다.)</span>
              </div>

            </div>
            <div class="topbox2c" id="contentbox">상품설명:
              <div id="content"> ${goods.goods_comment}</div>
            </div>
            <div class="topbox2c" id="priceAndTimeFont">
              <span id="priceFont">경매 시작 가격</span>
              <span id="timeoutFont">경매 종료까지 시간</span>
            </div>
            <div class="topbox2c" id="priceAndTime">
              <span id="priceVal">${goods.goods_start_price}원</span>
              <span id="timeVal" >1일 05시간 30분</span>
            </div>
            <div class="topbox2c" id="topbox2footer">
              <div id="person">
                <span class="footerFont">현재 경재 참여 인원</span>
                <img id="personimg" src="/resources/images/goods/person.png" alt="">
                <span id="personcnt">20</span>
              </div>
              <div id="jjimp">
                <span class="footerFont">이 상품을 찜한 사람</span>
                <img src="/resources/images/goods/favorite.jpg">
                <span id="jjimcnt">10</span>
              </div>
            </div>
          </div>
        </div>
        <div id="mid">

        </div>
        <div id="bottom">

        </div>
      </div>
			
		</div>
	</div>
	<script src="/resources/js/goods/slide.js"></script>
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>