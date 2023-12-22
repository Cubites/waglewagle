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
    <link rel="stylesheet" href="/resources/css/admin/adminlist.css"/>
    <script>
	    function noticewrite(){
			 location.href = "/admin/noticewrite";
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
			 location.href = "/admin/userManageList";
		}
		function goods(){
			 location.href = "/admin/goodsManageList";
		}
		function admin(){
			 location.href = "/admin/adminmanage";
		}
		function password(){
			 location.href = "/admin/changepwd";
		}
		function adminjoin(){
			location.href="/admin/join";
		}
    </script>
</head> 
<body>
	<div id="header">
        <div id="headerContainer">
            <br/>
            <img src="/resources/images/log.jpg" title="와글와글 로고" onclick="notice()"/>
        </div>
    </div>

    <div id="center">
        <div id="container">
            <div id="side-box">
                <div id="notice" onclick="notice()"><a href=""></a>공지</div>
                <div id="qna" onclick="qna()">문의</div>
                <div id="showdata" onclick="stats()">통계</div>
                <div id="adminuser" onclick="user()">회원관리</div>
                <div id="admingoods" onclick="goods()">게시글관리</div>
                <div id="adminmaster" onclick="admin()">관리자 계정</div>
                <div id="chagepwd" onclick="password()">비밀번호 변경</div>
            </div>
            <div id="main-box">
            
            	<!-- 회원 검색 -->
                <div id="plus"> 검색 
                	<input type="text" size="20"> 
                	<button>조회하기</button>
                </div>
                
                <!-- 관리자 추가 버튼  -->
				<div>
					<input type="button" id="writebtn" value="관리자추가" onclick="adminjoin()">
                 </div>
                 
                 <!-- 회원 리스트 -->
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
                                <th>이름</th>
                                <th>관리단계<th>
                                <th>삭제하기</th>
                            </tr>
                        </thead>
                        
                        <tbody>
                            <tr>
                                <td colspan="5">등록된 관리자가 없습니다.</td>
                            </tr> 
    
                                    
                           <!--  <tr>
                                <td>10</td>
                                <td class="nlist_l">
                                    <a href="notice_view.html">망곰이01</a>
                                </td>
                                <td class="date">0</td>
                                <td><button>삭제하기</button></td>
                            </tr>
                            
                            <tr>
                                <td>9</td>
                                <td class="nlist_l">
                                    <a href="notice_view.html">망곰이01</a>
                                </td>
                                <td class="date">0</td>
                                <td><button>삭제하기</button></td>
                            </tr>
    
                            <tr>
                                <td>8</td>
                                <td class="nlist_l">
                                    <a href="notice_view.html">망곰이01</a>
                                </td>
                                <td class="date">0</td>
                                <td><button >삭제하기</button></td>
                            </tr> -->
    
                        </tbody>
                    </table>
            </div>
            </div>
        </div>
    </div>
</body> 
</html>