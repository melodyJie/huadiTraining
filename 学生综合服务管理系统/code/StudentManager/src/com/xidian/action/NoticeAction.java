package com.xidian.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xidian.entity.Notice;
import com.xidian.service.NoticeService;

public class NoticeAction extends ActionSupport implements ModelDriven<Notice> {
	private Notice notice = new Notice();
	private NoticeService noticeService;
	
	@Override
	public Notice getModel() {
		return this.notice;
	}
	
	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	
	/**
	 * 修改公告
	 * @return
	 * @throws IOException 
	 */
	public String addNotice() throws IOException{
		noticeService.updateNotice(this.notice);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().println("修改成功！！！");
		return NONE;
	}
	
	/**
	 * 获取公告
	 * @return
	 * @throws IOException 
	 */
	public String attainNotice() throws IOException{
		String notice = noticeService.attainNotice(this.notice);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().println(notice);
		return NONE;
	}

}
