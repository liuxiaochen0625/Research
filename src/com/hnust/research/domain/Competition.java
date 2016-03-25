package com.hnust.research.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 竞赛类
 * @author Administrator
 *
 */
public class Competition {
	private Long id;  //id
	private String title;   //竞赛名字
	private String description;		//竞赛描述
	private String status;	//状态
	private String author;		//发起人

	private Date start;		//开始时间
	private Date end;		//结束时间
	
	private String noteIds;   //记录参加人的id的字段("1,3,5,6,7,8")，表示id为1,3,6,7,8的用户参加过这个比赛
	private Integer count;   //有多少个作品记录
	
	private  Set<Soft> softs=new HashSet<Soft>();	//一个竞赛对应多个作品
	
	/**
	 * 增加用户id记录的方法
	 * @return
	 */
	public void addNoteId(Long id) {
		if(noteIds!=null){
			noteIds=noteIds+","+id;
		}else{
			noteIds=id.toString();
		}
	}
	

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public Set<Soft> getSofts() {
		return softs;
	}

	public void setSofts(Set<Soft> softs) {
		this.softs = softs;
	}

	public String getNoteIds() {
		return noteIds;
	}

	public void setNoteIds(String noteIds) {
		this.noteIds = noteIds;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	
}
