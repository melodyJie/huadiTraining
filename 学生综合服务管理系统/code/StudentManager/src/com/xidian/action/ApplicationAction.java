package com.xidian.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xidian.entity.Application;
import com.xidian.entity.Student;
import com.xidian.service.ApplicationService;
import com.xidian.service.StudentService;
import com.xidian.util.JsonDateValueProcessor;

public class ApplicationAction extends ActionSupport implements
		ModelDriven<Application> {
	private Application application = new Application();
	private ApplicationService applicationService;
	private StudentService studentService;

	@Override
	public Application getModel() {
		return this.application;
	}

	public void setApplicationService(ApplicationService applicationService) {
		this.applicationService = applicationService;
	}

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	/**
	 * 添加留校申请
	 * 
	 * @return
	 * @throws IOException
	 */
	public String addApplication() throws IOException {
		applicationService.addApplication(application);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().println("提交成功！！！");
		return NONE;
	}

	/**
	 * 查询留校申请记录数
	 * 
	 * @return
	 * @throws IOException
	 */
	public String queryApplicationNumber() throws IOException {
		int count = applicationService.queryApplication(1, Integer.MAX_VALUE)
				.size();
		count = count / 10 + (count % 10 == 0 ? 0 : 1);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(count);
		return NONE;
	}

	/**
	 * 查询留校申请
	 * 
	 * @return
	 * @throws IOException
	 */
	public String queryApplication() throws IOException {
		int page = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("num"));
		List<Application> list = applicationService.queryApplication(page,
				10);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class,
				new JsonDateValueProcessor());
		JSONArray jsonArray = JSONArray.fromObject(list, jsonConfig);
		response.getWriter().println(jsonArray);
		return NONE;
	}

	/**
	 * 取消留校申请
	 * 
	 * @return
	 */
	public String cancelApplication() {
		applicationService.cancelApplication(application);
		return NONE;
	}

	/**
	 * 根据状态查询所有留校申请记录数
	 * 
	 * @return
	 * @throws IOException 
	 */
	public String queryAllApplicationNumber() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		List<Student> list = new ArrayList<Student>();
		if (null != application.getReason()
				&& !application.getReason().equals(" ")) {
			Student student = new Student();
			String[] strs = application.getReason().trim().split(" ");
			if (strs.length == 2) {
				student.setName(strs[0]);
				student.setStudentNumber(strs[1]);
			} else {
				if (application.getReason().startsWith(" ")) {
					student.setStudentNumber(application.getReason().trim());
				} else {
					student.setName(application.getReason().trim());
				}
			}
			list = studentService.queryStudent(student);
			if (null == list || list.size() == 0) {
				response.getWriter().print("0");
				return NONE;
			}
		}
		String[] states = application.getState().split(" ");
		int count = applicationService.queryAllApplication(list, states, 1,
				Integer.MAX_VALUE).size();
		count = count / 10 + (count % 10 == 0 ? 0 : 1);
		response.getWriter().print(count);
		return NONE;
	}

	/**
	 * 根据状态查询所有留校申请
	 * 
	 * @return
	 * @throws IOException
	 */
	public String queryAllApplication() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		List<Student> list = new ArrayList<Student>();
		if (null != application.getReason()
				&& !application.getReason().equals(" ")) {
			Student student = new Student();
			String[] strs = application.getReason().trim().split(" ");
			if (strs.length == 2) {
				student.setName(strs[0]);
				student.setStudentNumber(strs[1]);
			} else {
				if (application.getReason().startsWith(" ")) {
					student.setStudentNumber(application.getReason().trim());
				} else {
					student.setName(application.getReason().trim());
				}
			}
			list = studentService.queryStudent(student);
			if (null == list || list.size() == 0) {
				return NONE;
			}
		}
		String[] states = application.getState().split(" ");
		int page = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("num"));
		List<Application> list2 = applicationService.queryAllApplication(list,
				states, page, 10);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class,
				new JsonDateValueProcessor());
		JSONArray jsonArray = JSONArray.fromObject(list2, jsonConfig);
		response.getWriter().println(jsonArray);
		return NONE;
	}

	/**
	 * 修改申请信息
	 * 
	 * @return
	 */
	public String updateApplication() {
		applicationService.updateApplication(this.application);
		return NONE;
	}

}
