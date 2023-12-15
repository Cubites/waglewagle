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
    <link rel="stylesheet" href="/resources/css/admin/noticewrite.css"/>

	<script>
		//게시물 삭제
		const boardDelete = () => {
		    if(confirm("선택한 공지를 정말로 삭제 하시겠습니까?")==true) document.location.href='/board/delete?seqno=${view.seqno}';
		}
		
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
			 location.href = "/admin/usermanage";
		}
		function goods(){
			 location.href = "/admin/goodsmanage";
		}
		function admin(){
			 location.href = "/admin/adminmanage";
		}
		function password(){
			 location.href = "/admin/changepwd";
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
                <div id="admingoods" onclick="goods()">게시글관리</div>
                <div id="adminmaster" onclick="admin()">관리자 계정</div>
                <div id="chagepwd" onclick="password()">비밀번호 변경</div>
            </div>

        
            <div id="main-box">
            	<div class="noticeView">
            		<div class="noticetitle" type="button">제목 : ${a.notices_title}</div>         
            		<div id=noticedate>
                    	<p id="noticedate" type="button">작성일 : <fmt:formatDate value="${a.notices_date}" pattern="YYYY-MM-dd"/></p>
                    </div>
            		<div class="noticecnt" type="button">내용 : ${a.notices_content}</div>
            	
            		<div class="writebtn">
            		
            			<!-- 공지 상세페이지 버튼들  -->
						<div id="notice_button">
							<input type="button" id="writebtn" value="목록" onclick="notice()">
							<input type="button" id="writebtn" value="수정" onclick="">
							<input type="button" id="writebtn" value="삭제" onclick="">
		                 </div>
            			<%-- <div>
            				<button>공지목록</button>
            				
            				<c:if test="${!empty loginInfo and loginInfo.no == NoticesVO.admins_id}">
            				<a href="noticemodify?notices_id=${NoticesVO.notices_id}">수정하기</a>
            				<a href="javascript:noticedelete();">삭제</a>
            				</c:if>
            			</div> --%>
            		</div>
            	</div>
            	
				 		
<%-- 				         <a href="/admin/noticewrite" type="button" id="modifybtn">공지작성</a>&nbsp;&nbsp;
				          <a href="/admin/noticemodify?seqno=${noticeview.seqno}&page=${page}&keyword=${keyword}" type="button" id="modifybtn" >공지수정</a>&nbsp;&nbsp;
				          <a href="javascript:noticeDelete()" type="button" id="modifybtn">공지삭제</a>
                    </div> --%>
            </div>
        </div>
    </div>
</body> 
</html>