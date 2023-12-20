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
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet" href="/resources/css/chat/chat.css">


<script>
	
	
	$(function() {
		
		
		
		$("#inputbutton").click(function() {
			chatting();
		})

		let pressEvent = $("#chatinput").on("keyup", function(key) {

			if (key.keyCode == 13) {
				chatting();
			}

		})

	})

	function chatting() {

		let chatClone = document.querySelector(".chatme").cloneNode(true);
		chatClone.classList.remove("hide");
		let inputDom = document.querySelector("#chatinput");

		if (inputDom.value !== '') {
			let chatText = chatClone.querySelector(".chatContent");
			chatText.innerText = inputDom.value;
			chatSend(inputDom.value);			
			inputDom.value = "";
			document.querySelector("#chatShowArea").appendChild(chatClone);

		}
	}

	function eventClear() {
		$("#chatinput").off("keyup");
	}
	
	
	
	
	
	  function chatSend(text){
		 
		   
		
		  $.ajax({
			  
			  url:"/chat/send",
			  type:"post",
			  contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			
			  data:{"chat_content":text,"chat_send":"${me.users_id}","chat_recieve":"${oppsite.users_id}","goods_id":"${me.goods_id}"},
			  success: function(data){
				  console.log(data);
			  }
			  
		  })
	  }
	
	  
	 
	
</script>


</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp"%>
	<div id="center">
		<div id="container">

			<div id="wrap">
				<div id="chatBoxes">

					<div id="leftBox1">
						<div id="imgBox">
							<div id="imgleft" class="imgButton">
								<img src="/resources/images/chat/realLeft.jpg" alt="">
							</div>
							<div id="imgContainer">
								<div id="imgContent">

									<div id="imagewrap">
										<div id="slideList">
											<!-- 업로드된 이미지 받아오기 -->
											<img class="imgs" src="/upload${me.goods_th_img}" />
											<c:forEach items="${images}" var="img" begin="0">
												<c:if test="${img.images_loc != me.goods_th_img}">
													<img class="imgs" src="/upload${img.images_loc}" />
												</c:if>
											</c:forEach>
										</div>
									</div>

								</div>
								<div id="textContent">
									<div id="goodsTitle">
										<span>판매상품</span> <span id="titleContent">${me.goods_title}</span>
									</div>
									
									<div id="seller">
										
										<c:choose>
											<c:when test="${me.memberType eq 'seller'}">
											<span>판매자</span><span>${me.users_nick}</span>
											</c:when>
											<c:otherwise>
											<span>판매자</span><span>${oppsite.users_nick}</span>
											</c:otherwise>
										</c:choose>
										
									</div>
									
									<div id="actionPrice">
										<span>낙찰 가격</span><span>${me.auctions_ing_price}</span>
									</div>
									
									<div id="addr">
										
										
										<c:choose>
											<c:when test="${me.memberType eq 'seller'}">
											<span>거래 장소</span><span>${me.users_address}</span>
											</c:when>
											<c:otherwise>
											<span>거래 장소</span><span>${oppsite.users_address}</span>
											</c:otherwise>
										</c:choose>
									
									</div>
								
								</div>
							</div>
							<div id="imgRight" class="imgButton">
								<img src="/resources/images/chat/realRight.jpg" alt="">
							</div>
						</div>
						<div id="mobileNotification">
							<div id="notice1">
								<img src="/resources/images/chat/light.jpg" alt="">
								<div>
									모바일로 '<span>거래 확정하기</span>'가 가능해요!
								</div>
							</div>
							<div id="notice2">제품을 받고 거래 확정하기를 반드시 눌러주세요.</div>
						</div>
						<div class="leftbutton" id="actionEnd">
							<img id="endImg" src="/resources/images/chat/ok.png" alt="" />
							<div id="endFont">거래 확정하기</div>
						</div>
						<div class="leftbutton" id="reportUser">
							<img id="reportImg"
								src="/resources/images/chat/Danger Square.png" alt="" />
							<div id="reportFont">거래 파기 신고하기</div>
						</div>
					</div>


					<div id="right">
						<div id="textArea">
							<div id="placeholders">
								<span id="place1">거래완료를 위해 쪽지를 주고 받으세요.</span> <span>(거래장소,시간정하기)</span>
							</div>

							<div id="chatShowArea">
							
							
							<c:forEach items="${chats}" var="chat">
								
								<c:choose>
								
								<c:when test="${chat.chat_send eq me.users_id}">
								
								<!-- 나의 영역 -->
								<div class="chatme">
									<div class="chatNick" id="myNick">${users_info.users_nick}</div>
									<div class="imgAndContent">

										<div>
											<img class="chatimg" src="/upload/${me.users_image}" alt="">
										</div>

										<div class="chatContentWrap">
											<div class="chatContent">
												${chat.chat_content}
											</div>
										</div>

									</div>
								</div>
								<!--         -->
								
								</c:when>
								
								<c:otherwise>
								
								<!-- 상대방 영역 -->
								<div class="chatop">
									<div class="chatNick" id="opNick">${oppsite.users_nick}</div>
									<div class="imgAndContent">
										<div>
											<img class="chatimg" src="/upload/${oppsite.users_image}" alt="">
										</div>

										<div class="chatContentWrap">
											<div class="chatContent">
												${chat.chat_content}
											</div>
										</div>

									</div>
								</div>
								<!--         -->
								
								</c:otherwise>
								
								</c:choose>
							
							</c:forEach>
							
							
							
							
								<!-- 상대방 영역 -->
								<div class="chatop hide">
									<div class="chatNick" id="opNick">${oppsite.users_nick}</div>
									<div class="imgAndContent">
										<div>
											<img class="chatimg" src="/upload/${oppsite.users_image}" alt="">
										</div>

										<div class="chatContentWrap">
											<div class="chatContent"></div>
										</div>

									</div>
								</div>
								<!--         -->

								<!-- 나의 영역 -->
								<div class="chatme hide">
									<div class="chatNick" id="myNick">${users_info.users_nick}</div>
									<div class="imgAndContent">

										<div>
											<img class="chatimg" src="/upload/${me.users_image}" alt="">
										</div>

										<div class="chatContentWrap">
											<div class="chatContent"></div>
										</div>

									</div>
								</div>
								<!--         -->

							</div>

						</div>


						<div id="chatInputArea">
							<div id="chatinputwrap">
								<input type="text" id="chatinput">
								<div id="inputbutton">보내기</div>
							</div>
						</div>

					</div>


				</div>
			</div>


		</div>
	</div>
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>

	<script src="/resources/js/goods/slideChat.js"></script>
	<script src="/resources/js/goods/slide.js"></script>
</body>
</html>