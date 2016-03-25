$(function(){
	var id=$("#id").val();
	$(".save").click(function(){
		$(".error").remove();
		var type=$(".type").val().trim();
		if(type.length<=0){
			var html=$("<span class='error'>不能为空</span>");
			$(".type").after(html);
		}
		if(id==""||id==null){//说明是增加的操作
			//2，ajax验证不能相同
			$.ajax({
	            url: "label_exists.action",
	            // 数据发送方式
	            type: "post",
	            // 接受数据格式
	            dataType : "json",
	            // 要传递的数据
	          	data : "type="+type,
	            // 回调函数，接受服务器端返回给客户端的值，即result值
	            success : check   
			}); 
		}else{//否则是编辑操作
			//2,ajax验证不能相同（注意，更新的时候，更新的这个字段可以和自己一样，如果要更新成别的,那么就要判断是否一样了）
			$.ajax({
	            url: "label_exists1.action",
	            // 数据发送方式
	            type: "post",
	            // 接受数据格式
	            dataType : "json",
	            // 要传递的数据
	          	data : "label="+language+"&id="+id,
	            // 回调函数，接受服务器端返回给客户端的值，即result值
	            success : check 
			}); 
		}
	})
	
})

function check(result){
	var json=eval("("+result+")");
	if(json.exists=="true"){
		var html=$("<span class='error'>已经存在</span>");
		$(".label").after(html);
	}else{
		if($(".error").length<=0){
			$("#label_form").submit();
		}
	}
}
