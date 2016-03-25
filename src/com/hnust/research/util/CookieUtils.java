package com.hnust.research.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.hnust.research.domain.User;
import com.hnust.research.service.UserService;
import com.opensymphony.xwork2.ActionContext;

/**
 * cookie的增加、删除、查询
 * @author Administrator
 *
 */
@Component      //交给spring管理
@Scope("prototype")   //多例
public class CookieUtils {
	//cookie的名字
	public static final String USER_COOKIE="user.cookie";
	
	/**
	 * 添加cookie方法
	 * @param user
	 * @return
	 */
	public Cookie addCookie(User user){
		Cookie cookie=new Cookie(USER_COOKIE,user.getUsername()+","+user.getPassword());
		System.out.println("添加了cookie");
		cookie.setMaxAge(60*60*24*14);  //cookie保存两周
		return cookie;
	}
	
	/**
	 * 得到cookie
	 * @param request
	 * @param userService
	 * @return
	 */
	public boolean getCookie(HttpServletRequest request,UserService userService){
		Cookie[] cookies=request.getCookies();
		if(cookies!=null){
			for(Cookie cookie:cookies){
				//System.out.println("cookie:"+cookie.getName());
				if(CookieUtils.USER_COOKIE.equals(cookie.getName())){
					String value=cookie.getValue();
					if(StringUtils.isNotBlank(value)){//user.cookie里的值不为空
						String[] split=value.split(",");
						String username=split[0];
						String password=split[1];
						User user=userService.findByUserNameAndPassword(username, password);
						if(user!=null){//找到用户
							ActionContext.getContext().getSession().put("user", user);
							System.out.println("用户不为空");
							return true;
						}
					}
				}
			}
		}
//		System.out.println("cookie 不存在");
		return false;//找不到用户
	}
	
	
	/**
	 * 删除cookie,也就是把cookie给清空了
	 * @param request
	 * @return
	 */
	public Cookie delCookie(HttpServletRequest request){
		Cookie[] cookies=request.getCookies();
		if(cookies!=null){
			for(Cookie cookie:cookies){
					if(USER_COOKIE.equals(cookie.getName())){
					cookie.setValue("");
					cookie.setMaxAge(0);
					return cookie;
				}
			}
		}
		return null;	
	}
}
