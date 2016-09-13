package com.xidian.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xidian.entity.Specialty;
import com.xidian.entity.Studentclass;
import com.xidian.service.SpecialtyService;
import com.xidian.service.StudentclassService;

public class StudentclassAction extends ActionSupport implements
		ModelDriven<Studentclass> {
	private Studentclass studentclass = new Studentclass();
	private SpecialtyService specialtyService;
	private StudentclassService studentclassService;

	@Override
	public Studentclass getModel() {
		return this.studentclass;
	}

	public void setSpecialtyService(SpecialtyService specialtyService) {
		this.specialtyService = specialtyService;
	}

	public void setStudentclassService(StudentclassService studentclassService) {
		this.studentclassService = studentclassService;
	}

	/**
	 * 根据专业查询班级
	 * 
	 * @return
	 * @throws IOException
	 */
	public String queryClass() throws IOException {
		Specialty specialty = specialtyService.findById(this.studentclass
				.getClassId());
		List<Studentclass> list = new ArrayList<Studentclass>();
		if (null != ServletActionContext.getRequest().getParameter("year")){
			int grade = Integer.parseInt(ServletActionContext.getRequest().getParameter("year"));
			list = studentclassService.findBySpecialtyYear(specialty, grade);
		} else {
			list = studentclassService.findByProperty("specialty", specialty);
		}

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JSONArray jsonArray = JSONArray.fromObject(list);
		response.getWriter().println(jsonArray);
		return NONE;
	}

	/**
	 * 查询所有班级
	 * 
	 * @return
	 * @throws IOException
	 */
	public String queryAllClass() throws IOException {
		List<Studentclass> list = studentclassService.queryAllClass();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JSONArray jsonArray = JSONArray.fromObject(list);
		response.getWriter().println(jsonArray);
		return NONE;
	}

}
