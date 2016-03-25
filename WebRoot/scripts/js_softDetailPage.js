$(function(){
	$('.percent').each(function(i){
		var percent=$(this).attr('percent')
		doProgress(0,i,percent);
	})
	
	var userId=$("#userId").val();
	var noteIds=$("#noteIds").attr("value");
	var snoteIds=noteIds.split(",")
	var exists="false";
	
	$("#vote_button").click(function(){
		if(userId==""){
			alert("请先登录再投票")
		}else{
			for(var i=0;i<snoteIds.length;i++){
				if(userId==snoteIds[i]){
					exists="true";
				}
			}
			if(exists=="true"){
				alert("您已经投票")
			}else{
				$("#vote_form").submit();
			}
		}
	})
	
	$("#download").click(function(){
		if(userId==""){
			alert("请先登录再下载")
		}else{
			$("#download_form").submit();
		}
	})

})


//慢慢显示投票进度条的函数
function doProgress(i,position,percent){
	i=i+1;
	if(i+1>percent){
		i=percent;
	}
	if(i<=percent){
		setTimeout("doProgress("+i+","+position+","+percent+")",20);
		$('.percent').eq(position).css({width:i+"%"})
	}
}



