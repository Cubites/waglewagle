	$(function(){
		//시구동 -> 동
		$(".productAddr").each(function(){
			var addr = $(this).text().trim();
			var dong = addr.substring(addr.lastIndexOf(' ')+1);
			$(this).text(dong);
		});
		
	})
	
	$(function(){
    var swiper = new Swiper('.swiper', {
        slidesPerView: 5,
        spaceBetween: 30,
        loop: true,
        loopAdditionalSlides: 1,
        navigation: {
          nextEl: '.swiper-button-next',
          prevEl: '.swiper-button-prev',
        },
      });
		
	})
	$(function(){
    var swiper = new Swiper('.swiper.banner', {
        slidesPerView: 1,
        loop: true,
        loopedSlides: 1,
        autoplay:{
        	delay:3000,
        	disableOnInteraction: false,
        },
        navigation: {
          nextEl: '.swiper-button-next',
          prevEl: '.swiper-button-prev',
        },
      });
		
	})
		
	$(function(){
		$(".swiper-slide").mouseover(function(){
			let now = new Date();
			let expDate = new Date($(this).find(".popProduct").find(".avgPrice_expDateArea").find(".avg_expValue").find(".productExp").text());
			let leftTime = expDate - now;
			
			const days = Math.floor(leftTime/(1000*60*60*24));
	     	const hours = ("0"+Math.floor(leftTime %(1000*60*60*24)/(1000*60*60))).slice(-2);

			
			$(".leftTime").text(days+"일 "+hours+"시간");
			$(this).find('.popProduct').find(".leftTime").removeClass("hide");
		})
		$(".swiper-slide").mouseout(function(){
			$(this).find('.popProduct').find(".leftTime").addClass("hide");
		})
	})