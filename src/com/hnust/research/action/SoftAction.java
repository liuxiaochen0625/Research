package com.hnust.research.action;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hnust.research.base.BaseAction;
import com.hnust.research.domain.Comment;
import com.hnust.research.domain.Competition;
import com.hnust.research.domain.Label;
import com.hnust.research.domain.Score;
import com.hnust.research.domain.Soft;
import com.hnust.research.domain.User;
import com.hnust.research.util.UploadFileUtil;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class SoftAction extends BaseAction<Soft>{
	
	private File file;
	private String fileFileName;
	private Long userId;
	private Long competitionId;
	private Integer level;
	private String PATH;
	/**
	 * 某个竞赛下作品列表
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception{
		return "list";
	}
	
	/**
	 * 作品详情
	 * @return
	 * @throws Exception
	 */
	public String detail() throws Exception{
		Soft soft=softService.getById(model.getId());
		if(soft==null){
			return "input";
		}else{
			soft.getScore().setPercents(soft.getScore().getLevel1(), soft.getScore().getLevel2(), soft.getScore().getLevel3(), soft.getScore().getLevel4(), soft.getScore().getLevel5());
			ActionContext.getContext().getValueStack().push(soft);
			
			List<Comment> commentList=new ArrayList<Comment>();
			commentList=commentService.getBy(soft);
			ActionContext.getContext().put("commentList", commentList);
			System.out.println("评论的个数="+commentList.size());
			return "detail";
		}
	}
	
	public String vote() throws Exception{
		User user=(User) ActionContext.getContext().getSession().get("user");
		if(user==null){
			return "toLoginUI";
		}
		if(level>5||level<0){
			return "input";
		}
		Soft soft=softService.getById(model.getId());
		if(soft==null){
			return "input";
		}else{
			Score score=soft.getScore();
			//判断该用户是否进行了投票
			if(soft.getScore().getNoteIds()!=null){
				String[] ids=soft.getScore().getNoteIds().split(",");
				for(int i=0;i<ids.length;i++){
					if(user.getId().toString().equals(ids[i])){
						return "input";
					}
				}
			}
			if(level==1){
				score.setLevel1(score.getLevel1()+1);
			}
			if(level==2){
				score.setLevel2(score.getLevel2()+1);
			}
			if(level==3){
				score.setLevel3(score.getLevel3()+1);			
			}
			if(level==4){
				score.setLevel4(score.getLevel4()+1);
			}
			if(level==5){
				score.setLevel5(score.getLevel5()+1);
			}
			score.setScores(score.getLevel1(), score.getLevel2(), score.getLevel3(), score.getLevel4(), score.getLevel5());
			score.addNoteIds(user.getId());
			scoreService.update(score);
		}
		return "toDetail";
	}
	
	/**
	 * 作品上传
	 * @return
	 * @throws Exception
	 */
	public String upload() throws Exception{
		if(file==null){
			return "input";
		}
		//从数据库中取出数据
		User user=(User) ActionContext.getContext().getSession().get("user");
		if(user==null){
			return "toLoginUI";
		}else{
			Competition competition=competitionService.getById(competitionId);
			if(competition==null||file==null){
				return "input";
			}
			/**
			 * 1.向t_soft插入数据
			 */
			PATH=ServletActionContext.getServletContext().getRealPath("\\soft")+"\\"+user.getUsername()+"\\"+fileFileName;
			model.setCreatedate(new Date());//设置创建时间
			model.setCompetition(competition);//设置所属竞赛
			model.setUser(user);//设置上传用户
			model.setPath(PATH);
			//先为这个作品生成一个score
			Score score=new Score(0,0,0,0,0,null);
			score.setScores((float) 0);
			scoreService.save(score);
			//再调用了hibernate的save方法之后，score对象变为持久态纳入session管理,就可以获取这个id了
			model.setScore(scoreService.getById(score.getId()));
			softService.save(model);
			
			/**
			 * 2并且做相应的更新
			 */
			//1所属竞赛的作品记录数+1
			competition.setCount(competition.getCount()+1);
			//2,竞赛的noteIds增加一个用户id记录
			competition.addNoteId(user.getId());
			//3,用户的作品数增加一个
			user.setSoftcount(user.getSoftcount()+1);
			userService.update(user);
			//在session中也要更新user
			ActionContext.getContext().getSession().put("user", user);
			
			/**
			 * 3.文件上传
			 */
			UploadFileUtil.uploadSoft(user, model, file, fileFileName);
		}
		
		return "toPersonInfo";
	}

	
	public String download() throws Exception{
		User user=(User) ActionContext.getContext().getSession().get("user");
		if(user!=null){
			Soft soft=softService.getById(model.getId());
			if(soft==null){
				return null;
			}else{
				this.fileFileName=soft.getPath().substring(soft.getPath().lastIndexOf("\\")+1);
				//解决乱码
				this.fileFileName=new String(this.fileFileName.getBytes("UTF-8"),"ISO-8859-1");
				PATH=soft.getPath();
			}
		}else{
			return "toLoginUI";
		}
		return "download";
	}
	
	
	/**
	 * inputStream属性的getter方法,
	 * StreamResult结果类型使用该属性来读取下载内容
	 * @return
	 * @throws Exception
	 */
	public InputStream getInputStream() throws Exception{
		String src=PATH.substring(PATH.indexOf("\\soft"));
		return ServletActionContext.getServletContext().getResourceAsStream(src);
	}
	
	//get和set方法
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getCompetitionId() {
		return competitionId;
	}

	public void setCompetitionId(Long competitionId) {
		this.competitionId = competitionId;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getPATH() {
		return PATH;
	}

	public void setPATH(String pATH) {
		PATH = pATH;
	}
	
}
