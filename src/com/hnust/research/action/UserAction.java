package com.hnust.research.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hnust.research.base.BaseAction;
import com.hnust.research.domain.Soft;
import com.hnust.research.domain.Source;
import com.hnust.research.domain.User;
import com.hnust.research.util.CookieUtils;
import com.hnust.research.util.QueryHelper;
import com.hnust.research.util.SendMailUtil;
import com.hnust.research.util.UploadFileUtil;
import com.opensymphony.xwork2.ActionContext;

/**
 * 实现了SessionAware,和ServletResponseAware接口，有利于对session和response进行操作
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> implements SessionAware,ServletResponseAware,ServletRequestAware{
	//用户是否存在的结果
	private String result;
	//Struts2中Map类型的session
	private Map<String, Object> session;
	//接收客户端传来的验证码
	private String checknumber;
	//ajax通过struts2返回给用户端的验证码情况json数据
	private String jsnumber;
	//ajax通过strtus2返回给用户端是否登录成功的json数据
	private String loginInfo;
	//ajax通过struts2返回给用户端是否更新用户信息成功的json数据
	private String updateInfo;
	//用来接收用户是否选中记住我的字段,ture表示选中，false表示没选中
	private boolean remmber;
	
	//response
	private HttpServletResponse response;
	//request
	private HttpServletRequest request;
	
	//用于判断是用来更新用户基本信息,还是用来更新用户头像。
	private String flag;
	
	//接受图片的file
	private File upload;
	//接受图片的名字
	private String uploadFileName;
	//项目的存放上传的资源的路径
	private final String PATH=ServletActionContext.getServletContext().getRealPath("/images/source/");
	//接受新密码的字符串
	private String newpassword;
	@Resource
	private CookieUtils cookieUtils;
	
	@Resource
	private UploadFileUtil uploadFileUtil;
	
	@Resource
	private SendMailUtil sendMailUtil;
	
	/**
	 * 检查用户是否登录
	 */
	public String isLogin() throws Exception{
		User user=(User) ActionContext.getContext().getSession().get("user");
		if(user!=null){
			result="{'login':'true'}";
		}else{
			result="{'login':'false'}";
		}
		return "isLogin";
	}
	

	/**
	 *检查用户名是否存 
	 */
	public String exits() throws Exception{
		/*result="{'exits':'true'}";    //转化成json数据,这里是测试用的*/	
		/**
		 * result="true",表示存在此用户。
		 * result="false",表示不存在此用户。
		 */
		User user=userService.findByUserName(model.getUsername());

		if(user!=null){ //用户存在
			result="{'exits':'true'}";
			System.out.println("传过来的用户名="+user.getUsername());
		}else{
			result="{'exits':'false'}";
		}
		System.out.println(result);
		return "exits";
	}



	/**
	 * 添加用户操作
	 */
	public String add() throws Exception{
		if(model.getUsername()!=null&&model.getPassword()!=null){
			/**
			 * 为了防止有人通过直接在url地址中传值注册相同名字的用户名和密码,
			 */
			User user=userService.findByUserName(model.getUsername());
			if(user==null){//确定数据库中没有这样的用户存在
				//设置nickname为默认
				model.setNickname(model.getUsername());
				//获取当前时间
				model.setCreatedate(new Date());
				//设置分享次数为0
				model.setSharecount(0);
				//设置作品数位0
				model.setSoftcount(0);
				//取出注册密码,把密码使用md5算法加密
				String md5Digest=DigestUtils.md5Hex(model.getPassword());
				//设置成为md5密码
				model.setPassword(md5Digest);
				//随机生成一个acticeCode
				Long time=new Date().getTime();
				Random random=new Random();
				int number=random.nextInt(1000);
				String activeCode=Long.toString(time)+Integer.toString(number);
				model.setActiveCode(activeCode);
				//发送邮件至用户
				//构造激活地址
				String path = request.getContextPath(); 
				String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
				String url=basePath+"/user_active.action?activeCode="+activeCode;
//				System.out.println(url);
				sendMailUtil.sendMail(model.getMail(),url);
				//头像地址默认
				model.setImgpath("/Research/images/source/default.jpg");
				//保存用户,此时用户并没有激活
				userService.save(model);
				//放到struts的值栈中去。
				ActionContext.getContext().put("user", model);
				//返回到激活界面
				return "confirm";
			}else{//如果存在这样的用户,则返回到添加界面
				return "toaddUI";
			}
		}
		return "toaddUI";
	}

	public String addUI() throws Exception{
		return "addUI";
	}

	/**
	 * 检查验证码是否正确
	 */
	public String checkNumberOk() throws Exception{
		String serverCode=(String) session.get("SESSION_SECURITY_CODE");
		if(serverCode.equals(checknumber)){
			jsnumber="{'number':'true'}"; 
		}else{
			jsnumber="{'number':'false'}";
		}
		return "checkNumberOk";
	}


	/**
	 * 登录界面
	 */
	public String loginUI() throws Exception{
		return "loginUI";
	}

	/**
	 * 检验登录是否成功
	 */
	public String loginStatus() throws Exception{
		if(null!=model.getUsername()&&null!=model.getPassword()){//防止恶意访问action
			//设置成md5密码
			String md5Digest=DigestUtils.md5Hex(model.getPassword());
			model.setPassword(md5Digest);
			User user=userService.findByUserNameAndPassword(model.getUsername().trim(),model.getPassword());
			if(user==null){//登录失败
				System.out.println("失败");
				loginInfo="{'status':'failure'}";
			}else{//用户名和密码正确,但是未激活
				if(user.getActive()==0){
					loginInfo="{'status':'success','active':'0','mail':'"+user.getMail()+"'}";
					System.out.println(loginInfo);
				}else{//密码用户名正确，而且已经激活
					loginInfo="{'status':'success','active':'1'}";
					System.out.println(loginInfo);
				}
			}
			return "loginStatus";
		}
		//返回到loginUI界面
		return "tologinUI";
	}

	/**
	 * 登录操作
	 */
	public String login() throws Exception{
		/**
		 * 要通过用户名和密码获得完整的用户
		 */
		
		//先要判断model中的用户名是否为空
		if(model.getUsername()!=null&&model.getPassword()!=null){
			String md5Digest=DigestUtils.md5Hex(model.getPassword());
			User user=userService.findByUserNameAndPassword(model.getUsername(), md5Digest);
			System.out.println(user.getUsername());
			System.out.println(user.getPassword());
			//判断用户是否激活
			if(user.getActive()==0){//未激活,返回系统忙界面
				return "error";
			}else{//激活了,正常登录
				//判断是否要添加到cookie中去
				if(remmber){
					//System.out.println("添加到cookie中去了");
					Cookie cookie=cookieUtils.addCookie(user);//添加cookie
					response.addCookie(cookie);  //将cookie添加到response中去
				}
				//在session中设值
				ActionContext.getContext().getSession().put("user", user);	
				//跳到主页面
				return "toIndex";
			}
		}
		return "tologinUI";
	}

	/**
	 * 退出登录
	 */
	public String logout() throws Exception{
		//退出的时候,记得把cookie里面的值给清空
		Cookie cookie=cookieUtils.delCookie(request);
		if(cookie!=null){
			response.addCookie(cookie);//此时这个cookie为空值了
		}
		//移除session中的user
		ActionContext.getContext().getSession().remove("user");
		//存在一个问题,就是当你在退出登录之后，你在哪个界面按的退出登录，那么就返回到那个登陆界面，这个在struts2.xml里卖弄怎么配置？
		return "logout";
	}
	
	/**
	 * 更新界面action
	 */
	public String updateUI() throws Exception{
		//首先要判断是否登录,没有登录的话就返回到登陆界面
		if(ActionContext.getContext().getSession().get("user")!=null){
			return "updateUI";
		}
		return "tologinUI";
	}

	/**
	 *更新action,前台采用ajax方法更新,点击更新按钮,不刷新整个页面。直接调用这个方法。
	 */
	public String update() throws Exception{
		//首先判断用户是否登录了,没有登录就返回到登录界面
		if(ActionContext.getContext().getSession().get("user")!=null){//有权限
			if(flag.equals("update_info")){//更新基本信息
				//更新之前查找昵称是否存在
				if(userService.findByNickName(model.getNickname().trim())){//昵称存在
					//如果昵称存在,但是是用户自己更新当前名称,还是能更新的
					if(userService.getById(model.getId()).getNickname().equals(model.getNickname())){
						updateInfo="{'updateInfo':'success'}";
						User user=userService.getById(model.getId());
						//设置要更新的属性
						user.setNickname(model.getNickname());
						user.setSex(model.getSex());
						user.setDescription(model.getDescription().trim());
						//更新
						userService.update(user);
						//重新设置session
						ActionContext.getContext().getSession().put("user", user);
					}else{
						updateInfo="{'updateInfo':'failure'}";
					}
				}else{//昵称不存在,可以更新
					//通过Id获得用户
					User user=userService.getById(model.getId());
					//设置要更新的属性
					user.setNickname(model.getNickname());
					user.setSex(model.getSex());
					user.setDescription(model.getDescription().trim());
					//更新
					userService.update(user);
					//重新设置session
					ActionContext.getContext().getSession().put("user", user);
					//返回更新成功信息
					updateInfo="{'updateInfo':'success'}";
				}
				System.out.println(updateInfo);
				return "update";
			}else{
				if(flag.equals("update_img")){//更新头像,涉及到图片的上传
					if(upload!=null){//说明不为空,而且文件大小没有超
						uploadFileUtil.uploadImg(upload, uploadFileName, PATH);
						updateInfo="{'updateInfo':'success'}";//更新成功
//						System.out.println("更新头像");
						return "updateImg";
					}else{//由于文件超过大小之后必须要返回input，具体去查struts2的帮助文档吧
						System.out.println("文件上传失败");
						updateInfo="{'updateInfo':'failure'}";//更新失败
						return "input";
					}
				}else{//更新密码的
					if(flag.equals("update_password")){
						//判断旧密码是否正确
//						System.out.println(userService.findPasswordById(model.getId()));
						String str1=DigestUtils.md5Hex(model.getPassword());//获得密码之后进行MD5加密
						if(str1.equals(userService.findPasswordById(model.getId()))){//输入的密码相等
							String str2=DigestUtils.md5Hex(newpassword);
							User user=userService.getById(model.getId());
							user.setPassword(str2);
							userService.update(user);
							//更新用户之后，记得更新一下cookie和session,因为这是更新密码，所以要设计cookie自动验证登录的，所以要更新
							ActionContext.getContext().getSession().put("user", user);
							//这时再重新加cookie
							Cookie cookie=cookieUtils.addCookie(user);
							response.addCookie(cookie);  //将cookie添加到response中去
							updateInfo="{'updateInfo':'success'}";//更新成功
						}else{//更新失败
							updateInfo="{'updateInfo':'failure'}";//更新失败
						}
						return "update";
					}
				}
			}
		}
		return "loginUI";
	}
	
	/**
	 * 查看个人中心的action
	 */
	public String personalInfo() throws Exception{
		return "personalInfo";
	}
	
	/**
	 * 用户激活的action
	 */
	public String active() throws Exception{
		User user=userService.findByActiveCode(model.getActiveCode());
		//如果user为空,就返回到错误界面,并且提示系统繁忙
		if(user==null){
			return "error";
		}else{
			if(user.getActive()==1){
				ActionContext.getContext().getSession().put("user", user);
				return "toIndex";
			}else{
				user.setActive(1);
				userService.update(user);
				ActionContext.getContext().getSession().put("user", user);
				return "active";
			}
		}
	}
	
	/**
	 * 找回密码界面
	 */
	public String forget() throws Exception{
		return "forget";
	}
	
	
	//===================管理员方法=====================
	/**
	 * 列出用户
	 */
	public String list() throws Exception{
		QueryHelper queryHelper=new QueryHelper(User.class, "user");
		queryHelper.preparePageBean(userService, pageNum, pageSize);
		return "list";
	}
	
	/**
	 * 删除用户
	 */
	public String delete() throws Exception{
		//注意在配置文件中配置级联删除
		userService.delete(model.getId());
		return "toList";
	}
	
	/**
	 * 查看用户详情
	 */
	public String detail() throws Exception{
		User user=userService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(user);
		
		Set<Source> sourceList=new HashSet<Source>();
		Set<Soft> softList=new HashSet<Soft>();
		sourceList=user.getSources();
		softList=user.getSofts();
		
		ActionContext.getContext().put("sourceList", sourceList);
		ActionContext.getContext().put("softList", softList);
		return "detail";
	}
	
	
	/**
	 * 
	 * 下面的是get和set方法
	 */
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public String getChecknumber() {
		return checknumber;
	}

	public void setChecknumber(String checknumber) {
		this.checknumber = checknumber;
	}

	public String getJsnumber() {
		return jsnumber;
	}

	public void setJsnumber(String jsnumber) {
		this.jsnumber = jsnumber;
	}

	public String getLoginInfo() {
		return loginInfo;
	}

	public void setLoginInfo(String loginInfo) {
		this.loginInfo = loginInfo;
	}

	public boolean isRemmber() {
		return remmber;
	}

	public void setRemmber(boolean remmber) {
		this.remmber = remmber;
		//System.out.println("设置记住我："+remmber);
	}
	
	//设置response的值
	public void setServletResponse(HttpServletResponse response) {
		this.response=response;
	}

	//设置request的值
	public void setServletRequest(HttpServletRequest request) {
		//System.out.println("Action里面调用了setServletRequest()--------------->");
		this.request=request;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getUpdateInfo() {
		return updateInfo;
	}

	public void setUpdateInfo(String updateInfo) {
		this.updateInfo = updateInfo;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
		System.out.println(uploadFileName);
	}

	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}
	
}
