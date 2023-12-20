<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="/resources/css/mypage/pwd_change.css">
</head>
<body>
	<div id="auctionsContainer">
        <div id="pwdNow" class="pwdBox">
            <div>현재 비밀번호</div>
            <input type="password">
        </div>
        <div id="newPwd" class="pwdBox">
            <div>새 비밀번호</div>
            <input type="password">
        </div>
        <div id="alertMessageBox">
            
        </div>
        <div id="newPwdConfirm" class="pwdBox">
            <div>비밀번호 확인</div>
            <input type="password">
        </div>
        <div id="pwdChangeButton">비밀번호 수정</div>
	</div>
    <script src="/resources/js/mypage/pwd_change.js"></script>
</body>
</html>