package com.hnust.research.domain;

import java.util.Date;

/**
 * 回复类
 * @author Administrator
 *
 */
public class Comment {
	private Long id; //id
	private Long toId;  //回复那个种类的id
	private String comment;  //回复内容
	private Date datetime;  //回复时间
	private String flag;	//回复的是什么
	private User user;	//对应的用户
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getToId() {
		return toId;
	}
	public void setToId(Long toId) {
		this.toId = toId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getDatetime() {
		return datetime;
	}
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
