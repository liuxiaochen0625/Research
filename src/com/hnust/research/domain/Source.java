package com.hnust.research.domain;

import java.io.Serializable;
import java.util.Date;


/**
 * 本类与user是多对一的关系,在映射文件中要设置好,
 * 一个资源属于一个用户,一个用户有多个资源
 * 资源javabean
 * @author Administrator
 *
 */
public class Source implements Serializable{
	private Long id; //id
	private String title; //标题
	private String description;  //描述
 	private String path;	//路径
	private Integer count;	//下载次数
	private String type;	//类型
	private Date time;	//上传时间
	private Integer status; //资源状态
	private String language;	//属于哪门语言
	
	private User user; //属于哪个用户
	
	private Score score; //这个资源的得分

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Score getScore() {
		return score;
	}

	public void setScore(Score score) {
		this.score = score;
	}
	
	
}
