<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원가입</title>
	<link rel="stylesheet" href="/resources/css/common/common.css">
	<link rel="stylesheet" href="/resources/css/users/join.css">
	<script src="https://code.jquery.com/jquery-latest.min.js"></script>
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
	
		// 폼 제출 버튼 클릭 -> 동네리스트, 전화번호 값 hidden 태그에 저장 후 제출
		$(function(){
			$("#joinBtn").click(function(){
				$("#phoneResult").val($("#phoneFront").val() + '-' + $("#phoneBack").val().slice(0,4) + '-' + $("#phoneBack").val().slice(4));
				
				var dongResult = '';
				for(var i=0; i<3; i++){
					if(dongList[i].length > 0){
						dongResult += (dongList[i] + ',');
					}
				}
				while(dongResult.slice(-1) === ','){
					dongResult = dongResult.slice(0, -1);
				}
				
				$("#users_addr_list").val(dongResult);
				$("#joinForm").submit();
			})
		})
	
		//이메일 형식 체크(정규식)
		function isEmail(email){
			let reg = new RegExp("([!#-'*+/-9=?A-Z^-~-]+(\.[!#-'*+/-9=?A-Z^-~-]+)*|\"\(\[\]!#-[^-~ \t]|(\\[\t -~]))+\")@([!#-'*+/-9=?A-Z^-~-]+(\.[!#-'*+/-9=?A-Z^-~-]+)*|\[[\t -Z^-~]*])");
			
			return reg.test(email);
		}
		
		//이메일 형식 올바른지 아닌지
		var validEmail = false;
		//입력된 이메일 값 실시간 체크
		$(function(){
			$("#users_email").keyup(function(){
				 
				if(!isEmail($("#users_email").val())){
					$("#emailCheckText").text("올바른 이메일을 입력해주세요.");
					$("#emailCheckText").css('visibility', 'visible');
					$("#isEmailDup").attr("disabled", true);
					validEmail = false;
				}else{
					$("#emailCheckText").css('visibility', 'hidden');
					$("#isEmailDup").attr("disabled", false);
					validEmail = true;
				}
			})
		})
		
	
		$(function(){	
			// 이메일 중복 체크
			$("#isEmailDup").click(function(){
				if(validEmail){
				
					$.ajax({
						url:"emailCheck",
						data:{users_email : $("#users_email").val()},
						success: function(data){
							if(data === 'true'){
								$("#emailCheckText").text("중복된 이메일입니다.");
								$("#emailCheckText").css('visibility', 'visible');
								$("#users_email").focus();
							}else{
								$("#emailCheckText").text("사용가능한 이메일입니다.")
								$("#users_email").attr('readonly', 'readonly');
								$("#emailCheckText").css('visibility', 'visible');
							}
						}
					})
				}
			})
			
		})
	
		var sendNumClicked = false;
		var intervalVar;
		$(function(){
			$("#sendNum").click(function(){
				sendNumClicked = true;
				$('#sendNum').prop('value', '인증번호 재전송');
				$('#validNum').val('');
				$.ajax({
					url:"sendAuthNum",
					data:{users_email : $("#users_email").val()},
					success: function(data){
						console.log(data);
					}
				});
				
				resetTimer();
				
			});
			
			function resetTimer(){
				clearInterval(intervalVar);
				
				timeSec = 180;
				$("#timer").prop('value', getTimeString(timeSec));
				//console.log("timeSec before : " + timeSec);
				
				intervalVar = setInterval(function(){
					if(timeSec !== 0){
						timeSec -= 1;
						$('#timer').prop('value', getTimeString(timeSec));
					}else{
						clearInterval(intervalVar);
						intervalVar = undefined;
					}
					//console.log("timeSec after : " + timeSec);
				}, 1000);
			}
			
			function getTimeString(sec){
			      var minutes = '' + Math.floor(sec / 60);
			      var dividedSec = sec % 60;
			      if (dividedSec < 10) {
			         dividedSec = '0' + dividedSec;
			      }
			      return minutes + ":" + dividedSec;
			}
		});	
		
		var isValidEmail = false;
		$(function(){
			$("#validCheck").click(function(){
				if(sendNumClicked && timeSec > 0){
					$.ajax({
						url:"checkAuthNum",
						data:{validNum : $("#validNum").val()},
						success: function(data){
							console.log(data);
							$('#timer').val('');
							if(data === 'true'){
								clearInterval(intervalVar);
								intervalVar = undefined;
								alert("인증이 완료되었습니다.");
								isValidEmail = true;
							}else{
								alert("인증번호가 일치하지 않습니다. ");
								isValidEmail = false;
							}
						}
					});
				}
			})
		})
		
		
		var dongList = ['', '', '']
	
		function clickAddDong1() {
	        new daum.Postcode({
	            oncomplete: function(data) {
	                
	                var dongName = '';

	                if(data.bname !== '' && data.bname1 === ''){
	                	dongName += data.bname;
	                }else if(data.bname !== '' && data.bname1 !== ''){
	                	dongName += data.bname1;
	                }
	                
	                $(document).ready(function(){
	                	$("#interest_dong1 > .addDongBtn").val(dongName);
	                	$("#interest_dong1 > .addDongBtn").css('backgroundColor', '#CF5C5C');
	                	$("#interest_dong1 > .addDongBtn").css('fontSize', '15px');	                	
	                });
	                var jibun = ''
	                if(data.jibunAddress === ''){
	                	jibun += data.autoJibunAddress;
	                }else{
	                	jibun += data.jibunAddress;
	                }
	                var a = dongName.charAt(dongName.length-1);
	                var dongnae = jibun.substring(0, jibun.lastIndexOf(a)+1);
	                
	               
	                dongList[0] = dongnae;

	            }
	        }).open();
	    }
		function clickAddDong2() {
	        new daum.Postcode({
	            oncomplete: function(data) {
	                
	                var dongName = '';
	                
	                if(data.bname !== '' && data.bname1 === ''){
	                	dongName += data.bname;
	                }else if(data.bname !== '' && data.bname1 !== ''){
	                	dongName += data.bname1;
	                }
	                
	                $(document).ready(function(){
	                	$("#interest_dong2 > .addDongBtn").val(dongName);
	                	$("#interest_dong2 > .addDongBtn").css('backgroundColor', '#CF5C5C');
	                	$("#interest_dong2 > .addDongBtn").css('fontSize', '15px');	
	                });
	                var jibun = ''
	                if(data.jibunAddress === ''){
	                	jibun += data.autoJibunAddress;
	                }else{
	                	jibun += data.jibunAddress;
	                }
	                var a = dongName.charAt(dongName.length-1);
	                var dongnae = jibun.substring(0, jibun.lastIndexOf(a)+1);
	                
	                dongList[1] = dongnae;
	            }
	        }).open();
	    }
		function clickAddDong3() {
	        new daum.Postcode({
	            oncomplete: function(data) {
	                
	                var dongName = '';
	                
	                if(data.bname !== '' && data.bname1 === ''){
	                	dongName += data.bname;
	                }else if(data.bname !== '' && data.bname1 !== ''){
	                	dongName += data.bname1;
	                }
	                
	                $(document).ready(function(){
	                	$("#interest_dong3 > .addDongBtn").val(dongName);
	                	$("#interest_dong3 > .addDongBtn").css('backgroundColor', '#CF5C5C');
	                	$("#interest_dong3 > .addDongBtn").css('fontSize', '15px');	
	                });
	                var jibun = ''
	                if(data.jibunAddress === ''){
	                	jibun += data.autoJibunAddress;
	                }else{
	                	jibun += data.jibunAddress;
	                }
	                var a = dongName.charAt(dongName.length-1);
	                var dongnae = jibun.substring(0, jibun.lastIndexOf(a)+1);
	                
	                dongList[2] = dongnae;
	            }
	        }).open();
	    }
		
	</script>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
	<div id="center">
		<div id="container">
			<div id="joinFormArea">
				<div id="formHeader">회원가입</div>
				<div id="formContent">
					<form id="joinForm" method="post" action="join">
						<!-- input data  -->
						<div id="inputArea">
							<div id="emailArea">
								<div class="formText">이메일</div>
								<div class="space-between" id="enterEmail">
									<input type="text" size="27" class="formInput" id="users_email" name="users_email"> <input type="button" class="basicBtn" value="이메일확인" id="isEmailDup"><br>
								</div>
								
								<div id="emailCheckText">이메일 체크 메세지</div>
								
								<div class="space-between">
									<div>
										<input type="text" size="10" id="validNum" class="formInput">
										<input type="text" size="2" id="timer" value="" readonly>
									</div>
									<div id="validButtons">
										<input type="button" id="sendNum" value="인증번호 발송"><input type="button" id="validCheck" value="인증번호 확인"><br>
									</div>
								</div>
							</div>
							<div id="passwordArea">
								<div id="pwd1">
									<div class="formText">비밀번호</div>
									<input type="password" size="40" class="formInput" name="users_pwd" placeholder="영문,특수문자,숫자 포함 8자 이상"><br>
								</div>
								<div id="pwd2">
									<div class="formText">비밀번호 확인</div>
									<input type="password" size="40" class="formInput">
								</div>
								<div class="flex-end">
									<div id="pwdCheckTxt">비밀번호가 일치하지 않습니다.</div>
								</div>
							</div>
							<div id="nickArea">
								<div class="formText">닉네임</div>
								<div  class="space-between">
									<input type="text" size="27" name="users_nick" class="formInput"><input type="button" class="basicBtn" value="중복확인"><br>
								</div>
								<div id="nickCheckTxt">사용가능한 닉네임입니다.</div>
							</div>
							<div id="name_gender">
								<div id="nameArea">
									<div class="formText">이름</div>
									<input type="text" size="27" name="users_name" class="formInput"><br>
								</div>
								<div id="genderArea">	
									<div class="formText">성별</div>
									<select name="users_gender">
										<option value="0">남자</option>
										<option value="1">여자</option>
									</select>
								</div>
							</div>
							<div id="phoneArea">
								<div class="formText">전화번호</div>
								<div id="phoneNumber">
									<select name="pn" id="phoneFront">
										<option value="010">010</option>
										<option value="011">011</option>
										<option value="016">016</option>
									</select>
									<input type="text" size="30" id="phoneBack" class="formInput" placeholder="전화번호"><br>
									<input type="hidden" id="phoneResult" name="users_phone" value="">
								</div>
							</div>
							<div id="dongArea">
								<div class="formText" id="interest_dong">관심 지역 설정</div>
								<!-- 주소검색 버튼
								<input type="button" onclick="sample4_execDaumPostcode()" value="주소검색하기"><br>
								 -->
								 
								<div id="addDongBtnArea">
									<div id="interest_dong1">
										<input type="button" class="addDongBtn" onclick="clickAddDong1()" value="+">
									</div>
									<div id="interest_dong2">
										<input type="button" class="addDongBtn" onclick="clickAddDong2()" value="+">
									</div>
									<div id="interest_dong3">
										<input type="button" class="addDongBtn" onclick="clickAddDong3()" value="+">
									</div>
									<div name="dongResult">
										<input type="hidden" id="users_addr_list" name="users_addr_list" value="">
									</div>
								</div>
							</div>
						</div>
						<!-- submit 버튼 -->
						<div id="submitArea">
							<input type="button" id="joinBtn" value="회원가입">
						</div>
						
					</form>	
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>