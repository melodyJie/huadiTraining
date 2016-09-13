package com.xidian.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xidian.data.TeacherCourse;
import com.xidian.entity.Teach;
import com.xidian.entity.Teacher;
import com.xidian.service.CourseService;
import com.xidian.service.SpecialtyService;
import com.xidian.service.TeachService;
import com.xidian.service.TeacherService;

public class TeachAction extends ActionSupport implements ModelDriven<Teach> {
	private Teach teach = new Teach();
	private TeachService teachService;
	private SpecialtyService specialtyService;
	private CourseService courseService;
	private TeacherService teacherService;

	@Override
	public Teach getModel() {
		return this.teach;
	}

	public void setTeachService(TeachService teachService) {
		this.teachService = teachService;
	}

	public void setSpecialtyService(SpecialtyService specialtyService) {
		this.specialtyService = specialtyService;
	}

	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}

	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}

	/**
	 * 根据课程查询教师
	 * 
	 * @return
	 * @throws IOException
	 */
	public String queryTeacher() throws IOException {
		List<Teach> list = teachService.queryTeacher(this.teach);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JSONArray jsonArray = JSONArray.fromObject(list);
		response.getWriter().println(jsonArray);
		return NONE;
	}

	/**
	 * 查询教师授课记录数
	 * 
	 * @return
	 * @throws IOException 
	 */
	public String queryTeacherCourseNumber() throws IOException {
		int count = teachService.queryTeacherCourse(1, Integer.MAX_VALUE)
				.size();
		count = count / 10 + (count % 10 == 0 ? 0 : 1);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(count);
		return NONE;
	}

	/**
	 * 查询教师授课
	 * 
	 * @return
	 * @throws IOException
	 */
	public String queryTeacherCourse() throws IOException {
		int page = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("num"));
		List<TeacherCourse> list = teachService.queryTeacherCourse(page, 10);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JSONArray jsonArray = JSONArray.fromObject(list);
		response.getWriter().println(jsonArray);
		return NONE;
	}

	/**
	 * 更新教师授课信息
	 * 
	 * @return
	 */
	public String updateTeacherCourse() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String[] courseId = null;
		if (null != request.getParameter("courseId")
				&& !request.getParameter("courseId").equals("")) {
			courseId = request.getParameter("courseId").split(" ");
		}
		Teacher teacher = teacherService.findByProperty("teacherNumber",
				request.getParameter("teacherNumber")).get(0);
		teachService.updateTeacherCourse(teacher, courseId);
		return NONE;
	}

	/**
	 * 查询教师选修课
	 * 
	 * @return
	 * @throws IOException
	 */
	public String queryElectives() throws IOException {
		Teacher teacher = (Teacher) ServletActionContext.getRequest()
				.getSession().getAttribute("user");
		teacher = teacherService.findByProperty("teacherId",
				teacher.getTeacherId()).get(0);
		List<Teach> list = teachService.queryElectives(teacher, 1,
				Integer.MAX_VALUE);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JSONArray jsonArray = JSONArray.fromObject(list);
		response.getWriter().println(jsonArray);
		return NONE;
	}
}
