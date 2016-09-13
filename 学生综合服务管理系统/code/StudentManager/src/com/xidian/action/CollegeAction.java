package com.xidian.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xidian.entity.College;
import com.xidian.service.CollegeService;

public class CollegeAction extends ActionSupport implements
		ModelDriven<College> {
	private College college = new College();
	private CollegeService collegeService;

	@Override
	public College getModel() {
		return this.college;
	}

	public void setCollegeService(CollegeService collegeService) {
		this.collegeService = collegeService;
	}

	/**
	 * 查询所有学院
	 * 
	 * @return
	 * @throws IOException
	 */
	public String queryCollege() throws IOException {
		List<College> list = collegeService.queryCollege();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JSONArray jsonArray = JSONArray.fromObject(list);
		response.getWriter().println(jsonArray);
		return NONE;
	}
}
