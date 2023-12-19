<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Sample</title>
	<link rel="stylesheet" href="/resources/css/common/common.css">
	<link rel="stylesheet" href="/resources/css/goods/goodsShow.css">
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>


	<script>
	//유저의 찜상태 체크
	var userlike = "${userFavor}" === 'true';
	var expDate = "${goods.goods_exp}";
	$(function(){
		
		
		if ("${rejectmsg}" !== "") {
			
			showModal("${rejectmsg}","result");
		}
		
		//신고하기 화면으로 넘어감
		$("#sirenimg").click(()=>{
			location.href = "/report/${goods.goods_id}/${users_info.users_id}";
		});
		//숫자만 입력가능 및 콤마로 자동 변환 이벤트
	      let inputDom = document.querySelector("#submitPoint > input");
	      inputDom.addEventListener("input", function (e) {
	        let val = e.target.value.replace(/[^0-9]/g, '');
	        e.target.value = val.replace(/\d(?=(?:\d{3})+$)/g, '$&,');
	      });
	      ////////////////////////////////////////
	      
	      //찜버튼 클릭 이벤트
		 $("#likebox").click(function () {
		        if (window.userlike === false) {
		          $("#likeImage").attr("src", "/resources/images/goods/favorite.jpg");
		          changeFavors(1);
		        } else {
		          $("#likeImage").attr("src", "/resources/images/goods/favorite_border.jpg");
		          changeFavors(-1);	
		        }
		        userlike = !userlike;
		        storeUserFavor(userlike);
		      })
		////////////////////////////////
	      
		//잘못 입력에 대한 모달꺼지는 것 정의
	      $("#basicModal").click(() => {
	        $("#basicModal").fadeOut();
	      })
		////////////////////////////////
		
		//시간 계산하는 기능 1초마다 실행되도록 함 
		setInterval(()=>{
			displayRemainingTime();
		},1000);
		////////////////////////////////
		
		//DB에서 읽어온 주소 중 동만 읽어옴 
		displayDong();
		///////////////////////////
		
		
	})
	function displayDong(){
		let jibun = "${goods.goods_address}".split(" ");
		
		let dong = jibun[jibun.length-1];
			
		$("#dong").text(dong);
		
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
	
	//찜 버튼 관련 
	//찜버튼 눌렀을 때 좋아유 수 변화를 위해 
	function changeFavors(num){
		let favorsDom = $("#jjimcnt"); 
		let val = Number(favorsDom.text())+num;
		favorsDom.text(val);
	}
	
	//찜버튼 눌렀을 때 DB에 반영 
	function storeUserFavor(status){
		//users_info.users_id;
		$.ajax({
			url:"/goods/favor",
			type: "post",
			data: {	
					"goodsId" : "${goods.goods_id}",
					"status" : userlike
				},
			success : function(){
				
			}
			
		});
	}
	/////////////////////////////////////
	
	 //가격 검증 관련 (프론트 검증 물론 입력못하게 막아두긴함)
    function askingPriceCheck() {
    	 let askPriceDom = $("#submitPoint > input");
         let askPrice = askPriceDom.val();
         let askPriceNoComma = askPrice.replace(/,/g, "");

         //숫자 검증
         if (askPriceValidation(askPriceNoComma)) {
           //검증 완료 후 숫자 변환 

           showModal("제출하신 가격은 <span id='priceComma'>" + askPrice + "</span>원 입니다.<br>" +
             "한번 제출한 가격은 취소할 수 없습니다. <br>" +
             "계속 진행하시겠습니까?", "submit");

         }
    }
    //제출된 가격을 검사하는 함수
    function askPriceValidation(price) {
      var num = Number(price);
      //숫자가 아니거나, 정수가 아닐시
      if (Number.isNaN(num) || !Number.isInteger(num)) {
        showModal("제출된 가격을 확인해주세요", "reject");
        return false;
      } else if (num < 0) {
        showModal("0보다 작은 숫자는 입력하실 수 없습니다.", "reject");
        return false;
      }

      return true;

    }
    //텍스트에 대해 모달창 보여주는 함수
    function showModal(text, command) {

      if (command !== "submit") {
        let modalContent = $("#basicModal-Content");
        modalContent.text(text);
        $("#basicModal").fadeIn();
        return false;
      }
      let submitModal = $("#submitModalText");
      submitModal.html(text);
      $("#submitModal").fadeIn();
      return true;

    }
	
    //모달창에서ok를 누르면!  
    function isOk(status) {
  
    
      if (status === true) {
        //ajax보내는 함수 정의
         askPrice();
      }

      $("#submitModal").fadeOut();

    }
    //모달 관련 함수 끝
    
    
    function askPrice(){
    	 let askPriceDom = $("#submitPoint > input");
    	 let askPrice = askPriceDom.val();
    	 let askPriceNoComma = askPrice.replace(/,/g, "");
    	
    	$.ajax({
    		url:"/bids/askPrice",
    		type:"post",
    		data: {"users_id":"${users_info.users_id}","goods_id":"${goods.goods_id}"
    			,"bids_price":askPriceNoComma,"point_usable":"${usersPoint.point_usable}"},
    		contentType:"application/x-www-form-urlencoded;charset=utf-8",
    		success:function(data){
    			
    			console.log(data);
    			showModal(data,"");
    			setTimeout(() => {
    				location.reload(true);
				}, 2000);
    			
    			
    		},
    		error:function(xhr,error,msg){
    			showModal(xhr.responseText,"reject");
    			setTimeout(() => {
    				location.reload(true);
				}, 2000);
    			
    			
    		}
    		
    	})
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
                
           			<!-- 업로드된 이미지 받아오기 --> 
               	  <img class="imgs" src="/upload${goods.goods_th_img}"/>
               	  <c:forEach items="${images}" var="img" begin="0">
               	  	<c:if test="${img.images_loc != goods.goods_th_img}">
               	  	<img class="imgs" src="/upload${img.images_loc}"/>
               	  	</c:if>
               	  </c:forEach>
               	  	
                </div>
              </div>
              
            </div>
            <script src="/resources/js/goods/slide.js"></script>
            <div id="likebox">
			  <!-- 유저의 찜상태 체크 -->
			  <c:choose>
			  	<c:when test="${userFavor}">
			  	<img id="likeImage" src="/resources/images/goods/favorite.jpg">
			  	</c:when>
			  	<c:otherwise>
			  	<img id="likeImage" src="/resources/images/goods/favorite_border.jpg">
			  	</c:otherwise>
			  </c:choose>
           
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
                <div id="sellerfont">판매자 : <span id="sellerNic">${users_info.users_nick}</span></div>
                <div id="disBox">와글이와의 거리 "<span id="dis">${users_info.users_rel}</span>"</div>
              </div>
              <div id="addrBox">
                <div id="addrfont">거래장소 : <span id="dong"></span></div>
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
              <span id="priceVal"><fmt:formatNumber value="${goods.goods_start_price}" pattern="#,###"/>원</span>
              <span id="timeVal" ></span>
            </div>
            <div class="topbox2c" id="topbox2footer">
              <div id="person">
                <span class="footerFont">현재 경매 참여 인원</span>
                <img id="personimg" src="/resources/images/goods/person.png" alt="">
                <span id="personcnt">${bidsCnt}</span>
              </div>
              <div id="jjimp">
                <span class="footerFont">이 상품을 찜한 사람</span>
                <img src="/resources/images/goods/favorite.jpg">
                <span id="jjimcnt">${favorsCnt}</span>
              </div>
            </div>
          </div>
        </div>
        <div id="mid">
          <div id="midTextBox">
            <div class="midText">나의 사용 가능 포인트</div>
            <div class="midText">희망 가격을 제출하면 경매에 자동으로 참여됩니다.</div>
          </div>
         <div id="midPointWrap">
        
          <div id="myPoints">
          	<img id="coin" src="/resources/images/goods/coin.png">
          	<div id="myPoint"><fmt:formatNumber value="${usersPoint.point_usable}" pattern="#,###"/>원</div>
          	<div id="fillText"><div>충전하기</div></div>
          </div>
        
          <div id="submitPoint">
          		<input type="text" placeholder="희망 가격을 제출해 주세요" oninput="this.value = this.value.replace(/[^0-9]/g, '');">
          		<div id="submitButton" onclick="askingPriceCheck()">제출하기</div>
          </div>
          
          
          </div>
          </div>
           
			
			 <div id="bottom">
          <div>
            <div id="bottomFont">
              나의 희망 가격
            </div>
            <div id="bidsPriceCheck">
              <span>${maxBids}</span> 원
            </div>
          </div>
        </div>
        
          </div>
        </div>
      </div>
			
		</div>
	</div>
	
	<!-- 모달창 -->
	 <div id="basicModal" class="modal">
    <div id="basicModal-Content" class="modal-Content">

    </div>
  </div>

  <div id="submitModal" class="modal">
    <div id="submitModal-Content" class="modal-Content">
      <div id="submitModalText"></div>
      <div>
        <div id="modalOk" onclick="isOk(true)"><span class="submitFont">확인</span></div>
        <div id="modalCancle" onclick="isOk(false)"><span class="submitFont">취소</span></div>
      </div>
    </div>
  </div>
  <!-- 모달창 끝 -->
	
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>

</body>
</html>