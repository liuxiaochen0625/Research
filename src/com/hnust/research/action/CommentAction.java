package com.hnust.research.action;

import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hnust.research.base.BaseAction;
import com.hnust.research.domain.Comment;
import com.hnust.research.domain.User;
import com.hnust.research.util.QueryHelper;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class CommentAction extends BaseAction<Comment>{
	private String result;
	public String comment() throws Exception{
		User user=(User) ActionContext.getContext().getSession().get("user");
		
		if(user!=null){
			model.setDatetime(new Date());
			model.setUser(user);
			//为了解决ajax传值乱码
			model.setComment(URLDecoder.decode(model.getComment(),"UTF-8"));
			commentService.save(model);
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			result="{'result':'yes','comment':'"+model.getComment()+"','img':'"+user.getImgpath()+"','time':'"+simpleDateFormat.format(model.getDatetime())+"','username':'"+user.getUsername()+"'}";
		}else{
			result="{'result':'no'}";
		}
		return "comment";
	}
	
	public String list() throws Exception{
		QueryHelper queryHelper=new QueryHelper(Comment.class, "comment");
		queryHelper.preparePageBean(commentService, pageNum, pageSize);
		return "list";
	}
	
	public String delete() throws Exception{
		commentService.delete(model.getId());
		return "toList";
	}
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
}
