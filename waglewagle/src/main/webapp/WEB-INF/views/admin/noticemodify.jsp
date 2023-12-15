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
		const noticeDelete = () => {
		    if(confirm("선택한 공지를 정말 삭제 하시겠습니까?")==true) document.location.href='/admin/delete?seqno=${noticeview.seqno}';
		}
		
		
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
            	            
            		
                    <!-- 제목 -->
                    <div id="noticetitle">
                        <p id="titletxt">제목</p>
                        <input id="title" name="title" value="${view.notices_title}">
                    </div>

                    <!-- 내용 -->
                    <div id="noticecontent">
                        <p id="contenttxt">내용</p>
                        <textarea id="content" name="content" value="${view.notices_content}"></textarea>
                    </div>


                    <!-- 공지업로드 버튼 -->
                    <div id="noticebutton">
                    	<input type="button" id="writebtn" value="공지수정" onclick="modifyForm()">
                        <input type="button" id="writebtn" value="취소" onclick="history.back()">
                    </div>

<%-- 					
                    <!-- 공지업로드 버튼 -->
                    <div id="notice button">
                        <c:if test="{pre_seqno !=0}">
                        <a href="/admin/noticeview?seqno=${pre_seqno}&page=${page}keyword=${keyword }">이전</a>&nbsp;&nbsp;
                        </c:if>
                        <a href="/board/list?page=${page}&keyword=${keyword}">목록가기</a>&nbsp;&nbsp;
				 		<c:if test="${next_seqno != 0}">	
				          <a href="/admin/noticeview??seqno=${next_seqno}&page=${page}&keyword=${keyword}">다음</a>&nbsp;&nbsp;
				 		</c:if>
				         <a href="/admin/noticewrite">공지작성</a>&nbsp;&nbsp;
				          <a href="/admin/noticemodify?seqno=${noticeview.seqno}&page=${page}&keyword=${keyword}">공지수정</a>&nbsp;&nbsp;
				          <a href="javascript:noticeDelete()">공지삭제</a>
                    </div> --%>
            </div>
        </div>
    </div>
</body> 
</html>