$(function(){
	$('.levelStyle a').click(function(){
		$(this).parent('.levelStyle').next('.MenuItem').show();
		$(this).parents('.level').siblings('li').find('.MenuItem').hide();
	})
})