package com.hnust.research.domain;

/**
 * 编程语言类（java，c++,c,js.....）
 * @author Administrator
 *
 */
public class ProgLanguage {
	//id
	private Integer id;
	//语言
	private String language;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	
	public ProgLanguage(String language) {
		this.language = language;
	}
	public ProgLanguage() {
		super();
	}
	
		
}
