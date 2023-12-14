document.getElementById("pwdChangeButton").addEventListener("click", (e) => {
    const pwds = document.getElementsByClassName("pwdBox");
    let pwdVals = [];

    for(let i=0; i<pwds.length; i++){
        pwdVals.push(pwds[i].getElementsByTagName("input")[0].value);
    }
    $.ajax({
        url: "/pwd/change",
        type: 'post',
        data: pwdVals[0],
        success: function(data) {
            if(data.check){
                console.log("올바른 비밀번호입니다.");

                let reg = /^(((?=.*[a-zA-Z])(?=.*[0-9]))|((?=.*[a-zA-Z])(?=.*[!@#$%^&*]))|((?=.*[!@#$%^&*])(?=.*[0-9]))).{8,20}$/;

                let valid = new RegExp(reg);
                if(valid.test(pwdVals[1])){
                    console.log('가능한 비밀번호 입니다.');
                    if(pwdVals[1] != pwdVals[2]){
                        alert("새 비밀번호와 비밀번호 확인이 일치하지 않습니다.");
                        document.querySelector("#newPwd>input").value = null;
                        document.querySelector("#newPwdConfirm>input").value = null;
                        document.querySelector("#newPwd>input").focus();
                    } else {
                        console.log(pwdVals);
                        console.log("비밀번호 수정 가능")
                    }
                } else {
                    console.log("다른 비밀번호를 입력해주세요.");
                }
            }
        }
    });
    
});