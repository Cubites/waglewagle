function getLowerCategories(){
	var val= $("#goodsCategory1").val();
	
	$.ajax({
		
		type:'post',
		url : '/goods/categories',
		headers: {
			"accept":"application/json",
			"Content-Type":"application/json"
		},
		dataType:'json',
		data:{"upper":val},
		success: function(result){
			console.log(result);
			printLower(result);
		}
		
	})
}
	
	function printLower(result){
		
		let cate2 = $("#goodsCategory2");
		cate2.empty();
		var str ="<option value=''>2차 카테고리</option>";
		cate2.append(str);
		var str = "<option value='-1' style='display:none;'>hidden</option>";
		cate2.append(str);
		if(result === null || result===""){
			return;
		}
		
		for(const cate of result){
			
			 str ="";
			 str += "<option value='"+cate.category_id+"'>";
			 str  += cate.category_name+"</option>";
			 
			 cate2.append(str);		
		}
		
		
	}

	function submits2(){
		
		var price = $("#price").val();
		
		//숫자 보기좋게 변환해둔 것 다시 고쳐야함!
		price=price.replaceAll("￦","");
		price=price.replaceAll(",","");
		price=price.trim();
		$("#price").val(price);
		
		//input 태그 file에 파일을 추가하면 내부적으로 files라는 속성에 저장함
		//근데 이 속성 읽기만 가능하고 변경은 불가능함 뭔가 변경하고 싶으면 
		//dataTransfer객체를 사용해야함 MDN 무슨 표준 
		const dataTransfer = new DataTransfer();
		
		for (var i = 0; i < window.filesArr.length; i++) {
	        if (!window.filesArr[i].is_delete) {
	           //dataTransfer객체에 items에 add
	          dataTransfer.items.add(filesArr[i]);
	        }
	      }
		//himage id를 가진 돔객체에 files를 dataTransfer.files로 교체
		$("#himage")[0].files = dataTransfer.files;
		
		//대표이미지 이름 저장하기 위해 -> 파일 id는 file0으로 파일 fileNum로 저장되어있음 
		 var image_id = $(".image_th").attr("id"); //id값 가져오기
	      var fileNumber;
	      var image_th;
	      if(image_id !== undefined){ //id값이 존재한다면,
	      fileNumber = image_id.substring(image_id.indexOf("e") + 1); //fileNum가져오기
	      image_th = filesArr[fileNumber].name;	//filesArr에서 fileNumber에 해당하는 file객체에 이름 가져오기
	      }else{
	    	  fileNumber=null;
	    	  image_th ="";
	      }
	      
	      $("#goods_th_img").val(image_th);
	      //1차 카테고리가 24,25일 경우 2차 카테고리 null이어도 됨
	      var ca1 = $("#goodsCategory1").val();
	     
	      if(ca1 === '24' || ca1 === '25'){
	    	  
	    	  $("#goodsCategory2").val("-1");
	    	  
	      }
	      
	    //기존 Date에 현재 시간 시간 추가 
	    var timeStampString = getTimeStamp();
	    $("#goods_exph").val(timeStampString);
	  	
	   
		var form = $("#registForm");
		form.submit();
		
	}

   

    function getTimeStamp(){
    	let goodsDateString = $("#goodsDate");
    	
    	let now = new Date();
    	
    	//시간 추출 
    	var hours = ('0' + now.getHours()).slice(-2);
    	var min = ('0' + now.getMinutes()).slice(-2);
    	var seconds = ('0' + now.getSeconds()).slice(-2);
    	var timeString = hours + ':' + min + ':' + seconds;
    	//
    	var TimeStampString = goodsDateString.val() + " " + timeString;
    
    	
    	return TimeStampString;
    }