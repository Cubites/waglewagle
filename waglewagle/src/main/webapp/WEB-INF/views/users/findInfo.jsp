<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 찾기</title>
<link rel="stylesheet" href="/resources/css/common/common.css">
	<link rel="stylesheet" href="/resources/css/users/findInfo.css">
	<script src="https://code.jquery.com/jquery-latest.min.js"></script>
	<script src="/resources/js/users/findInfo.js"></script>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp"%>
	<div id="center">
		<div id="container">
			<div id="findFormArea">
				<div id="formHeaderArea">
					<div id="formHeaderId">아이디 찾기</div>				
					<div id="formHeaderPwd">비밀번호 찾기</div>				
				</div>
				<div id="formContent">
					<form id="findIdForm" method="post" action="find_id" class="">
						<!-- input data : find id  -->
						<div id="inputAreaId">
							<div class="nameArea">
								<div class="formText">이름</div>
								<input type="text" size="35" class="formInput" id="users_name1" name="users_name">
							</div>
							<div id="phoneArea">
								<div class="formText">전화번호</div>
								<div id="phoneNumber">
									<select name="pn" id="phoneFront">
										<option value="010">010</option>
										<option value="011">011</option>
										<option value="016">016</option>
									</select>
									<input type="text" size="30" id="phoneBack" class="formInput" placeholder="숫자만 입력해주세요"><br>
									<input type="hidden" id="phoneResult" name="users_phone" value="">
								</div>
							</div>
						</div>
						
						<div id="btnArea">
							<input type="button" id="findIdBtn" class="btn" value="아이디 찾기">
						</div>
						
					</form>	
					<form id="findPwdForm" method="post" action="find_pwd" class="hide">
						<!-- input data : find pwd -->
						<div id="inputAreaPwd">
							<div class="nameArea">
								<div class="formText">이름</div>
								<input type="text" size="35" class="formInput" id="users_name2" name="users_name">
							</div>
							<div id="emailArea">
								<div id="email">
									<div class="formText">이메일</div>
									<div class="space-between">
										<input type="text" size="23" class="formInput" id="users_email" name="users_email"><input type="button" class="basicBtn pinkBtn" value="인증번호 발송" id="sendNum">
									</div>	
								</div>
								<div id="authNum">
									<div class="space-between">
										<div class="wrap">
											<input type="text" size="18" id="validNum" class="formInput">
											<input type="text" size="2" id="timer" value="" readonly>
										</div>
										<input type="button" class="basicBtn pinkBtn" value="확인" id="validCheck">
									</div>
								</div>
							</div>
						</div>
						<div id="btnArea">
							<input type="button" id="findPwdBtn" class="btn" value="비밀번호 찾기">
						</div>
					</form>
					
					<form id="changePwdForm" method="post" action="change_pwd" class="hide">
						<!-- input data : change pwd -->
						<div id="inputAreaChangePwd">
							<div id="passwordArea">
								<div id="pwd1">
									<div class="formText">비밀번호</div>
									<input type="password" size="40" class="formInput" id="users_pwd" name="users_pwd" placeholder="영문,특수문자,숫자 포함 8자 이상"><br>
								</div>
								<div id="pwd2">
									<div class="formText">비밀번호 확인</div>
									<input type="password" size="40" class="formInput" id="users_pwd2">
									<div class="flex-end">
										<div id="pwdCheckTxt">비밀번호가 일치하지 않습니다.</div>
									</div>
								</div>
							</div>
						</div>
						
						<div id="btnArea">
							<input type="button" id="changePwdBtn" class="btn" value="비밀번호 변경하기">
						</div>
						
					</form>	
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>