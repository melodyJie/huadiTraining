package com.xidian.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xidian.entity.College;
import com.xidian.entity.Specialty;
import com.xidian.service.CollegeService;
import com.xidian.service.SpecialtyService;

public class SpecialtyAction extends ActionSupport implements
		ModelDriven<Specialty> {
	private Specialty specialty = new Specialty();
	private SpecialtyService specialtyService;
	private CollegeService collegeService;

	@Override
	public Specialty getModel() {
		return this.specialty;
	}

	public void setSpecialtyService(SpecialtyService specialtyService) {
		this.specialtyService = specialtyService;
	}

	public void setCollegeService(CollegeService collegeService) {
		this.collegeService = collegeService;
	}

	/**
	 * 根据学院查询专业
	 * 
	 * @return
	 * @throws IOException
	 */
	public String querySpecialty() throws IOException {
		College college = collegeService.findById(specialty.getSpecialtyId());
		List<Specialty> list = specialtyService.querySpecialty(college);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JSONArray jsonArray = JSONArray.fromObject(list);
		response.getWriter().println(jsonArray);
		return NONE;
	}

	/**
	 * 查询所有专业
	 * 
	 * @return
	 * @throws IOException
	 */
	public String queryAllSpecialty() throws IOException {
		List<Specialty> list = specialtyService.queryAllSpecialty(1,
				Integer.MAX_VALUE);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JSONArray jsonArray = JSONArray.fromObject(list);
		response.getWriter().println(jsonArray);
		return NONE;
	}

}
