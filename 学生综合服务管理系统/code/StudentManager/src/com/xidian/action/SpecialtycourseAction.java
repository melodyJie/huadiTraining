package com.xidian.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xidian.entity.Course;
import com.xidian.entity.Specialty;
import com.xidian.entity.Specialtycourse;
import com.xidian.entity.Teach;
import com.xidian.entity.Teacher;
import com.xidian.service.CourseService;
import com.xidian.service.SpecialtyService;
import com.xidian.service.SpecialtycourseService;
import com.xidian.service.TeachService;
import com.xidian.service.TeacherService;

public class SpecialtycourseAction extends ActionSupport implements
		ModelDriven<Specialtycourse> {
	private Specialtycourse specialtycourse = new Specialtycourse();
	private SpecialtycourseService specialtycourseService;
	private SpecialtyService specialtyService;
	private CourseService courseService;
	private TeacherService teacherService;
	private TeachService teachService;

	@Override
	public Specialtycourse getModel() {
		return this.specialtycourse;
	}

	public void setSpecialtycourseService(
			SpecialtycourseService specialtycourseService) {
		this.specialtycourseService = specialtycourseService;
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

	public void setTeachService(TeachService teachService) {
		this.teachService = teachService;
	}

	/**
	 * 查询专业课程
	 * 
	 * @return
	 * @throws IOException
	 */
	public String querySpecialtycourse() throws IOException {
		Specialty specialty = specialtyService.findById(this.specialtycourse
				.getSpecialtyCourseId());
		List<String> propertyNames = new ArrayList<String>();
		List<Object> values = new ArrayList<Object>();
		propertyNames.add("specialty");
		values.add(specialty);
		propertyNames.add("term");
		values.add(this.specialtycourse.getTerm());
		List<Specialtycourse> list = specialtycourseService.querySpecialtycourse(
				propertyNames, values);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JSONArray jsonArray = JSONArray.fromObject(list);
		response.getWriter().println(jsonArray);
		return NONE;
	}

	/**
	 * 查询课可添加课程
	 * 
	 * @return
	 * @throws IOException
	 */
	public String queryChooseCourse() throws IOException {
		Specialty specialty = specialtyService.findById(this.specialtycourse
				.getSpecialtyCourseId());
		List<String> propertyNames = new ArrayList<String>();
		List<Object> values = new ArrayList<Object>();
		propertyNames.add("specialty");
		values.add(specialty);
		propertyNames.add("term");
		values.add(this.specialtycourse.getTerm());
		List<Course> list = specialtycourseService.queryChooseCourse(
				propertyNames, values);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JSONArray jsonArray = JSONArray.fromObject(list);
		response.getWriter().println(jsonArray);
		return NONE;
	}

	/**
	 * 添加专业课程信息
	 * 
	 * @return
	 */
	public String addSpecialtycourse() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Specialty specialty = specialtyService.findById(Long.parseLong(request
				.getParameter("specialtyId")));
		Course course = courseService.findByProperty("courseId",
				Long.parseLong(request.getParameter("courseId"))).get(0);
		Teacher teacher = teacherService.findByProperty("teacherId",
				Long.parseLong(request.getParameter("teacherId"))).get(0);
		Teach teach = teachService.findByTeach(course, teacher);
		Specialtycourse s = new Specialtycourse();
		s.setSpecialty(specialty);
		s.setTeach(teach);
		s.setTerm(this.specialtycourse.getTerm());
		specialtycourseService.addSpecialtycourse(s);
		return NONE;
	}

	/**
	 * 删除专业课程信息
	 * 
	 * @return
	 */
	public String deleteSpecialtycourse() {
		specialtycourseService.deleteSpecialtycourse(specialtycourse);
		return NONE;
	}

	/**
	 * 查询教师课程
	 * 
	 * @return
	 * @throws IOException
	 */
	public String queryTeacherCourse() throws IOException {
		Teacher teacher = (Teacher) ServletActionContext.getRequest()
				.getSession().getAttribute("user");
		teacher = teacherService.findByProperty("teacherId",
				teacher.getTeacherId()).get(0);
		List<Teach> list = teachService.findByProperty("teacher", teacher, 1,
				Integer.MAX_VALUE);
		List<Specialtycourse> list2 = specialtycourseService
				.queryTeacherCourse(list, 1, Integer.MAX_VALUE);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JSONArray jsonArray = JSONArray.fromObject(list2);
		response.getWriter().println(jsonArray);
		return NONE;
	}
}
