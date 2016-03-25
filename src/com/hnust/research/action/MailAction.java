package com.hnust.research.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


import com.hnust.research.domain.User;
import com.hnust.research.service.UserService;
import com.hnust.research.util.SendMailUtil;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 邮件处理action
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
public class MailAction extends ActionSupport implements ServletRequestAware{
	@Resource
	private UserService userService;
	@Resource
	private SendMailUtil sendMailUtil;
	//返回给用户的mail是否存在的信息
	private String result;
	private String mail;
	private String activeCode;
	private HttpServletRequest request;
	/**
	 * 检验邮箱是否被注册
	 * @return
	 * @throws Exception
	 */
	public String exits() throws Exception{
		System.out.println(mail);
		if(userService.exitsMail(mail)){//存在
			result="{'exits':'yes'}";
		}else{
			result="{'exits':'no'}";
		}
		return "exits";
	}
	/**
	 * 重发邮件
	 * @return
	 * @throws Exception
	 */
	public String resend() throws Exception{
		String path = request.getContextPath(); 
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
		String url=basePath+"/user_active.action?activeCode="+activeCode;
//		System.out.println(url);
		sendMailUtil.sendMail(mail,url);
		return "resend";
	}
	
	public String sendPassMail() throws Exception{
		User user=userService.findByMail(mail);
		if(user==null){
			return "error";
		}else{
			String nickname=user.getNickname();
			String pass="";
			String str="abcdefghijklmnopqrstuvwxyz";
			for(int i=0;i<6;i++){
				pass=pass+str.charAt((int)(Math.random() * 26));
			}
			try {
				sendMailUtil.sendMail1(mail, pass, nickname);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			result="{'updatePass':'success'}";
			pass=DigestUtils.md5Hex(pass);
			user.setPassword(pass);
			userService.update(user);
			return "sendPassMail";
		}
	}
	/**
	 * 下面是get和set方法
	 */
	public String getResult() {
		return result;
	}
	
	public void setResult(String result) {
		this.result = result;
	}
	
	public String getMail() {
		return mail;
	}
	
	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getActiveCode() {
		return activeCode;
	}

	public void setActiveCode(String activeCode) {
		this.activeCode = activeCode;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}

}
