package com.hnust.research.domain;

import java.io.Serializable;

/**
 * 所属类型（标签:前端界面,邮件,博客系统.....）
 * @author Administrator
 *
 */
public class Label implements Serializable{
	//id
	private Integer id;
	//种类
	private String type;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public Label(String type) {
		this.type = type;
	}
	public Label() {
		super();
	}
	
}
