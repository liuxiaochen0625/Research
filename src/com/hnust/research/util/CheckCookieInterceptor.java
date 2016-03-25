package com.hnust.research.util;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.hnust.research.domain.User;
import com.hnust.research.service.UserService;
import com.hnust.research.serviceImpl.UserServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 检查cookie的拦截器
 * @author Administrator
 *
 */
@Component
@Scope("prototype")
public class CheckCookieInterceptor extends AbstractInterceptor {

	@Resource
	private UserService userService;
	@Resource
	private CookieUtils cookieUtils;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		/**
		 * 下面四行代码测试用
		 */
		System.out.println("用户cookie拦截器执行");
		/*System.out.println("---------> 之前");
		String result = invocation.invoke(); // 放行
		System.out.println("---------> 之后");
		return result;*/
//		System.out.println("cookie检查开始");
		
		//1,查看是否有用户登录的session
		User user=(User) ActionContext.getContext().getSession().get("user");
		if(user!=null){//如果不为空,就放行
			//此时要从数据库中取出用户，然后设置新session
			user=userService.getById(user.getId());
			ActionContext.getContext().getSession().put("user", user);
			return invocation.invoke();
		}else{//如果为空,那么就检查用户是否设置了cookie
			if(cookieUtils.getCookie(ServletActionContext.getRequest(), userService)){
				//cookie存在，并且已经把用户放入到session中去了
				return invocation.invoke();
			}else{//如果cookie不存在并且没有session，那么就只能继续放行了
				return invocation.invoke();
			}
		}
	}
}
