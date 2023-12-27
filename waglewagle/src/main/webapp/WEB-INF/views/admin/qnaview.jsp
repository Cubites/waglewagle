<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head> 
    <meta charset="utf-8">
    <title></title>
    <META name="viewport" content="width=device-width, height=device-height, initial-scale=1.0, user-scalable=no"> 
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.css" />
    <link rel="stylesheet" href="/resources/css/admin/qnaview.css"/>

	<script>
		//문의글 삭제
		function qnaDelete(qnas_id){
		    if(confirm("선택한 문의사항을 삭제 하시겠습니까?")==true)  {
		    	location.href="/admin/qnaDelete/"+qnas_id;  	
		    }
		} 

		//답글 작성 
		//페이지 이동만 하는것..
	    function writeReply(qnas_id){
	    	location.href="/admin/writeReply/"+qnas_id;
		}
		
	
		 //답글 삭제
		function delReply(qnas_id){
		    if(confirm("선택한 답글을 삭제 하시겠습니까?")==true)  {
		    	location.href="/admin/delReply?qnas_id="+qnas_id;  	
		    }
		} 

		
		//답글 수정하기
	  	function modifyReply(){
			console.log(document.getElementById("qnas_id").innerText);
			const qnas_id = document.getElementById("qnas_id").innerText;
			location.href="/admin/modifyReply/${qnas_id}";
		}  
		
		//답글 수정 (ajax)
		/*function modifyReply(qnas_id){
				$.ajax({
					url:"/admin/modifyReply",
					type:"post",
					contnetType:"application/json",
					data:JSON.stringify({
						data:qnas_id
					}),
					success:function(data){
					}
				}) 
			} */
		
		
		 
		//사이드바 선택에 맞는 함수들..
	    function notice(){
			 location.href = "/admin/noticelist";
		}
		function qna(){
			 location.href = "/admin/qnalist";
		}
		function stats(){
			 location.href = "/admin/stats";
		}
		function user(){
			 location.href = "/admin/userManageList";
		}
		function goods(){
			 location.href = "/admin/goodsManageList";
		}
		function admin(){
			 location.href = "/admin/adminmanage";
		}
		function password(){
			 location.href = "/admin/changePwd";
		}
	</script>
</head> 
<body>
	<div id="header">
        <div id="headerContainer">
            <br/>
            <img src="/resources/images/log.jpg" title="와글와글 로고"/>
        </div>
    </div>

    <div id="center">
        <div id="container">
            <div id="side-box">
				<div id="notice" onclick="notice()">공지</div>
                <div id="qna" onclick="qna()">문의</div>
                <div id="showdata" onclick="stats()">통계</div>
                <div id="adminuser" onclick="user()">회원관리</div>
                <div id="admingoods" onclick="goods()">상품관리</div>
                <div id="adminmaster" onclick="admin()">관리자 계정</div>
                <div id="chagepwd" onclick="password()">비밀번호 변경</div>
            </div>
        
            <div id="main-box">
				<div class="noticeView">        		
					<div class="rightloc">
						<p style="font-size:15px" class="noticeinfo" id="qnas_id">글번호: ${a.qnas_id } &nbsp&nbsp 작성일: ${a.qnas_date }</p>
					</div>
					<!-- 작성자 -->
					<div class="qview">
						<div class="titletxt">작성 회원</div>&nbsp
						<div id="noticetitle">
							${a.users_id}
						</div>
					</div>
					<!-- 제목 -->
            	   	<div class="qview">
	            	   	<div class="titletxt">문의 제목 </div>&nbsp
	                   	<div id="noticetitle">
	                       	${a.qnas_title}
	                   	</div>
                   	</div >
                   	<!-- 내용 -->
                   	<div class="qview">
	                   	<div class="titletxt">문의 내용 </div>&nbsp
	                   	<div id="noticecontent">
	                       	${a.qnas_content}
	                   	</div>
                   	</div>

					<!-- 버튼들 -->
            		<div class="writebtn">
            			<!-- 공지 상세페이지 버튼들  -->
						<div id="notice_button">
							<input type="button" id="writebtn" value="목록" onclick="qna()">
							<input type="button" id="writebtn" value="문의글 삭제" onclick="qnaDelete(${a.qnas_id})">
						</div>
					</div>
            		
					<form method="post" action="/admin/writeReply/${qnas_id}">
						<div class="qview2">
							<div class="titletxt">문의 답글 </div>&nbsp
	            			<div id="replyText">
	            				<textarea id="noticetitle" name="qnas_reply" placeholder="문의답글을 작성하세요">${a.qnas_reply}</textarea>
	            			</div>
            			</div>
            			<!-- 답변 유무로 버튼 변경되어서 표시된다. -->
            			<c:if test="${empty a.qnas_reply }">
	            			<input type="submit" value="답글작성" id="writebtn">
	            	 	</c:if>
	            	 	
	            	 	<c:if test="${!empty a.qnas_reply}">
	            	 		<div id="replybtn">
	            	 			<input type="submit" value="답글수정" id="writebtn">
	            				<%-- <input type="button" id="writebtn" value="답글수정" onclick="writeReply(${a.qnas_id})"> --%>
	            				<input type="button" id="writebtn" value="답글삭제" onclick="delReply(${a.qnas_id})">
	            			</div>
	            	 	</c:if>	
            		</form>

				</div>
			</div>
		</div>
    </div>
</body> 
</html>