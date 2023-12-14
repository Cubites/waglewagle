// document.querySelectorAll(".favorArea img").forEach(item => {
// 	item.addEventListener("click", e => {
// 		e.target.parentNode.parentNode.removeChild(e.target.parentNode);
// 	});
// });
function deleteArea(e) {
	e.target.parentNode.parentNode.removeChild(e.target.parentNode);
	if(!document.getElementById("addArea")){
		let temp = document.createElement("div");
		temp.setAttribute("id", "addArea");
		temp.setAttribute("onclick", "addArea()");
		temp.innerHTML = "+";
		document.getElementById("flbItems").appendChild(temp);
	}
}

function addArea() {
	if(document.getElementsByClassName("favorArea").length >= 3){
		alert("관심지역은 3개까지만 등록할 수 있습니다.")
	} else {
		new daum.Postcode({
			oncomplete: function(data) {
				console.log(data.jibunAddress);
				let dongVal = "";
				let fullAddr = "";
				if(data.bname1){
					dongVal = data.bname1;
				} else {
					dongVal = data.bname;
				}
				let dong = data.jibunAddress.lastIndexOf("동 ");
				let eup = data.jibunAddress.lastIndexOf("읍 ");
				let lee = data.jibunAddress.lastIndexOf("면 ");
				if(dong != -1){
					fullAddr = data.jibunAddress.substr(0, dong + 1);
				} else if(eup != -1){
					fullAddr = data.jibunAddress.substr(0, eup + 1);
				} else {
					fullAddr = data.jibunAddress.substr(0, lee + 1);
				}

				document.getElementById("addArea").remove();

				let temp = document.createElement("div");
				temp.setAttribute("class", "favorArea");
				temp.setAttribute("onclick", "deleteArea(event)");
				temp.innerHTML = `
					<div>${dongVal}</div>
					<img src="/resources/images/close_icon.png">
					<input type="hidden" value="${fullAddr}">
				`;
				document.getElementById("flbItems").appendChild(temp);
				
				if(document.getElementsByClassName("favorArea").length < 3){
					let temp2 = document.createElement("div");
					temp2.setAttribute("id", "addArea");
					temp2.setAttribute("onclick", "addArea()");
					temp2.innerHTML = "+";
					document.getElementById("flbItems").appendChild(temp2);
				}
			}
		}).open();
	}
}

function saveAreas() {
	let areas = [];
	let temp = document.getElementsByClassName("favorArea")
	for(let i=0; i < temp.length; i++){
		areas.push(temp[i].querySelector("div").innerHTML);
	};
	console.log(areas);
}