package com.hnust.research.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hnust.research.base.BaseAction;
import com.hnust.research.domain.Competition;
import com.hnust.research.domain.Soft;
import com.hnust.research.util.QueryHelper;
import com.opensymphony.xwork2.ActionContext;

/**
 * 竞赛action
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
public class CompetitionAction extends BaseAction<Competition>{
	
	private String result;
	private	long userId;
	
	public String existsUser() throws Exception{
		Competition competition=competitionService.getById(model.getId());
		result="{'exists':'false'}";
		if(competition!=null){
			String ids[]=competition.getNoteIds().split(",");
			for(int i=0;i<ids.length;i++){
				if(userId==Long.parseLong(ids[i])){
					result="{'exists':'true'}";
				}
			}
		}else{
			return "input";
		}
		return "existsUser";
	}
	
	public String list() throws Exception{
		return listStart();
	}
	
	/**
	 * 还在进行的竞赛列表
	 * @return
	 * @throws Exception
	 */
	public String listStart() throws Exception{
		//获取分页数据
		QueryHelper queryHelper=new QueryHelper(Competition.class, "competition");
		queryHelper.addCondition("competition.status=?", "正在进行")
		.preparePageBean(competitionService, pageNum, pageSize);
		return "listStart";
	}
	
	/**
	 * 已经结束的竞赛
	 * @return
	 * @throws Exception
	 */
	public String listEnd() throws Exception{
		//获取分页数据
		QueryHelper queryHelper=new QueryHelper(Competition.class, "competition");
		queryHelper.addCondition("competition.status=?", "已经结束")
		.preparePageBean(competitionService, pageNum, pageSize);
		return "listEnd";
	}
	
	/**
	 * 单个竞赛详情
	 * @return
	 * @throws Exception
	 */
	public String detail() throws Exception{
		Competition competition=competitionService.getById(model.getId());
		if(competition!=null){
			ActionContext.getContext().getValueStack().push(competition);
			//准备这个竞赛的作品数目
			QueryHelper queryHelper=new QueryHelper(Soft.class, "soft");
			queryHelper.addCondition("soft.competition=?", competition)
						.addOrderProperty("soft.score.scores","1")
						.preparePageBean(softService, pageNum, pageSize);
		}else{
			return "input";
		}
		
		return "detail";
	}
	
	
	/**
	 * 参加界面
	 * @return
	 * @throws Exception
	 */
	public String attendUI() throws Exception{
		List<Competition> competitionList=new ArrayList<Competition>();
		competitionList=competitionService.findAll();
		Competition competition=competitionService.getById(model.getId());
		if(competition!=null){
			ActionContext.getContext().getValueStack().push(competition);
		}
		ActionContext.getContext().put("competitionList", competitionList);
		return "attendUI";
	}
	
	/**
	 * 参加竞赛
	 * @return
	 * @throws Exception
	 */
	public String attend() throws Exception{
		
		return "attend";
	}
	
	
	//============================以下为管理员具有的操作=======================
	/**
	 * 管理员发布竞赛界面,这个action只适合管理人员,其他人操作的话会报404
	 */
	public String createUI() throws Exception{
		return "saveUI";
	}
	
	/**
	 * 编辑界面
	 */
	public String editUI() throws Exception{
		if(model.getId()!=null){
			Competition competition=competitionService.getById(model.getId());
			ActionContext.getContext().getValueStack().push(competition);
		}
		return "saveUI";
	}
	
	/**
	 * 发布竞赛操作,返回到list界面
	 */
	 public String create() throws Exception{
		 //1,为model封装好值
		 model.setAuthor("管理员");
		 model.setStatus("正在进行");
		 model.setStart(new Date());
		 model.setCount(0);
		 //2,保存到数据库
		 competitionService.save(model);
		 return "toListStart";
	 }
	 
	 /**
	  * 编辑竞赛
	  */
	 public String edit() throws Exception{
		 //1,从数据库中取得数据
		 Competition competition=competitionService.getById(model.getId());
		 //2,设置新改的值
		 competition.setDescription(model.getDescription());
		 competition.setTitle(model.getTitle());
		 competition.setEnd(model.getEnd());
		 //3,保存到数据库中
		 competitionService.update(competition);
		 return "toListStart";
	 }
	 
	 /**
	  * 删除竞赛
	  */
	 public String delete() throws Exception{
		 competitionService.delete(model.getId());
		 return "toListStart";
	 }

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	

}
