$(function(){
	$('.div_title').click(function(){
		if($(this).attr('flag')==1){
			$(this).attr('flag','0');
			$(this).next('div').fadeIn(500);
		}else{
			$(this).attr('flag','1');
			$(this).next('div').fadeOut(500)
		}
	})

	//滚动文字
	var scrtime;
	$(".notice").hover(function(){
		clearInterval(scrtime);
	},function(){
		scrtime=setInterval(function(){
			var $ul=$(".notice ul");
			var liHeight=$ul.find('li:last').height();
			$ul.animate({marginTop: liHeight+20+"px"},1000,function(){
				$ul.find("li:last").prependTo($ul)
				$ul.find("li:first").hide();
				$ul.css({marginTop:0});
				$ul.find("li:first").fadeIn(1000);
			})
		},4000)
	}).trigger("mouseleave");


	// 鼠标移入代码语言、代码分类切换
	$(".choose_area span").click(function(){
		var index=$(this).index();
		$(this).addClass('span_hover')
		$(this).siblings('span').removeClass('span_hover').addClass('not_hover')

		$(this).parent('div').siblings('ul').hide()
		$(this).parent('div').siblings('ul').eq(index).show();
	})
})

