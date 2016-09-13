package com.xidian.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xidian.entity.Choosing;
import com.xidian.entity.Course;
import com.xidian.entity.Student;
import com.xidian.entity.Teach;
import com.xidian.entity.Teacher;
import com.xidian.service.ChoosingService;
import com.xidian.service.CourseService;
import com.xidian.service.TeachService;
import com.xidian.service.TeacherService;

public class CourseAction extends ActionSupport implements ModelDriven<Course> {
	private Course course = new Course();
	private CourseService courseService;
	private TeacherService teacherService;
	private TeachService teachService;
	private ChoosingService choosingService;

	@Override
	public Course getModel() {
		return this.course;
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

	public void setChoosingService(ChoosingService choosingService) {
		this.choosingService = choosingService;
	}

	/**
	 * 查询可选课程数量
	 * 
	 * @return
	 * @throws IOException
	 */
	public String queryCourseNumber() throws IOException {
		int count = courseService.queryCourse(1, Integer.MAX_VALUE).size();
		count = count / 10 + (count % 10 == 0 ? 0 : 1);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(count);
		return NONE;
	}

	/**
	 * 查询可选课程
	 * 
	 * @return
	 * @throws IOException
	 */
	public String queryCourse() throws IOException {
		int page = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("num"));
		List<Teach> list = courseService.queryCourse(page, 10);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JSONArray jsonArray = JSONArray.fromObject(list);
		response.getWriter().println(jsonArray);
		return NONE;
	}

	/**
	 * 选课
	 * 
	 * @return
	 * @throws IOException
	 */
	public String chooseCourse() throws IOException {
		Student student = (Student) ServletActionContext.getRequest()
				.getSession().getAttribute("user");
		List<Choosing> list = choosingService.findChoosings(student);
		boolean flag = courseService.judgeCourse(this.course, list);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		if (flag) {
			response.getWriter().println("你已经选过该课程！！！");
		} else {
			Course c = courseService.findByProperty("courseName",
					course.getCourseName()).get(0);
			Teacher teacher = teacherService.findByProperty("teacherNumber",
					course.getCourseType()).get(0);
			Teach teach = teachService.findByTeach(c, teacher);
			choosingService.saveChoosing(teach);
			response.getWriter().println("选课成功！！！");
		}
		return NONE;
	}

	/**
	 * 判断课程是否存在
	 * 
	 * @return
	 * @throws IOException
	 */
	public String judgeCourse() throws IOException {
		List<Course> list = courseService.findByProperty("courseName",
				this.course.getCourseName());
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		if (null != list && list.size() > 0) {
			response.getWriter().println("课程已存在！！！");
		} else {
			response.getWriter().print("");
		}
		return NONE;
	}

	/**
	 * 添加课程
	 * 
	 * @return
	 */
	public String addCourse() {
		courseService.addCourse(this.course);
		return NONE;
	}

	/**
	 * 查询所有课程数量
	 * 
	 * @return
	 * @throws IOException
	 */
	public String queryAllCoursesNumber() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String input = "";
		if (null != request.getParameter("input")
				&& !request.getParameter("input").equals("")) {
			input = request.getParameter("input");
		}
		int count = courseService.queryAllCourses(input, 1, Integer.MAX_VALUE).size();
		count = count / 10 + (count % 10 == 0 ? 0 : 1);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(count);
		return NONE;
	}

	/**
	 * 查询所有课程
	 * 
	 * @return
	 * @throws IOException
	 */
	public String queryAllCourses() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String input = "";
		int page = Integer.parseInt(request.getParameter("num"));
		if (null != request.getParameter("input")
				&& !request.getParameter("input").equals("")) {
			input = request.getParameter("input");
		}
		List<Course> list = courseService.queryAllCourses(input, page, 10);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JSONArray jsonArray = JSONArray.fromObject(list);
		response.getWriter().println(jsonArray);
		return NONE;
	}

	/**
	 * 删除课程
	 * 
	 * @return
	 */
	public String deleteCourse() {
		courseService.deleteCourse(this.course);
		return NONE;
	}

	/**
	 * 根据类型查询课程
	 * 
	 * @return
	 * @throws IOException
	 */
	public String queryCourseType() throws IOException {
		List<Course> list = courseService.findByProperty("courseType",
				this.course.getCourseType());
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JSONArray jsonArray = JSONArray.fromObject(list);
		response.getWriter().println(jsonArray);
		return NONE;
	}
}
