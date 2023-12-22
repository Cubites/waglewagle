<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 검색</title>
<link rel="stylesheet" href="/resources/css/common/common.css">
<link rel="stylesheet" href="/resources/css/goods/search.css">
</head>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp"%>
	<div id="center">
		<div id="container">
			<div id="searchBar">
					<!-- <img id="check_img" alt="" src="/resources/images/category_check.png">
					카테고리 1
					<img id="tri_img" alt="" src="/resources/images/category_tri.png">
					<img id="check_img" alt="" src="/resources/images/category_check.png">
					카테고리 2
					<img id="tri_img" alt="" src="/resources/images/category_tri.png"> -->
				<!-- 토글 -->
				<select id="category1">
					<option value="-">1차 카테고리</option>
					<option value="1">패션 / 뷰티</option>
					<option value="11">전자기기</option>
					<option value="20">반려동물</option>
					<option value="26">생활 / 가전</option>
					<option value="24">도서 / 음악</option>
					<option value="25">기타</option>
				</select>
				
				<select id="category2">
					<option value="">2차 카테고리</option>
					
					
					
					<%-- <c:choose>
						<c:when test="${category1_value == 1 }">
							<option value="2">여성의류</option>
							<option value="3">남성의류</option>
							<option value="4">코트</option>
							<option value="5">어린이</option>
							<option value="6">신발</option>
							<option value="7">가방 / 지갑</option>
							<option value="8">시계</option>
							<option value="9">주얼리</option>
							<option value="10">미용</option>
						</c:when>
					</c:choose>
					
					<c:choose>
						<c:when test="${category1_value == 11 }">
							<option value="12">노트북</option>
							<option value="13">데스크탑</option>
							<option value="14">웨어러블</option>
							<option value="15">휴대폰</option>
							<option value="16">태블릿</option>
							<option value="17">컴퓨터 주변기기</option>
							<option value="18">카메라</option>
							<option value="19">콘솔 게임기</option>
						</c:when>
					</c:choose>
					
					<c:choose>
						<c:when test="${category1_value == 20 }">
							<option value="21">소모품</option>
							<option value="22">옷</option>
							<option value="23">장난감</option>
						</c:when>
					</c:choose> --%>
					
				</select>
				<input type="hidden" id="category1_value" value="${category1_value}" />
				
				<!--  -->
				
				<div id="searchBox">
					<img id="searchIcon" alt="search" src="/resources/images/search.png">	
					<input id="searchWordByBar" type="text" value="${searchWord }" placeholder="상품명 혹은 거래 제목을 입력해주세요.">
				</div>
			</div>
			
			<div id="result_div">
				<c:if test="${not empty searchCategory }">
					<c:if test="${searchCategory eq 2 }">
						<div class="result">'여성의류'</div>					
					</c:if>
					<c:if test="${searchCategory eq 3 }">
						<div class="result">'남성의류'</div>					
					</c:if>
					<c:if test="${searchCategory eq 4 }">
						<div class="result">'코트'</div>					
					</c:if>
					<c:if test="${searchCategory eq 5 }">
						<div class="result">'어린이'</div>					
					</c:if>
					<c:if test="${searchCategory eq 6 }">
						<div class="result">'신발'</div>					
					</c:if>
					<c:if test="${searchCategory eq 7 }">
						<div class="result">'가방/지갑'</div>					
					</c:if>
					<c:if test="${searchCategory eq 8 }">
						<div class="result">'시계'</div>					
					</c:if>
					<c:if test="${searchCategory eq 9 }">
						<div class="result">'주얼리'</div>					
					</c:if>
					<c:if test="${searchCategory eq 10 }">
						<div class="result">'미용'</div>					
					</c:if>
					
					<c:if test="${searchCategory eq 12 }">
						<div class="result">'노트북'</div>					
					</c:if>
					<c:if test="${searchCategory eq 13 }">
						<div class="result">'데스크탑'</div>					
					</c:if>
					<c:if test="${searchCategory eq 14 }">
						<div class="result">'웨어러블'</div>					
					</c:if>
					<c:if test="${searchCategory eq 15 }">
						<div class="result">'휴대폰'</div>					
					</c:if>
					<c:if test="${searchCategory eq 16 }">
						<div class="result">'태블릿'</div>					
					</c:if>
					<c:if test="${searchCategory eq 17 }">
						<div class="result">'컴퓨터 주변기기'</div>					
					</c:if>
					<c:if test="${searchCategory eq 18 }">
						<div class="result">'카메라'</div>					
					</c:if>
					<c:if test="${searchCategory eq 19 }">
						<div class="result">'콘솔 게임기'</div>					
					</c:if>
					
					<c:if test="${searchCategory eq 21 }">
						<div class="result">'소모품'</div>					
					</c:if>
					<c:if test="${searchCategory eq 22 }">
						<div class="result">'옷'</div>					
					</c:if>
					<c:if test="${searchCategory eq 23 }">
						<div class="result">'장난감'</div>					
					</c:if>
				</c:if>
				<c:if test="${not empty  searchWord}">
					<div class="result">'${searchWord }'</div>
				</c:if>
				<div id="result_comment">에 대한 검색 결과 입니다.</div>
			</div>
			
			<div id="sort_div">
				<div id="recent">최신등록순</div>|
				<div id="lower">낮은가격순</div>|
				<div id="higher">높은가격순</div>
			</div>
			
			<div id="list_div">
			
				<c:forEach var="item" items="${goodsList }">
					<div class="item">
	                     <div class="popProduct">
	                        <div class="productImg">
	                           <img src="/upload/${item.goods_th_img }">
	                        </div>
	                        <div class="productTitle">${item.goods_title }</div>
	                       	<div class="avgPrice_expDateArea">
	                        	<div class="avg_expTitle">
	                        		<div class="avgPriceTitle">평균 입찰가</div>
		                        	<div class="expTitle">입찰 마감일</div>
		                        </div>
		                        <div class="avg_expValue">
		                        	<div class="productAvgPrice">${item.goods_avg_price }원</div>
			                        <div class="productExpArea">
			                        	<div class="productExp"></div> 
				                        <div class="originExp hide">${item.goods_exp }</div>
			                        </div>
		                        </div>
	                        </div>
		                    <div class="leftTime toDetailPage">
	                        	<div class="leftTimeVal"></div>
	                        	<input type="hidden" value="${item.goods_id }" class="goodsIdVal">
		                   	</div>
	                     </div>      
					</div>
	             </c:forEach>
             </div>
			
		
		</div>
	</div>
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
	<script type="text/javascript">
	
		// category1_value를 전역 변수로 선언
	    var category1_value = $("#category1").val();
		
		// 검색어까지 입력했을 때만 검색되기
		$("#searchWordByBar").keyup(function (event) {  	        
	        if (event.keyCode === 13) {
	        	var searchText = event.target.value.toLowerCase();
	    	    var category2_value = $("#category2").val();
	         	console.log(searchText);  
	         	console.log(category1_value);
	         	console.log(category2_value);
	        	location.href = "/goods/search?category_id=" + category2_value + "&goods_title=" + searchText;
	        }
	    }); 
		
		// 정렬하기
		$("#recent").click(function (event) {
			console.log(event.target.value)
		})
		
		// 초기화 시에도 실행되도록 함수 호출
	    updateCategory2Options($("#category1").val());

	    $("#category1").change(function () {
	        category1_value = $(this).val();
	        $("#category1_value").val(category1_value);

	        // category1_value에 따라서 category2의 상태(disabled 여부)를 설정
	        updateCategory2Options(category1_value);
	    });

	    function updateCategory2Options(category1_value) {
	        var category2 = $("#category2");

	        // category2의 모든 옵션을 제거
	        category2.empty();

	        // category1_value에 따라서 동적으로 옵션 추가
	        switch (category1_value) {
	            case "1":
	                category2.append('<option value="2">여성의류</option>');
	                category2.append('<option value="3">남성의류</option>');
	                category2.append('<option value="4">코트</option>');
	                category2.append('<option value="5">어린이</option>');
	                category2.append('<option value="6">신발</option>');
	                category2.append('<option value="7">가방 / 지갑</option>');
	                category2.append('<option value="8">시계</option>');
	                category2.append('<option value="9">주얼리</option>');
	                category2.append('<option value="10">미용</option>');
	                break;
	            case "11":
	                category2.append('<option value="12">노트북</option>');
	                category2.append('<option value="13">데스크탑</option>');
	                category2.append('<option value="14">웨어러블</option>');
	                category2.append('<option value="15">휴대폰</option>');
	                category2.append('<option value="16">태블릿</option>');
	                category2.append('<option value="17">컴퓨터 주변기기</option>');
	                category2.append('<option value="18">카메라</option>');
	                category2.append('<option value="19">콘솔 게임기</option>');
	                break;
	            case "20":
	                category2.append('<option value="21">소모품</option>');
	                category2.append('<option value="22">옷</option>');
	                category2.append('<option value="23">장난감</option>');
	                break;
	            default:
	                category2.append('<option value="">2차 카테고리</option>');
	                break;
	        }

	        // category2의 상태(disabled 여부)를 설정
	        if (category1_value === "-" || category1_value === "24" || category1_value === "25" || category1_value === "26") {
	            category2.prop("disabled", true);
	        } else {
	            category2.prop("disabled", false);
	        }
	    }
	    
	    // 남은 시간 뜨고 각 상품 페이지로 이동하기
	    $(function(){
    	   $(".item").mouseover(function(){
    	      let now = new Date();
    	      let expDate = new Date($(this).find(".popProduct").find(".avgPrice_expDateArea").find(".avg_expValue").find(".productExp").text());
    	      let leftTime = expDate - now;
    	      
    	      const days = Math.floor(leftTime/(1000*60*60*24));
    	        const hours = ("0"+Math.floor(leftTime %(1000*60*60*24)/(1000*60*60))).slice(-2);

    	      
    	      $(".leftTimeVal").text(days+"일 "+hours+"시간");
    	      $(this).find('.popProduct').find(".leftTime").css("display", "flex");
    	      
    	   })
    	   $(".item").mouseout(function(){
    	      $(this).find('.popProduct').find(".leftTime").css("display", "none");
    	   })
    	   
    	})
    	   
    	$(function(){
    	   $(".toDetailPage").click(function(){
    	      var goods_id = $(this).find('input').val();
    	      location.href='/goods/'+goods_id;
    	      
    	   })
    	})
	
	</script>
</body>
</html>