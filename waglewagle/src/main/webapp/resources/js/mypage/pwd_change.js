const reg = /^(((?=.*[a-zA-Z])(?=.*[0-9]))|((?=.*[a-zA-Z])(?=.*[!@#$%^&*]))|((?=.*[!@#$%^&*])(?=.*[0-9]))).{8,20}$/;
const valid = new RegExp(reg);

// 새 비밀번호의 비밀번호 규칙 검증
document.querySelector("#newPwd>input").addEventListener("input", (e) => {
    if(e.target.value.length < 8 || e.target.value.length > 20) {
        $("#alertMessageBox").text("비밀번호는 8자이상 20자이하만 가능합니다.");
    } else if(!valid.test(e.target.value)) {
        $("#alertMessageBox").text("영문, 숫자, 특수문자 중 2가지 이상을 넣어야합니다.");
    } else {
        $("#alertMessageBox").text("");
    }
});

// 비밀번호 변경
document.getElementById("pwdChangeButton").addEventListener("click", (e) => {
    const pwds = document.getElementsByClassName("pwdBox");
    let pwdVals = [];

    for(let i=0; i<pwds.length; i++){
        pwdVals.push(pwds[i].getElementsByTagName("input")[0].value);
    }
    $.ajax({
        url: "/pwd/check",
        type: 'post',
		contentType: 'application/json',
        data: JSON.stringify({
            data: pwdVals[0]
        }),
        success: function(data) {
            if(data){
                console.log("올바른 비밀번호입니다.");

                if(valid.test(pwdVals[1])){
                    console.log('가능한 비밀번호 입니다.');
                    if(pwdVals[1] != pwdVals[2]){
                        alert("새 비밀번호와 비밀번호 확인이 일치하지 않습니다.");
                        document.querySelector("#newPwd>input").value = null;
                        document.querySelector("#newPwdConfirm>input").value = null;
                        document.querySelector("#newPwd>input").focus();
                    } else {
                        $.ajax({
                            url: "/pwd/change",
                            type: 'post',
                            contentType: 'application/json',
                            data: JSON.stringify({
                                data: pwdVals[1]
                            }),
                            success: function(data) {
                                if(data){
                                    alert("비밀번호가 변경되었습니다.");
                                    $("#pwdBox > input").each(function(index, item) {
                                        item.val("");
                                    });
                                }
                            }
                        });
                    }
                } else {
                    alert("새 비밀번호를 사용할 수 없습니다. 영문, 숫자, 특수문자 중 2가지 이상 포함하고 8자이상 20자이하로 입력해주세요.");
                }
            } else {
                alert("현재 비밀번호가 올바르지 않습니다.");
            }
        }
    });
    
});