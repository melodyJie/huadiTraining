package com.xidian.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xidian.entity.Achievement;
import com.xidian.entity.Choosing;
import com.xidian.entity.Course;
import com.xidian.entity.Specialtycourse;
import com.xidian.entity.Student;
import com.xidian.entity.Teacher;
import com.xidian.service.AchievementService;
import com.xidian.service.ChoosingService;
import com.xidian.service.CourseService;
import com.xidian.service.SpecialtycourseService;
import com.xidian.service.StudentService;
import com.xidian.service.TeacherService;

public class AchievementAction extends ActionSupport implements
		ModelDriven<Achievement> {
	private Achievement achievement = new Achievement();
	private AchievementService achievementService;
	private SpecialtycourseService specialtycourseService;
	private StudentService studentService;
	private CourseService courseService;
	private TeacherService teacherService;
	private ChoosingService choosingService;

	@Override
	public Achievement getModel() {
		return this.achievement;
	}

	public void setAchievementService(AchievementService achievementService) {
		this.achievementService = achievementService;
	}

	public void setSpecialtycourseService(
			SpecialtycourseService specialtycourseService) {
		this.specialtycourseService = specialtycourseService;
	}

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}

	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}

	public void setChoosingService(ChoosingService choosingService) {
		this.choosingService = choosingService;
	}

	/**
	 * 查询本学期成绩记录数
	 * 
	 * @return
	 * @throws IOException
	 */
	public String queryNowGradeNumber() throws IOException {
		int count = achievementService.queryNowGrade(1, Integer.MAX_VALUE)
				.size();
		count = count / 10 + (count % 10 == 0 ? 0 : 1);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(count);
		return NONE;
	}

	/**
	 * 查询本学期成绩
	 * 
	 * @return
	 * @throws IOException
	 */
	public String queryNowGrade() throws IOException {
		int page = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("num"));
		List<Achievement> list = achievementService.queryNowGrade(page, 10);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JSONArray jsonArray = JSONArray.fromObject(list);
		response.getWriter().println(jsonArray);
		return NONE;
	}

	/**
	 * 查询全部成绩记录
	 * 
	 * @return
	 * @throws IOException
	 */
	public String queryAllGradeNumber() throws IOException {
		int count = achievementService.queryAllGrade(1, Integer.MAX_VALUE)
				.size();
		count = count / 10 + (count % 10 == 0 ? 0 : 1);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(count);
		return NONE;
	}

	/**
	 * 查询全部成绩
	 * 
	 * @return
	 * @throws IOException
	 */
	public String queryAllGrade() throws IOException {
		int page = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("num"));
		List<Achievement> list = achievementService.queryAllGrade(page, 10);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JSONArray jsonArray = JSONArray.fromObject(list);
		response.getWriter().println(jsonArray);
		return NONE;
	}

	/**
	 * 根据专业课程查询学生记录数
	 * 
	 * @return
	 * @throws IOException
	 */
	public String queryStudentCourseNumber() throws IOException {
		Specialtycourse specialtycourse = specialtycourseService
				.findByProperty("specialtyCourseId",
						this.achievement.getAchievementId()).get(0);
		int count = studentService.queryStudentCourse(specialtycourse, 1,
				Integer.MAX_VALUE).size();
		count = count / 10 + (count % 10 == 0 ? 0 : 1);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(count);
		return NONE;
	}

	/**
	 * 根据专业课程查询学生
	 * 
	 * @return
	 * @throws IOException
	 */
	public String queryStudentCourse() throws IOException {
		int page = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("num"));
		Specialtycourse specialtycourse = specialtycourseService
				.findByProperty("specialtyCourseId",
						this.achievement.getAchievementId()).get(0);
		List<Student> list = studentService.queryStudentCourse(specialtycourse,
				page, 10);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JSONArray jsonArray = JSONArray.fromObject(list);
		response.getWriter().println(jsonArray);
		return NONE;
	}

	/**
	 * 查询选修课学生记录数
	 * 
	 * @return
	 * @throws IOException
	 */
	public String queryStudentElectivesNumber() throws IOException {
		Course course = courseService.findByProperty("courseId",
				this.achievement.getAchievementId()).get(0);
		Teacher teacher = (Teacher) ServletActionContext.getRequest()
				.getSession().getAttribute("user");
		teacher = teacherService.findByProperty("teacherId",
				teacher.getTeacherId()).get(0);
		int count = choosingService.queryStudentElectives(course, teacher, 1,
				Integer.MAX_VALUE).size();
		count = count / 10 + (count % 10 == 0 ? 0 : 1);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(count);
		return NONE;
	}

	/**
	 * 查询选修课学生
	 * 
	 * @return
	 * @throws IOException
	 */
	public String queryStudentElectives() throws IOException {
		Course course = courseService.findByProperty("courseId",
				this.achievement.getAchievementId()).get(0);
		Teacher teacher = (Teacher) ServletActionContext.getRequest()
				.getSession().getAttribute("user");
		teacher = teacherService.findByProperty("teacherId",
				teacher.getTeacherId()).get(0);
		int page = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("num"));
		List<Choosing> list = choosingService.queryStudentElectives(course,
				teacher, page, 10);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JSONArray jsonArray = JSONArray.fromObject(list);
		response.getWriter().println(jsonArray);
		return NONE;
	}

	/**
	 * 添加成绩
	 * 
	 * @return
	 */
	public String addAchievement() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String courseName = request.getParameter("courseName");
		String studentNumber = request.getParameter("studentNumber");
		Student student = studentService.findByProperty("studentNumber",
				studentNumber).get(0);
		Course course = courseService.findByProperty("courseName", courseName)
				.get(0);
		Teacher teacher = (Teacher) request.getSession().getAttribute("user");
		this.achievement.setStudent(student);
		this.achievement.setCourse(course);
		this.achievement.setTeacher(teacher);
		achievementService.addAchievement(this.achievement);
		return NONE;
	}

	/**
	 * 根据教师课程查看成绩记录数
	 * 
	 * @return
	 * @throws IOException
	 */
	public String queryAchievementNumber() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String specialtyName = request.getParameter("specialty");
		String courseName = request.getParameter("courseName");
		Course course = courseService.findByProperty("courseName", courseName)
				.get(0);
		Teacher teacher = (Teacher) request.getSession().getAttribute("user");
		this.achievement.setCourse(course);
		this.achievement.setTeacher(teacher);
		int count = achievementService.queryAchievement(this.achievement,
				specialtyName, 1, Integer.MAX_VALUE).size();
		count = count / 10 + (count % 10 == 0 ? 0 : 1);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(count);
		return NONE;
	}

	/**
	 * 根据教师课程查看成绩
	 * 
	 * @return
	 * @throws IOException
	 */
	public String queryAchievement() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String specialtyName = null;
		if (null != request.getParameter("specialty")){
			specialtyName = request.getParameter("specialty");
		}
		String courseName = request.getParameter("courseName");
		int page = Integer.parseInt(request.getParameter("num"));
		Course course = courseService.findByProperty("courseName", courseName)
				.get(0);
		Teacher teacher = (Teacher) request.getSession().getAttribute("user");
		this.achievement.setCourse(course);
		this.achievement.setTeacher(teacher);
		List<Achievement> list = achievementService.queryAchievement(
				this.achievement, specialtyName, page, 10);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JSONArray jsonArray = JSONArray.fromObject(list);
		response.getWriter().println(jsonArray);
		return NONE;
	}

	/**
	 * 修改成绩
	 * 
	 * @return
	 */
	public String updateAchievement() {
		achievementService.updateAchievement(this.achievement);
		return NONE;
	}
}
