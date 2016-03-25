package com.hnust.research.action;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hnust.research.base.BaseAction;
import com.hnust.research.domain.Competition;
import com.hnust.research.domain.Notice;
import com.hnust.research.util.QueryHelper;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class NoticeAction extends BaseAction<Notice>{
	public String createUI() throws Exception{
		return "saveUI";
	}
	
	public String editUI() throws Exception{
		if(model.getId()!=null){
			Notice notice=noticeService.getById(model.getId());
			ActionContext.getContext().getValueStack().push(notice);
		}
		return "saveUI";
	}
	
	public String create() throws Exception{
		model.setDatetime(new Date());
		noticeService.save(model);
		return "toList";
	}
	
	public String edit() throws Exception{
		 //1,从数据库中取得数据
		 Notice notice=noticeService.getById(model.getId());
		 //2,设置新改的值
		 notice.setContent(model.getContent());
		 notice.setDatetime(new Date());
		 //3,保存到数据库中
		 noticeService.update(notice);
		 
		 return "toList";
	}
	
	
	public String delete() throws Exception{
		noticeService.delete(model.getId());
		return "toList";
	}
	
	public String list() throws Exception{
		QueryHelper queryHelper=new QueryHelper(Notice.class, "notice");
		queryHelper.preparePageBean(noticeService, pageNum, pageSize);
		return "list";
	}
}
