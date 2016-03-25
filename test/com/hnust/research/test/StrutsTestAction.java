package com.hnust.research.test;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

@Component
@Scope("prototype")
public class StrutsTestAction extends ActionSupport{
	public String execute(){
		return "success";
	}
}
