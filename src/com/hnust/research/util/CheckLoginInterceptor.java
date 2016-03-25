package com.hnust.research.util;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.hnust.research.domain.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@Component
@Scope("prototype")
public class CheckLoginInterceptor extends AbstractInterceptor{

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		System.out.println("检查用户是否登录的拦截器执行");
		return invocation.invoke();
	}
	
}
