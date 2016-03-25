$(function(){
	$('#send').click(function(){
		$('.error').remove();
		// 获取页面的四个内容
		var softname=$('.softname').val().trim();
		var description=CKEDITOR.instances.description.getData();
		var file=$('.choose_file').val();

		// 验证作品名字是否填写
		if(softname.length<=0){
			var html=$("<span id='maodian2' class='a2 error'>请填写作品名称</span>")
			$('.softname').parent('div').append(html);
			location.hash="maodian2";
		}

		// 验证描述是否填写
		if(description.length<=0){
			var html=$("<span id='maodian3' class='a2 error'>请填写描述</span>")
			$('#description').before(html);
			location.hash="maodian3";
		}

		// 验证
		if(file.length<=0){
			var html=$("<span id='maodian4' class='a2 error'>请选择文件</span>")
			$('.choose_file').after(html)
			location.hash="maodian4";
		}else{
			var lastIndex=file.lastIndexOf(".")
			var expandName=file.substring(lastIndex)
			if(expandName==".zip"||expandName==".rar"){
			}else{
				var html=$("<span id='maodian4' class='a2 error'>请选择rar或者zip后缀的文件</span>")
				$('.choose_file').after(html)
				location.hash="maodian4";
			}
		}
		
		//检查是否有错，然后再验证用户是否参加了这场比赛,参加了就不提示您已经残疾了这个比赛
		if($(".error").length<=0){
			//
			var userId=$("#userId").val()
			if(userId==""){
				alert("请登录用户再参加比赛")
			}else{
				//获取下拉框选择了哪一项
				var index=$("#competitionId option:selected").index()
				//
				var noteIds=$(".hidden_noteIds").find('input').eq(index).val();
				var snoteIds=noteIds.split(",");
				var exists="false";
				for(var i=0;i<snoteIds.length;i++){
					if(userId==snoteIds[i]){
						exists="true";
					}
				}
				if(exists=="true"){
					alert("你已经参加了这个竞赛")
				}else{
					if(exists=="false"){
						$("#softUpload").submit();
					}
				}
			}
//			$("#softUpload").submit()
		}
	})

	$('.choose_file').change(function(){
		$('.info').text('你选择的文件:'+$(this).val())
		$('.info').show();
	})
})

