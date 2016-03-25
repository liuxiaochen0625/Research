package com.hnust.research.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 实现Serializable接口,当重启tomcat的时候,如果用户是登录了的，那么该用户不会退出session
 * 一个用户有多个资源，一用户对资源，一对多的关系
 * @author Administrator
 *
 */
public class User implements Serializable{
	/**
	 * id
	 * 用户名
	 * 密码
	 * 分享次数
	 * 创建时间
	 * 头像路径
	 * 用户简介
	 * 昵称
	 * 性别
	 * 是否激活
	 * 注册邮箱
	 * 激活码
	 */
	private Long id;
	private String username;
	private String password;
	private Integer sharecount;
	private Integer softcount;
	private Date createdate;
	private String imgpath;
	private String description;
	private String nickname;
	private Integer sex;
	private Integer active;
	private String mail;
	private String activeCode;
	
	/**
	 * 一个用户有多个资源，一对多关系
	 */
	private Set<Source> sources=new HashSet<Source>();
	/**
	 * 一个用户有多个作品,一对多关系
	 */
	private Set<Soft> softs=new HashSet<Soft>();
	
	/**
	 * 一个用户有多个回复，一对多关系
	 */
	private Set<Comment> comments=new HashSet<Comment>();
	
	/**
	 * 其他标识字段
	 */
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getSharecount() {
		return sharecount;
	}
	public void setSharecount(Integer sharecount) {
		this.sharecount = sharecount;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public String getImgpath() {
		return imgpath;
	}
	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Integer getActive() {
		return active;
	}
	public void setActive(Integer active) {
		this.active = active;
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
	public Set<Source> getSources() {
		return sources;
	}
	public void setSources(Set<Source> sources) {
		this.sources = sources;
	}
	public Set<Soft> getSofts() {
		return softs;
	}
	public void setSofts(Set<Soft> softs) {
		this.softs = softs;
	}
	public Integer getSoftcount() {
		return softcount;
	}
	public void setSoftcount(Integer softcount) {
		this.softcount = softcount;
	}
	public Set<Comment> getComments() {
		return comments;
	}
	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}
	
}
