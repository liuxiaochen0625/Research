//评论的js
$(function(){
	$("#comment_button").click(function(){
		var userId=$("#userId").val();
		var flag=$("#flag").val();
		var comment=$("#comment").val();
		var toId=$("#toId").val();
		
		if(comment==""){
			alert("评论内容不能为空");
		}else{
			$.ajax({
	            url: "comment_comment.action?flag="+flag+"&comment="+encodeURI(encodeURI(comment))+"&userId="+userId+"&toId="+toId,
	            // 数据发送方式
	            type: "post",
	            // 接受数据格式
	            dataType : "json",
	            // 回调函数，接受服务器端返回给客户端的值，即result值
	            success : check 
			}); 
		}
	});
})

function check(result){
	var json = eval("("+result+")");
	var result = json.result;
	if(result=='yes'){
		var username = json.username;
		var time = json.time;
		var img = json.img;
		var comment = json.comment;
		var html=$("<li class='comments_item'>"
						+"<div class='comments_item_bd'>"
							+"<div class='ui_avatar'>"
								+"<a href=''>" + "<img src='"+img+"' alt='"+username+"' width='35' height='35'>" + "</a>"
							+"</div>"
							+"<div class='comment_content'>"
								+"<a href=''>" +username+ "</a>"
								+" :"+comment
								+"<div class='comments_op'>"
									+"<span>"+time+".0</span>"
								+"</div>"
							+"</div>"
						+"</div>"
					+"</li>");
		//将html加到评论列表后面
		$("#comment_ul").append(html);
		$("#comment").val("");
		
	}else{
		alert('评论出现了问题');
	}
	
}

/*<li class="comments_item">
<div class="comments_item_bd">
	<div class="ui_avatar">
		<a href=""><img src="/Research/images/source/default.jpg" alt="" width="35" height="35"/></a>
	</div>
	<div class="comments_content">
		<a href="">${user.username }</a>
		:${comment }
		<div class="comments_op">
			<span>${datetime }</span>
		</div>
	</div>
</div>
</li>*/