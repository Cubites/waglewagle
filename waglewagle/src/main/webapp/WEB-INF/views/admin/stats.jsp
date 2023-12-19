<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <link rel="stylesheet" href="/resources/css/admin/qnalist.css"/>

	<script>
	
		const search = () => {
			const keyword = document.querySelector('#keyword');
			document.location.href='/admin/qnalist?page=1&keyword=' + keyword.value;
		}
		
		const press = () => {
			if(event.keyCode == 13) search();
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
               통계페이지
            </div>
        </div>
    </div>
</body>
</html>