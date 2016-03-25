package com.hnust.research.domain;

import java.math.BigDecimal;

public class Score {
	private Long id;
	private Integer level1;
	private Integer level2;
	private Integer level3;
	private Integer level4;
	private Integer level5;
	private String noteIds;
	
	private Float percent1;
	private Float percent2;
	private Float percent3;
	private Float percent4;
	private Float percent5;
	
	private Float scores;
	
	
	public Score(Integer level1,Integer level2,Integer level3,Integer level4,Integer level5,String noteIds){
		this.level1=level1;
		this.level2=level2;
		this.level3=level3;
		this.level4=level4;
		this.level5=level5;
		this.noteIds=noteIds;	
	}
	
	public Score() {
	}
	
	public void  setPercents(Integer level1,Integer level2,Integer level3,Integer level4,Integer level5){
		Integer total=level1+level2+level3+level4+level5;
		if(total==0){
			this.percent1=(float) 0;
			this.percent2=(float) 0;
			this.percent3=(float) 0;
			this.percent4=(float) 0;
			this.percent5=(float) 0;
		}else{
			float a1=(float)level1*100/total;
			float a2=(float)level2*100/total;
			float a3=(float)level3*100/total;
			float a4=(float)level4*100/total;
			float a5=(float)level5*100/total;
			
			BigDecimal b1=new BigDecimal(a1);
			this.percent1=b1.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
			
			BigDecimal b2=new BigDecimal(a2);
			this.percent2=b2.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
			
			BigDecimal b3=new BigDecimal(a3);
			this.percent3=b3.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
			
			BigDecimal b4=new BigDecimal(a4);
			this.percent4=b4.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
			
			BigDecimal b5=new BigDecimal(a5);
			this.percent5=b5.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
			
		}
	}
	
	public void setScores(Integer level1,Integer level2,Integer level3,Integer level4,Integer level5 ){
		Integer total=level1+level2+level3+level4+level5;
		if(total==0){
			this.scores=(float) 0;
		}else{
			this.scores=(float) (level1+2*level2+3*level3+4*level4+5*level5)/total; 
		}
	}
	
	public boolean existsId(Long id){
		String[] ids=noteIds.split(",");
		for(int i=0;i<ids.length;i++){
			if(Float.parseFloat(ids[i])==id){
				return true;
			}
		}
		return false;
	}
	
	public void addNoteIds(Long id){
		if(this.noteIds==null){
			this.noteIds=id.toString();
		}else{
			this.noteIds=this.noteIds+","+id.toString();
		}
	}

	public Integer getLevel1() {
		return level1;
	}

	public void setLevel1(Integer level1) {
		this.level1 = level1;
	}

	public Integer getLevel2() {
		return level2;
	}

	public void setLevel2(Integer level2) {
		this.level2 = level2;
	}

	public Integer getLevel3() {
		return level3;
	}

	public void setLevel3(Integer level3) {
		this.level3 = level3;
	}

	public Integer getLevel4() {
		return level4;
	}

	public void setLevel4(Integer level4) {
		this.level4 = level4;
	}

	public Integer getLevel5() {
		return level5;
	}

	public void setLevel5(Integer level5) {
		this.level5 = level5;
	}

	public Float getPercent1() {
		return percent1;
	}

	public void setPercent1(Float percent1) {
		this.percent1 = percent1;
	}

	public Float getPercent2() {
		return percent2;
	}

	public void setPercent2(Float percent2) {
		this.percent2 = percent2;
	}

	public Float getPercent3() {
		return percent3;
	}

	public void setPercent3(Float percent3) {
		this.percent3 = percent3;
	}

	public Float getPercent4() {
		return percent4;
	}

	public void setPercent4(Float percent4) {
		this.percent4 = percent4;
	}

	public Float getPercent5() {
		return percent5;
	}

	public void setPercent5(Float percent5) {
		this.percent5 = percent5;
	}

	public Float getScores() {
		return scores;
	}

	public void setScores(Float scores) {
		this.scores = scores;
	}

	public String getNoteIds() {
		return noteIds;
	}

	public void setNoteIds(String noteIds) {
		this.noteIds = noteIds;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
