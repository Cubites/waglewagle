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
    
    <link rel="stylesheet" href="/resources/css/admin/join.css"/>
    <script>
		    function loginCheck() {
		    	if ($("#email").val() == '') {
		    		alert("이메일을 입력해 주세요");
		    		$("#email").focus();
		    		return false;
		    	}
		    	if ($("#pwd").val() == '') {
		    		alert("비밀번호를 입력해 주세요");
		    		$("#pwd").focus();
		    		return false;
		    	}
		    }
		    
		    
		    function selectDiv(element) {
		        const selectedDiv = document.querySelector('.selected');
		        if (selectedDiv) {
		            selectedDiv.classList.remove('selected');
		        }
		
		        element.classList.add('selected');
		    }
		
	   
	    	$(function(){
				$("#emailCheck").click(function(){ //click이벤트가 실행되면 -> ajax가 실행되는거고 
					$.ajax({
						url:'emailCheck.do',
						data:{email:$('#email').val()},
						success:function(res){ //(요청이 정상적으로 일어나면 실행되는것) or 실행한 경우를 굳이 코딩해줘서 출력하지는 않는다. 
							console.log(res);
							if(res == 'true'){
								alert('이메일이 중복되었습니다.');
								$("#email").val('');
								$("#email").focus();
							}else{
								dupCheck = true;
								alert('사용가능한 이메일입니다.');
								//$('#email').attr('readonly','readonly'); //수정이 안되게 하는거...
							} 
						}
						
					})
				})
			})
		
		    
		    
		    function addAdmin() {
		        // 관리자 추가 로직을 추가하세요.
		        alert("관리자 추가");
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
                <div id="plus">
                    <div id="head-plus">관리자 추가</div>
                    <div id="body-plus">
                        <table cellpadding="5" cellspacing="0" background-color="eeeeee">
   
                            <tr>
                              <td>*아이디</td>
                              <td><input class="onborder" id="admin-email" type="text" placeholder="wagle123@gmail.com"/>
                              	
                                  <input type="button" value="중복확인" onclick="emailCheck()" id="emailCheck">
                              </td>
                            </tr>
                      
                            <tr>
                              <td>*비밀번호</td>
                              <td><input class="onborder" id="admin-pwd" type="password" min="8" placeholder="영문,숫자 합 8자리 이상"/></td>
                            </tr>
                      
                            <tr>
                              <td>*비밀번호확인</td>
                              <td><input class="onborder" id="admin-pwd" type="password" min="8" placeholder="동일한 비밀번호를 입력"/> </td>
                            </tr>
                      
                            <tr>
                              <td>*이름</td>
                              <td><input class="onborder" for="admin-name" type="text" max="10" placeholder="이름을 입력하세요."/></td>
                            </tr>
                      
                            <tr>
                              <td>전화번호:</td>
                              <td><input class="onborder" type="text" size="3" placeholder="010"> -
                                  <input class="onborder" type="text" size="4"> -
                                  <input class="onborder" type="text" size="4">
                              </td>
                            </tr>
                   
                            <tr>
                              <td>관리자 권한</td>
                              <td><select class="onborder">
                                    <option>====전체====</option>
                                    <option>일반 관리자</option>
                                    <option>부관리자</option>
                                  </select>            
                              </td>
                            </tr>
                         </table>

                        <button onclick="addAdmin()">추가하기</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body> 
</html>