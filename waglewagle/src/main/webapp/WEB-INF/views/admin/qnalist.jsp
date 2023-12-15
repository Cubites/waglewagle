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
				<input type="text" name="keyword" id="keyword" style="width:35%; height:30px; border:2px solid #168; font-size: 16px"
         		placeholder="문의제목,작성자 검색내용 입력하세요" onkeydown="press()">
        <input type="button" value="검색" 
             style="width:5%; height:30px; background:828080;color:black; font-weight:bold; cursor:pointer;"
             	onclick="search()">		
		 <br><br> 
		 <div class="noticelist">	
                    <table class="nlist">

                        <colgroup>
                            <col width="80px" />
                            <col width="*" />
                            <col width="100px" />
                            <col width="100px" />
                            <col width="100px" />
                        </colgroup>

                        <thead>
                            <tr>
                                <th>번호</th>
                                <th>제목</th>
                                <th>작성일</th>
                                <th>삭제하기</th>
                            </tr>
                        </thead>
                        
                        <tbody>
                            <!-- <tr>
                                <td class="nonotice" colspan="8">등록된 공지가  없습니다.</td>
                            </tr> -->
    
                                    
                            <tr>
                                <td>10</td>
                                <td class="nlist_l">
                                    <a href="notice_view.html">공지 제목</a>
                                </td>
                                <td class="date">2023-01-01</td>
                                <td><button>삭제하기</button></td>
                            </tr>
                            
                            <tr>
                                <td>9</td>
                                <td class="nlist_l">
                                    <a href="notice_view.html">공지 제목</a>
                                </td>
                                <td class="date">2023-01-01</td>
                                <td><button>삭제하기</button></td>
                            </tr>
    
                            <tr>
                                <td>8</td>
                                <td class="nlist_l">
                                    <a href="notice_view.html">공지 제목</a>
                                </td>
                                <td class="date">2023-01-01</td>
                                <td><button >삭제하기</button></td>
                            </tr>
    
                        </tbody>
                    </table>
            </div>
        <%-- <table class="InfoTable">
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>작성일</th>
                <th>조회수</th>
            </tr>
            <c:forEach items="${list}" var="list">     
	            <tr onMouseover="this.style.background='#46D2D2'" onMouseout="this.style.background='white'">
	                <td>${list.seq}</td>
	                <td style="text-align: left;"><a href="/board/view?seqno=${list.seqno}&page=${page}&keyword=${keyword}" 
	                   onMouseover="this.style.textDecoration='underline'" 
	                   onMouseout="this.style.textDecoration='none'">${list.title}</a></td>
	                <td>${list.writer}</td>
	                <!-- <td>${list.regdate}</td>  -->
	                	<td><fmt:formatDate value="${list.regdate}" type="both" pattern="yyyy-MM-dd [E] a hh:mm:ss" /></td>
	                <td>${list.hitno}</td>
	            </tr>
			  </c:forEach>
        </table>   --%>
        <br>
		  <div>${pageList}</div>
		  <br>
        <div class="bottom_menu">
        	<a href="/board/list?page=1">처음으로</a>&nbsp;&nbsp;
          <a href="/board/write">글쓰기</a>&nbsp;&nbsp;          
        </div>
            </div>
        </div>
    </div>   
</body>

</html>