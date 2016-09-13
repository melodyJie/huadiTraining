package com.xidian.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xidian.entity.Choosing;
import com.xidian.service.ChoosingService;

public class ChoosingAction extends ActionSupport implements
		ModelDriven<Choosing> {
	private Choosing choosing = new Choosing();
	private ChoosingService choosingService;

	public void setChoosingService(ChoosingService choosingService) {
		this.choosingService = choosingService;
	}

	@Override
	public Choosing getModel() {
		return this.choosing;
	}
	
	/**
	 * 查询选课结果
	 * 
	 * @return
	 * @throws IOException
	 */
	public String queryResult() throws IOException {
		List<Choosing> list = choosingService.queryResult();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JSONArray jsonArray = JSONArray.fromObject(list);
		response.getWriter().println(jsonArray);
		return NONE;
	}

	/**
	 * 取消选课
	 * 
	 * @return
	 */
	public String deleteChoosing() {
		choosingService.deleteChoosing(this.choosing);
		return NONE;
	}

}
