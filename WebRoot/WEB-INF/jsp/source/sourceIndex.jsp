<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>开源平台主页</title>
<link rel="icon" href="${pageContext.request.contextPath}/images/source/title.png" type="image/x-icon" />
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/source/title.png" type="image/x-icon" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/source.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/sourceIndex.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/js.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/js_sourceIndexPage.js"></script>
	<script language="javascript" src="${pageContext.request.contextPath}/plugins/ckeditor/ckeditor.js" charset="utf-8"></script>
	<script type="text/javascript">
		CKEDITOR.editorConfig = function( config )
		{
			config.language = 'zh-cn'; //配置语言    
			config.width = 660; //宽度    
			config.height = 80; //高度    
			config.skin = 'v2'; //编辑器皮肤样式
			config.resize_enabled = false;  //取消拖拽改变大小
			//自定义工具栏
			config.toolbar =
			    [
			       ['Smiley', 'SpecialChar','TextColor', 'BGColor']
			    ];      
			//自定义表情的使用
			config.smiley_path='plugins/ckeditor/plugins/smiley/images/qq/';
			config.smiley_images = ['1.gif', '2.gif', '3.gif', '4.gif', '5.gif', '6.gif', '7.gif', '8.gif', '9.gif', '10.gif', '11.gif',
			                        '12.gif', '13.gif', '14.gif', '15.gif','16.gif','17.gif','18.gif','19.gif','20.gif','21.gif','22.gif',
			                        '23.gif','24.gif','25.gif','26.gif','27.gif','28.gif','29.gif','30.gif','31.gif','32.gif','33.gif','34.gif',
			                        '35.gif','36.gif','37.gif','38.gif','39.gif','40.gif','41.gif','42.gif','43.gif','44.gif','45.gif','46.gif',
			                        '47.gif','48.gif','49.gif','50.gif','51.gif','52.gif','53.gif','54.gif','55.gif','56.gif','57.gif','58.gif',
			                        '59.gif','60.gif','61.gif','62.gif','63.gif','64.gif','65.gif','66.gif','67.gif','68.gif','69.gif','70.gif',
			                        '71.gif','72.gif','73.gif','74.gif','75.gif','76.gif','77.gif','78.gif','79.gif','80.gif','81.gif','82.gif',
			                        '83.gif','84.gif','85.gif','86.gif','87.gif','88.gif','89.gif','90.gif','91.gif','92.gif','93.gif','94.gif',
			                       '95.gif','96.gif','97.gif','98.gif','99.gif','100.gif'
			                        ];
			//图片的描述
			config.smiley_descriptions=['惊讶', '撇嘴', '色', '发呆', '得意', '害羞', '闭嘴', '睡', '大哭', '尴尬', '发怒',
			 	                        '调皮', '呲牙', '微笑', '难过','酷','抓狂','吐','偷笑','可爱','白眼','傲慢',
				                        '饥饿','困','惊恐','流汗','憨笑','大兵','奋斗','疑问','嘘...','晕','衰','骷髅',
				                        '敲打','拜拜','发抖','爱情','跳跳','猪头','抱抱','蛋糕','闪电','炸弹','刀','足球',
				                        '便便','咖啡','饭','玫瑰','凋谢','爱心','心碎','礼物','太阳','月亮','强','弱','握手',
				                        '飞吻','怄火','西瓜','冷汗','抠鼻','鼓掌','出糗','坏笑','左哼哼','右哼哼','哈欠','鄙视',
				                        '委屈','难过','阴险','亲亲','吓','可怜','菜刀','啤酒','篮球','乒乓','示爱','瓢虫','抱拳',
				                        '勾引','拳头','差劲','爱你','NO','OK','转圈','磕头','磕头','跳绳','挥手','激动','街舞',
				                        '献吻','左太极','右太极'
			                            ]
			
			//字体的定义
			config.font_names = '宋体/宋体;黑体/黑体;仿宋/仿宋_GB2312;楷体/楷体_GB2312;隶书/隶书;幼圆/幼圆;微软雅黑/微软雅黑;' + config.font_names;//這是基於默認字體的增加
			
			// 在 CKEditor 中集成 CKFinder注意 ckfinder 的路径选择要正确。   
			//config.filebrowserImageBrowseUrl = 'plugins/ckfinder/ckfinder.html?type=Images',//浏览图片的服务器的链接,对一般用户不用开放这个权限
			config.filebrowserImageUploadUrl = 'ck/ck_img.action',
			config.filebrowserWindowWidth = '1000',
			config.filebrowserWindowHeight = '700'
		};
	</script>
</head>
<body>

	<!-- 小滚动返回条 -->
	<div class="back" title="点击回到顶部"></div>
	<!-- 小滚动返回条结束 -->
	<!-- 头部 -->
	<div id="header_wrapper">
		<div id="header">
			<span class="logo">科大开源平台</span>
			<ul>
				<li><a href="index.action" class="selected">首页</a></li>
				<li><a href="source_list.action" >资源列表</a></li>
				<li><a href="source_uploadUI.action" >我要上传</a></li>
				<li><a href="competition_list.action"  >创新竞赛</a></li>
				<li><a href="competition_attendUI.action" >参加竞赛</a></li>
			</ul>
			<%@ include file="/WEB-INF/jsp/public/head.jspf" %>
		</div>
	</div>
	<!-- 头部结束 -->
	
	
	<!--传值表单  -->
	<s:form id="post_form" action="source_list.action">
	</s:form>
	<!--传值表单结束  -->
	
		<!-- 主页内容开始 -->
	<div id="main">
		<!-- 左边 -->
		<div class="main_left">
			<!-- 公告 -->
			<div class="div_title" flag="0">
				站内公告
			</div>
			<div class="notice">
				<ul>
					<s:iterator value="#noticeList">
						<li>
							<p>${content }</p>
							<p class="p_r"><span>${datetime }</span></p>
						</li>
					</s:iterator>
				</ul>
			</div>
			<!-- 公告结束 -->

			<!-- 最火资源 -->
			<div class="div_title" flag="0">
				最火资源Top10
			</div>
			<div class="mostdownload">
				<s:iterator value="#top10List" status="st">
					<div class="download_content">
						<em class="red_em">${st.index+1}</em>
						<a href="javascript:view(${id})" title="${title }">${title }</a>
					</div>
				</s:iterator>
				<div class="more">
					<a href="source_list.action?param3=DESC">更多</a>
				</div>
				
			</div>
			<!-- 最火资源结束 -->

			<!-- 结束竞赛 -->
			<div class="div_title" flag="0">
				最新竞赛
			</div>
			<div class="endCompetition">
				<s:iterator value="competitionList" status="st">
					<div class="end_list">
						<em class="orange_em">${st.index+1}</em>
						<a href="competition_detail.action?id=${id }" title="${title }">${title }</a>
					</div>
				</s:iterator>
				
				<div class="more">
					<a href="competition_list.action">更多</a>
				</div>
			</div>
			<!--结束竞赛结束  -->
		</div>
		<!-- 左边结束 -->

		<!-- 中间 -->
		<div class="main_middle" >
			<div class="div_title" flag="0">
				资源
			</div>
			<div class="top_source">
				<div class="type_wrapper">
					<div class="choose_area">
						<span class="span_hover">代码语言</span>
						<span class="not_hover">代码分类</span>
					</div>
					<ul style="display:block;">
						<s:iterator value="#languageList">
							<li>
								<a href="javascript:goLanguage('${language }')">${language }</a>	
							</li>
						</s:iterator>
					</ul>
					<script type="text/javascript">
					function goLanguage(language){
						$("#post_form").append("<input type='hidden' name='param1' value='"+language+"'></input>")
						$("#post_form").submit();						
					}
					</script>

					<ul>
						<s:iterator value="#labelList">
							<li>
								<a href="javascript:goLabel('${type }')">${type }</a>
							</li>
						</s:iterator>
					</ul>
					<script type="text/javascript">
					function goLabel(type){
						$("#post_form").append("<input type='hidden' name='param2' value='"+type+"'></input>")
						$("#post_form").submit();
					}
					</script>
				</div>
				<div class="latest_share">
					<span class="span_hover">最新分享</span>
					<ul class="index_source_list">
						<s:iterator value="latestShareList" status="st">
							<li>
								<em class="red_em">${st.index+1 }</em>
								<a href="javascript:view(${id })" title="${title }">${title }</a>
							</li>
						</s:iterator>
					</ul>
					<div class="more">
						<a href="source_list.action?param4=DESC">更多</a>
					</div>
				</div>
				<div class="recommend">
					<span class="span_hover">推荐下载</span>
					<ul class="index_source_list">
						<s:iterator value="recommendList" status="st">
							<li>
								<em class="red_em">${st.index+1 }</em>
								<a href="javascript:view(${id })" title="${title }">${title }</a>
							</li>
						</s:iterator>
					</ul>
					<div class="more">
						<a href="source_list.action">更多</a>
					</div>
				</div>
			</div>

			<div class="div_title" flag="0">
				竞赛
			</div>
			<div class="middle_competition">
				<div class="competition_top">
					为了促进各位同学对计算机的热情,本站开放竞赛模块,提供给大家一个竞争的平台,各式各样的竞赛,任你选择。参加竞赛之后,上传你的竞赛作品资源,一定要配上截图加以说明你的作品,最后我们会根据网友的投票分数来进行评奖。
				</div>
				<div class="current_competition">
					<span class="span_hover">当前竞赛</span>
					<ul class="index_competition_list">
						<s:iterator value="competitionList" status="st">
							<li class='<s:if test="%{#st.odd==true }">one</s:if><s:else>two</s:else>'>
								<table>
									<tr>
										<td colspan="4"><a href="competition_detail.action?id=${id }">${title }</a></td>
									</tr>
									<tr>
										<td>开始时间:${start }</td>
										<td>结束时间:${end }</td>
										<td><a href="competition_detail.action?id=${id }">查看详情</a></td>
									</tr>
								</table>
							</li>
						</s:iterator>
					</ul>
					<div class="more">
						<a href="competition_list.action">更多</a>
					</div>
				</div>
			</div>

			<div class="div_title" flag="0">
				留言
			</div>
			<div class="bottom_comment">
				<div class="bottom_comment_top">
					大家如果有什么意见或者想说的话,可以给我们留言,我们会改正自己的缺点。
				</div>
				<form action="">
					<textarea name="" id="leave_message" class="ckeditor"></textarea>
				</form>
				<input type="button" class="likebutton" value="提交">
				<div class="more">
					<a href="#">查看留言</a>
				</div>
			</div>
		</div>
		<!-- 中间结束 -->

		<!-- 右边区域 -->
		<div class="main_right">
			<div class="div_title" flag="0" >
				新入成员
			</div>
			<div class="new_member">
				<table>
					<s:iterator value="#latestUsersList">
						<tr>
							<td rowspan="2">
								<img src="${imgpath}" alt="无法加载" width="40" height="40"/>
							</td>
							<td>
								<a href="">${username }</a>
							</td>
						</tr>
						<tr>
							<td>${createdate}加入</td>
						</tr>
					</s:iterator>
				</table>
				<!-- <div class="more">
					<a href="">更多</a>
				</div> -->
			</div>

			<div class="div_title" flag="0">
				贡献最多
			</div>
			<div class="most_uploader">
				<table>
					<s:iterator value="#mostUploadUsersList">
						<tr>
							<td rowspan="2">
								<img src="${imgpath}" alt="无法加载" width="40" height="40"/>
							</td>
							<td>
								<a href="">${nickname }</a>
							</td>
						</tr>
						<tr>
							<td>${sharecount }次上传</td>
						</tr>
					</s:iterator>
				</table>

				<!-- <div class="more">
					<a href="">更多</a>
				</div> -->
			</div>		
		</div>
		<!-- 右边区域结束 -->
	</div>
	<!-- 主页内容结束 -->	
	
	<script type="text/javascript">
		function view(id){
			window.location.href="source_detail.action?id="+id;
		}
	</script>
	
	<!--底部版权开始11  -->
	<%@ include file="/WEB-INF/jsp/public/bottom.jspf" %>
	<!--底部版权结束  -->

</body>
</html>
