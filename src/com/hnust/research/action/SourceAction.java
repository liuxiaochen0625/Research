package com.hnust.research.action;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hnust.research.base.BaseAction;
import com.hnust.research.domain.Comment;
import com.hnust.research.domain.Label;
import com.hnust.research.domain.ProgLanguage;
import com.hnust.research.domain.Source;
import com.hnust.research.domain.User;
import com.hnust.research.util.QueryHelper;
import com.hnust.research.util.UploadFileUtil;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class SourceAction extends BaseAction<Source>{
	
	private Long userId;
	private File file;
	private String fileFileName;
	private String message;
	private String PATH;
	private String key;
	private String flag;
	
	/**
	 * 排序参数1,语言
	 */
	private String param1;
	/**
	 * 排序参数2,类型
	 */
	private String param2;
	/**
	 * 排序参数3,下载次数,升序：ASC,降序：DESC,不参与排序：其他值
	 */
	private String param3;
	/**
	 * 排序参数4,日期,升序：ASC,降序：DESC，不参与排序：其他值
	 */
	private String param4;
	
	@Resource
	private UploadFileUtil uploadFileUtil;
	
	/**
	 * 上传界面
	 * @return
	 * @throws Exception
	 */
	public String uploadUI() throws Exception{
		//准备语言和标签
		List<Label> labelList=new ArrayList<Label>();
		List<ProgLanguage> languageList=new ArrayList<ProgLanguage>();
		labelList=labelService.findAll();
		languageList=progLanguageService.findAll();
		//放到map中
		ActionContext.getContext().put("labelList", labelList);
		ActionContext.getContext().put("languageList", languageList);
		return "uploadUI";
	}
	
	/**
	 * 上传操作
	 * @return
	 * @throws Exception
	 */
	public String upload() throws Exception{
		User user=(User) ActionContext.getContext().getSession().get("user");
		if(user!=null){
			if(file!=null){
				uploadFileUtil.uploadSource(model, file, user, fileFileName);
			}
		}else{
			return "loginUI";
		}
		return "upload";
	}
	
	public String list() throws Exception{
		System.out.println(param1);
		//分页代码
		QueryHelper queryHelper=new QueryHelper(Source.class,"source");
		queryHelper.addCondition(param1!=null&&!param1.equals("all"), "source.language=?",param1)//按语言
					.addCondition(param2!=null&&!param2.equals("all"), "source.type=?", param2)//按类型
					.addCondition("source.status=?", 1)//审核通过的资源
					.addOrderProperty(param3!=null&&(param3.equals("ASC")||param3.equals("DESC")),"source.count", param3)//按下载量排序,如果满足前面的条件就添加排序语句
					.addOrderProperty(param4!=null&&(param4.equals("ASC")||param4.equals("DESC")),"source.time", param4)//按日期排序,如果满足最前面的条件就添加排序语句
					.preparePageBean(sourceService, pageNum, pageSize);//准备数据

		//准备语言和标签
		List<Label> labelList=new ArrayList<Label>();
		List<ProgLanguage> languageList=new ArrayList<ProgLanguage>();
		labelList=labelService.findAll();
		languageList=progLanguageService.findAll();
		//准备日期和下载次数的list
		Map<String, String> param3List=new HashMap<String, String>();
		Map<String, String> param4List=new HashMap<String, String>();
		param3List.put("DESC", "降序");param3List.put("ASC", "升序");
		param4List.put("DESC", "降序");param4List.put("ASC", "升序");
		//放到map中
		ActionContext.getContext().put("labelList", labelList);
		ActionContext.getContext().put("languageList", languageList);
		ActionContext.getContext().put("param3List", param3List);
		ActionContext.getContext().put("param4List", param4List);
		return "list";
	}
	
	/**
	 * 单个资源详细页面,通过sourceId
	 * @return
	 * @throws Exception
	 */
	public String detail() throws Exception{
		//查找数据
		Source source=sourceService.getById(model.getId());
		//准备数据到页面
		ActionContext.getContext().put("source",source);
		
		//评论
		List<Comment> commentList=new ArrayList<Comment>();
		commentList=commentService.getBy(source);
		ActionContext.getContext().put("commentList", commentList);
		System.out.println("评论的个数="+commentList.size());
		return "detail";
	}
	
	/**
	 * 文件下载,传一个文件名
	 * @return
	 * @throws Exception
	 */
	public String download() throws Exception{
		User user=(User) ActionContext.getContext().getSession().get("user");
		if(user!=null){
			Source source=sourceService.getById(model.getId());
			if(source==null){
				return null;
			}else{//都满足条件就下载吧
				/**
				 * 首先更新下载次数
				 */
				source.setCount(source.getCount()+1);
				sourceService.update(source);
				this.fileFileName=source.getPath().substring(source.getPath().lastIndexOf("\\")+1);
				//解决乱码
				this.fileFileName=new String(this.fileFileName.getBytes("UTF-8"),"ISO-8859-1");
				PATH=source.getPath();
			}
		}else{
			return "loginUI";
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
		String src=PATH.substring(PATH.indexOf("\\resource"));
		return ServletActionContext.getServletContext().getResourceAsStream(src);
	}
	
	
	public String search() throws Exception{
		System.out.println(key);
		QueryHelper queryHelper=new QueryHelper(Source.class, "source");
		queryHelper.addCondition(key!=null,"source.title like '%"+key+"%' AND source.status=1 or source.language like '%"+key+"%' AND source.status=1 or source.type like '%"+key+"%' AND source.status=1")
					.addOrderProperty(param3!=null&&(param3.equals("ASC")||param3.equals("DESC")),"source.count", param3)//按下载量排序,如果满足前面的条件就添加排序语句
					.addOrderProperty(param4!=null&&(param4.equals("ASC")||param4.equals("DESC")),"source.time", param4)//按日期排序,如果满足最前面的条件就添加排序语句
					.preparePageBean(sourceService, pageNum, pageSize);
		
		//准备日期和下载次数的list
		Map<String, String> param3List=new HashMap<String, String>();
		Map<String, String> param4List=new HashMap<String, String>();
		param3List.put("DESC", "降序");param3List.put("ASC", "升序");
		param4List.put("DESC", "降序");param4List.put("ASC", "升序");
		ActionContext.getContext().put("param3List", param3List);
		ActionContext.getContext().put("param4List", param4List);
		return "search";
	}
	
	public String normalDelete() throws Exception{
		User user=(User) ActionContext.getContext().getSession().get("user");
		if(user==null){
			return "loginUI";
		}else{//只有自己资源才能删除
			Source source=sourceService.getById(model.getId());
			if(source.getUser().getNickname()==user.getNickname()){
				sourceService.delete(model.getId());
			}
		}
		return "normalDelete";
	}
	
	
	//==================以下方法为管理员使用的方法==============================
	public String editUI() throws Exception{
		List<ProgLanguage> languageList=new ArrayList<ProgLanguage>();
		List<Label> labelList=new ArrayList<Label>();
		languageList=progLanguageService.findAll();
		labelList=labelService.findAll();
		Source source=sourceService.getById(model.getId());
		
		ActionContext.getContext().put("labelList", labelList);
		ActionContext.getContext().put("languageList", languageList);
		ActionContext.getContext().getValueStack().push(source);
		return "editUI";
	}
	
	/**
	 * 编辑
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception{
		Source source=sourceService.getById(model.getId());
		source.setDescription(model.getDescription());
		source.setTitle(model.getTitle());
		source.setLanguage(model.getLanguage());
		source.setType(model.getType());
		sourceService.update(source);
		if(flag.equals("ok")){
			return "toListOk";
		}else{
			return "toListUnchecked";
		}
	}
	
	/**
	 * 通过审核
	 * @return
	 * @throws Exception
	 */
	public String check() throws Exception{
		Source source=sourceService.getById(model.getId());
		source.setStatus(1);
		sourceService.update(source);
		return "toListUnchecked";
	}
	
	public String adminDownload() throws Exception{
		Source source=sourceService.getById(model.getId());
		if(source==null){
			return null;
		}else{//都满足条件就下载吧
			/**
			 * 首先更新下载次数
			 */
			source.setCount(source.getCount()+1);
			sourceService.update(source);
			this.fileFileName=source.getPath().substring(source.getPath().lastIndexOf("\\")+1);
			//解决乱码
			this.fileFileName=new String(this.fileFileName.getBytes("UTF-8"),"ISO-8859-1");
			PATH=source.getPath();
		}
		return "download";
	}
	
	/**
	 * 管理员删除操作
	 * @return
	 * @throws Exception
	 */
	public String adminDelete() throws Exception{
		sourceService.delete(model.getId());
		System.out.println("flag="+flag);
		if(flag.equals("ok")){
			return "toListOk";
		}else{
			return "toListUnchecked";
		}
	}
	
	/**
	 * 审核通过的资源列表
	 * @return
	 * @throws Exception
	 */
	public String listOk() throws Exception{
		//准备分页数据
		QueryHelper queryHelper=new QueryHelper(Source.class, "source");
		queryHelper.addCondition("source.status=?",1)
					.preparePageBean(sourceService, pageNum, pageSize);
		return "listOk";
	}
	
	/**
	 * 审核未通过的资源列表
	 * @return
	 * @throws Exception
	 */
	public String listUnchecked() throws Exception{
		QueryHelper queryHelper=new QueryHelper(Source.class, "source");
		queryHelper.addCondition("source.status=?",0)
					.preparePageBean(sourceService, pageNum, pageSize);
		return "listUnchecked";
	}
	
	/**
	 *下面是set和get方法
	 */
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPATH() {
		return PATH;
	}

	public void setPATH(String pATH) {
		PATH = pATH;
	}

	public String getParam1() {
		return param1;
	}

	public void setParam1(String param1) {
		this.param1 = param1;
	}

	public String getParam2() {
		return param2;
	}

	public void setParam2(String param2) {
		this.param2 = param2;
	}

	public String getParam3() {
		return param3;
	}

	public void setParam3(String param3) {
		this.param3 = param3;
	}

	public String getParam4() {
		return param4;
	}

	public void setParam4(String param4) {
		this.param4 = param4;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}	
	
}
