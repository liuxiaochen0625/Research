$(function(){
	//点击图标返回顶部
	var back = $('.back');
    back.click(function() {
                var timer = setInterval(function(){
                    $(window).scrollTop($(window).scrollTop()-30);
                    if($(window).scrollTop() == 0){
                        clearInterval(timer);
                    }
                },2);
            });
    $(window).scroll(function() {
           if($(window).scrollTop() < 100){
                    back.hide(500);
                } else {
                    back.show(500);
                }
     });


	// 设置选择性提交按钮
	$('#tijiao').mouseover(function(){
		// alert('ok')
		if($('#text').val()==""||$('#text').val()=="请输入资源关键字"){
			
		}else{
			$('#tijiao').click(function(){
				$('#form').submit();
			})
		}
	})

	//输入框获得焦点
	$('#text').focus(function(){
		$(this).addClass('text1');
		if($(this).val()=="请输入资源关键字"){
			$(this).attr('value','');
		}
	})
	// 输入框失去焦点
	$('#text').blur(function(){
		$(this).removeClass('text1').addClass('text');
		if($(this).val()==""){
			$(this).attr('value','请输入资源关键字');
		}
	})

	  // 资源类型切换
    $('.source_type1  h2 a').mouseover(function(){
        var cur=$(this).index();
        $(this).addClass('type_hover').siblings('a').removeClass('type_hover');
        $(this).parents('h2').siblings('ul').eq(cur).show().siblings('ul').hide();
        if(cur=='1'){
        	$(this).parents('h2').siblings('ul').eq(cur).addClass('type1')
        }else{
        	$(this).parents('h2').siblings('ul').eq(cur).removeClass('type1')
        }
    })
    
	  //显示下拉菜单
	  $(".person_info").hover(function(){
	    $(".scrollbar").show();
	  },function(){
	    $(".scrollbar").hide();
	  })
	  
		  //点击展开,展开div
	  $(".click_expand").click(function(){
	    $(this).hide();
	    $(this).next('div').show();
	    $(this).siblings('.click_close').show();
	    $(this).parents('li').addClass('expand')
	  })
	  //点击收起,隐藏div
	  $(".click_close").click(function(){
	    $(this).hide();
	    $(this).prev('div').hide();
	    $(this).siblings('.click_expand').show();
	    $(this).parents('li').removeClass('expand')
	  })
	  
	  
   // 注册页面样式
    $('#username1').focus(function(){
       $(this).addClass('hover');
//       if($(this).val()=='用户名'){
//            $(this).attr('value',"")
//       }
    })
    $('#username1').blur(function(){
       $(this).removeClass('hover');
//       if($(this).val()==''){
//            $(this).attr('value',"用户名")
//       }
    })
    $('#password1').focus(function(){
        $(this).addClass('hover');
   }) 
   $('#password1').blur(function(){
        $(this).removeClass('hover');
   }) 
     $('#repassword').focus(function(){
        $(this).addClass('hover');
   }) 
   $('#repassword').blur(function(){
        $(this).removeClass('hover');
   }) 





  // 移入和移除文件按钮时的样式变化
  $('#file').hover(function(){
      $(this).siblings('a').addClass('filechoose_hover');
  },function(){
      $(this).siblings('a').removeClass('filechoose_hover');
  })
  // 点击文件选择按钮时下方显示选择的文件名字
  $('#file').change(function(){
      $('#filename').text('当前选择文件:'+$(this).val())
  })

  // 资料更改js
  $('#member_info .left li').click(function(){
    var cur=$(this).index();
    $(this).addClass('click');
    $(this).siblings('li').removeClass('click');
    // alert(cur)
    $(this).parents('div').siblings('div.right').hide()
    $("#moni_loading").show();
    setTimeout(function(){
    	 $("#moni_loading").hide();
    	 $("div.right").eq(cur).show().siblings('div.right').hide();
    },500)
  })
  
  
  //点击回复,出现回复框
  $('.reply').click(function(){
    var $reply_div_wrapper=$(this).parents('.comments_list').find('.reply_div_wrapper');
    var toWho=$(this).parent('div').siblings('a :first').text();
    $reply_div_wrapper.find('.toWho').text("回复:"+toWho);
    $reply_div_wrapper.show();
  })

  

})