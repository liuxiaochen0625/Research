package com.hnust.research.util;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.hnust.research.domain.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 这个拦截器是在checkCookieInterceptor拦截器之后执行
 * 占时先不用这个拦截器
 * @author Administrator
 *
 */
@Component
@Scope("prototype")
public class CheckPrivilegeInterceptor extends AbstractInterceptor{

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		/* System.out.println("url检查---------> 之前");
		 String result = invocation.invoke(); // 放行
		 System.out.println("url检查---------> 之后");
		 return result;*/
		System.out.println("检查url开始");
		//获取信息
		String namespace=invocation.getProxy().getNamespace();
		String actionName=invocation.getProxy().getActionName();
		String privUlr=namespace+actionName;//对应的URL
		System.out.println(privUlr);

		User user=(User) ActionContext.getContext().getSession().get("user");
		if(user==null){//说明既没有cookie也没有session
			//判断url的值，如果是去登陆的话，就放行
			if(privUlr.startsWith("/user_loginUI")){
				return invocation.invoke();
			}else{//如果是其他的值,就返回到登陆界面，因为此时user为空
				return "tologinUI";//跳转到登陆界面
			}
		}else{//用户不为空,有权限
			return invocation.invoke();
		}		
		
	}

}
