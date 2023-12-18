$(function(){
		$("#loginBtn").click(function(){
			if($("#users_email").val().length < 1 || $("#users_email").val() === ""){
				alert("아이디를 입력해주세요.");
				$("#users_email").focus();
				return
			}else if($("#users_pwd").val().length < 1 || $("#users_pwd").val() === ""){
				alert("비밀번호를 입력해주세요.");
				$("#users_pwd").focus();
				return
			}else{
				$("#loginForm").submit();
			}
			
		})
})