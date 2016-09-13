package com.xidian.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xidian.entity.Teacher;
import com.xidian.service.TeacherService;

public class TeacherAction extends ActionSupport implements
		ModelDriven<Teacher> {
	private Teacher teacher = new Teacher();
	private TeacherService teacherService;

	@Override
	public Teacher getModel() {
		return this.teacher;
	}

	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}

	/**
	 * 判断教师是否存在
	 * 
	 * @return
	 * @throws IOException
	 */
	public String judgeTeacher() throws IOException {
		List<Teacher> list = teacherService.findByProperty("identityId",
				this.teacher.getIdentityId());
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		if (null != list && list.size() > 0) {
			response.getWriter().println("教师已存在！！！");
		} else {
			response.getWriter().print("");
		}
		return NONE;
	}

	/**
	 * 添加教师
	 * 
	 * @return
	 */
	public String addTeacher() {
		teacherService.addTeacher(this.teacher);
		return NONE;
	}

	/**
	 * 查询所有教师记录数
	 * 
	 * @return
	 * @throws IOException 
	 */
	public String queryAllTeacherNumber() throws IOException {
		int count = teacherService.findAll(1, Integer.MAX_VALUE).size();
		count = count / 10 + (count % 10 == 0 ? 0 : 1);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(count);
		return NONE;
	}

	/**
	 * 查询所有教师
	 * 
	 * @return
	 * @throws IOException
	 */
	public String queryAllTeacher() throws IOException {
		int page = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("num"));
		List<Teacher> list = teacherService.findAll(page, 10);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JSONArray jsonArray = JSONArray.fromObject(list);
		response.getWriter().println(jsonArray);
		return NONE;
	}
}
