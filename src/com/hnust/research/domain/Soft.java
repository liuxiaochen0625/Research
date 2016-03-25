package com.hnust.research.domain;

import java.util.Date;

/**
 * 参赛作品类
 * @author Administrator
 *
 */

public class Soft {
	private Long id;   //id
	private String title;   //作品名字
	private String description;	//作品描述
	private Date createdate;    //上传时间                 
	private String path; //上传路径
	
	/**
	 * 一个作品对应一个投票类（一一对应）
	 */
	private Score score;
	
	
	/**
	 * 一个作品,对应一个用户
	 */
	private User user;
	
	/**
	 * 一个作品对应一个竞赛
	 */
	private Competition competition;

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Competition getCompetition() {
		return competition;
	}

	public void setCompetition(Competition competition) {
		this.competition = competition;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public Score getScore() {
		return score;
	}

	public void setScore(Score score) {
		this.score = score;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	
	
}
