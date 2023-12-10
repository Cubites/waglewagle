<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="forms" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="Spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <script src="http://code.jquery.com/jquery-latest.min.js"></script>
  <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
  <script src="/resources/js/goods/regist.js"></script>
 <link rel="stylesheet" href="/resources/css/common/common.css">

  <title>Document</title>
  <style>
  	.errorMessage{
  		color: red;
  		font-size: small;
  		font-weight: bold;
  	}
  	#addrSearchButton{
  		border-radius: 5px;
  		color:white;
  		border: none;
  	}
    #inputBoxContainer {
      display: flex;
      flex-direction: column;
      justify-content: space-around;
      padding-top: 5%;
      padding-bottom: 5%;
      padding-left: 7%;
      
    }

    .inputlable {
      display: block;
    }

    .inputBox {
      display: flex;
      flex-direction: column;
      margin-bottom: 2%;
      margin-left: 3%;

    }

   	.goodsInput {
      width: 25%;
      height: 30px;

      background-color: #EBEBEB;
      border-radius: 5px;
      border: none;
      text-align: center;
      font-weight: bold;

    }

    .pointer {
      color: red;
    }

    .inputlabel {
      font-weight: bold;
    }

    #textBox {
      width: 450px;
      height: 130px;
      background-color: #EBEBEB;
      border-radius: 10px;

    }

    #submitbutton {
      display: flex;
      justify-content: center;
    }

    input[type=submit] {
      display: inline;
      color: white;
      font-size: large;
    }

    #submits {
      border-radius: 5px;
      width: 200px;

      background-color: #CF5C5C;
      height: 50px;

      display: flex;

      justify-content: center;

      align-items: center;

      font-size: large;
      font-weight: bold;
      color: white;

      margin-bottom: 5%;
    }

    #checkimg {
      display: inline-block;
      width: 25px;
      height: 25px;
      margin-right: 10px;
    }

    select {
      width: 250px;
      height: 30px;
    }

    #imageBox {
      width: 58%;
      background-color: #EBEBEB;
      border-radius: 5px;
      padding-top: 1%;

    }

    .selectedImageBox {

      min-width: 80px;
      min-height: 80px;
      max-width: 80px;
      max-height: 80px;
      margin: 0.5%;

    }

    .imageSets {
      position: relative;
      display: inline-block;
      margin: 0.5%;

    }

    .image_th {

      border: 4px solid #71DF8A;
    }

    .image_text {

      position: absolute;
      top: -15%;
      left: 10%;

      color: black;
      font-size: 1%;

    }

    .xbutton {
      position: absolute;
      top: -15%;
      left: 85%;
      width: 15%;

    }

    #fileLengthText {
      display: inline-block;
      margin-left: 60px;

    }

    #clipImage {
      width: 1.3%;
    }

    input[type="button"] {
      width: 10%;
      margin-left: 1%;
    }

    #addrDong {
      text-align: center;
      font-weight: bold;
      width: 20%;
    }

    #goodsDate {
      width: 20%;
    }
  </style>

<script>

function getLowerCategories(){
	var val= $("#goodsCategory1").val();
	
	$.ajax({
		
		type:'post',
		url : '/goods/categories',
		headers: {
			"accept":"application/json",
			"Content-Type":"application/json"
		},
		dataType:'json',
		data:{"upper":val},
		success: function(result){
			console.log(result);
			printLower(result);
		}
		
	})
}
	
	function printLower(result){
		
		let cate2 = $("#goodsCategory2");
		cate2.empty();
		var str ="<option value=''>2차 카테고리</option>";
		cate2.append(str);
		var str = "<option value='-1' style='display:none;'>hidden</option>";
		cate2.append(str);
		if(result === null || result===""){
			return;
		}
		
		for(const cate of result){
			
			 str ="";
			 str += "<option value='"+cate.category_id+"'>";
			 str  += cate.category_name+"</option>";
			 
			 cate2.append(str);		
		}
		
		//cate2.html(html);
	}

	function submits2(){
		
		var price = $("#price").val();
		
		//숫자 보기좋게 변환해둔 것 다시 고쳐야함!
		price=price.replaceAll("￦","");
		price=price.replaceAll(",","");
		price=price.trim();
		$("#price").val(price);
		
		//input 태그 file에 파일을 추가하면 내부적으로 files라는 속성에 저장함
		//근데 이 속성 읽기만 가능하고 변경은 불가능함 뭔가 변경하고 싶으면 
		//dataTransfer객체를 사용해야함 MDN 무슨 표준 
		const dataTransfer = new DataTransfer();
		
		for (var i = 0; i < window.filesArr.length; i++) {
	        if (!window.filesArr[i].is_delete) {
	           //dataTransfer객체에 items에 add
	          dataTransfer.items.add(filesArr[i]);
	        }
	      }
		//himage id를 가진 돔객체에 files를 dataTransfer.files로 교체
		$("#himage")[0].files = dataTransfer.files;
		
		//대표이미지 이름 저장하기 위해 -> 파일 id는 file0으로 파일 fileNum로 저장되어있음 
		 var image_id = $(".image_th").attr("id"); //id값 가져오기
	      var fileNumber;
	      var image_th;
	      if(image_id !== undefined){ //id값이 존재한다면,
	      fileNumber = image_id.substring(image_id.indexOf("e") + 1); //fileNum가져오기
	      image_th = filesArr[fileNumber].name;	//filesArr에서 fileNumber에 해당하는 file객체에 이름 가져오기
	      }else{
	    	  fileNumber=null;
	    	  image_th ="";
	      }
	      
	      $("#goods_th_img").val(image_th);
	      //1차 카테고리가 24,25일 경우 2차 카테고리 null이어도 됨
	      var ca1 = $("#goodsCategory1").val();
	     
	      if(ca1 === '24' || ca1 === '25'){
	    	  
	    	  $("#goodsCategory2").val("-1");
	    	  
	      }
	      
	    //기존 Date에 현재 시간 시간 추가 
	    var timeStampString = getTimeStamp();
	    $("#goods_exph").val(timeStampString);
	  	
	   
		var form = $("#registForm");
		form.submit();
		
	}

   

    function getTimeStamp(){
    	let goodsDateString = $("#goodsDate");
    	alert(goodsDateString.val());
    	let now = new Date();
    	
    	//시간 추출 
    	var hours = ('0' + now.getHours()).slice(-2);
    	var min = ('0' + now.getMinutes()).slice(-2);
    	var seconds = ('0' + now.getSeconds()).slice(-2);
    	var timeString = hours + ':' + min + ':' + seconds;
    	//
    	var TimeStampString = goodsDateString.val() + " " + timeString;
    	console.log(TimeStampString);
    	
    	return TimeStampString;
    }

  </script>

</head>
<body>
  <%@ include file="/WEB-INF/views/common/header.jsp" %>
  <div id="center">
    <div id="container">

      <div id="inputBoxContainer">
      <forms:form id="registForm" modelAttribute="goodsFormVO" method="post" enctype="multipart/form-data" action="/goods/regist">
        <div class="inputBox">
          <span class="inputlabel"><span class="pointer">*</span>상품 이미지</span>

          <input id="inputFile" type="file" multiple accept="image/*">
          <!-- 여기 추가 -->
          <input type="file" id="himage" name="images" style="display: none;">
          <input type="hidden" id="goods_th_img" name="goods_th_img"/>
          <span id="fileLengthText">(0/5)</span>
          <div id="imageBox">
            <img id="selectImageBox" class="selectedImageBox" src="/resources/images/goods/imagebox.PNG" alt="">
          </div>
          	      <!-- 필드 에러 외 글로벌 오류 출력 -->
	        <c:if test="${error != null && error.hasGlobalErrors()}">
			  <c:forEach var="er" items="${error.getGlobalErrors()}">
				<span class="errorMessage">${er.getDefaultMessage()}</span>
			  </c:forEach>
	         </c:if>
        </div>
       
        <div class="inputBox">
          <span class="inputlabel"><span class="pointer">*</span>제목</span>
          <!--  <input id="goodsTitle" type="text" size="100">-->
       
           	<forms:input path="goods_title" size="100" cssClass="goodsInput"/>
            <forms:errors path="goods_title" cssClass="errorMessage"/>
        </div>
       
        <div class="inputBox">
          <span class="inputlabel"><span class="pointer">*</span>설명</span>
          <textarea name="goods_comment" id="textBox" placeholder="   
      
      -상품명 ( 브랜드 )
      -구매시기
      -사용기간
      -물건 상태
      *실제 촬용한 사진과 함께 상세 정보를 입력해주세요"></textarea>
        </div>
        <div class="inputBox">
          <span class="inputlabel"><span class="pointer">*</span>카테고리</span>
          <div id="selectCategory">
             <forms:select path="goods_category1" id="goodsCategory1" onchange="getLowerCategories();">
        	<forms:option value="">1차 카테고리</forms:option>
        	<forms:option value="1">패션/뷰티</forms:option>
        	<forms:option value="11">전자기기</forms:option>
        	<forms:option value="20">반려동물</forms:option>
        	<forms:option value="24">도서/음악</forms:option>
        	<forms:option value="25">기타</forms:option>
        </forms:select>
         <forms:select path="goods_category2" id="goodsCategory2">
        	<forms:option value="">2차 카테고리</forms:option>
        	<!-- 24,25번 카테고리 선택시 넣을 값임 -->
        	<!--<forms:option value="-1" cssStyle="display:none;">hidden</forms:option>-->
        	<div id="category2Ajax">
        	
        	</div>
        </forms:select>
        
          </div>
          <forms:errors path="goods_category1" cssClass="errorMessage"/>
        </div>
        
       
        
       
        
        <div class="inputBox">
          <span class="inputlabel"><span class="pointer">*</span>시작가격</span>
         <!-- <input id="price" type="text" > -->
          <forms:input path="goods_start_price" id="price" cssClass="goodsInput"/>
          <forms:errors path="goods_start_price" cssClass="errorMessage"/>
        </div>
        
        
        <div class="inputBox">
          <span class="inputlabel"><span class="pointer">*</span>
            거래장소
            <img id="clipImage" src="/resources/images/goods/clip.PNG">
          </span>
          <div id="addrBox">
          	<forms:input path="goods_dong" id="addrDong" readonly="true" cssClass="goodsInput"/>
            <forms:input path="goods_addr" id="fullJibun" cssStyle="display:none;"/>
            <input id="addrSearchButton" type="button" value="주소조회" style="background-color: #777777;">
          </div>
          <forms:errors path="goods_addr" cssClass="errorMessage" />
        </div>
      
          
        <div class="inputBox">
          <span class="inputlabel"><span class="pointer">*</span>거래마감일</span>
          <input id="goodsDate" type="date" name="goods_expDate" Class="goodsInput">
          <input type="hidden" name="goods_exp" id="goods_exph">
       	   <forms:errors path="goods_exp" cssClass="errorMessage"/>
        </div>
      
     
        </forms:form>
      </div>
      <div id="submitbutton">
        <div id="submits" onclick="submits2()">
          <img src="/resources/images/goods/checkbutton.PNG" id="checkimg">
          <h3>경매시작</h3>
        </div>

      </div>
    </div>
  </div>
  <%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>