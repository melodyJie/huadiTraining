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
import com.xidian.data.Data;
import com.xidian.entity.College;
import com.xidian.entity.Specialty;
import com.xidian.entity.Student;
import com.xidian.entity.Studentclass;
import com.xidian.service.CollegeService;
import com.xidian.service.SpecialtyService;
import com.xidian.service.StudentService;
import com.xidian.service.StudentclassService;

public class StudentAction extends ActionSupport implements
		ModelDriven<Student> {
	private Student student = new Student();
	private StudentService studentService;
	private StudentclassService studentclassService;
	private CollegeService collegeService;
	private SpecialtyService specialtyService;

	@Override
	public Student getModel() {
		return this.student;
	}

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	public void setStudentclassService(StudentclassService studentclassService) {
		this.studentclassService = studentclassService;
	}

	public void setCollegeService(CollegeService collegeService) {
		this.collegeService = collegeService;
	}

	public void setSpecialtyService(SpecialtyService specialtyService) {
		this.specialtyService = specialtyService;
	}

	/**
	 * 查询学生所选宿舍
	 * 
	 * @return
	 * @throws IOException
	 */
	public String queryRoom() throws IOException {
		Student student = (Student) ServletActionContext.getRequest()
				.getSession().getAttribute("user");
		Student s = studentService.findByProperty("studentId",
				student.getStudentId()).get(0);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		if (null != s.getRoom()) {
			JSONArray jsonArray = JSONArray.fromObject(s.getRoom());
			response.getWriter().println(jsonArray);
		} else {
			response.getWriter().print("");
		}
		return NONE;
	}

	/**
	 * 选择系统
	 * 
	 * @return
	 */
	public String chooseSystem() {
		return "system";
	}

	/**
	 * 获取个人信息
	 * 
	 * @return
	 * @throws IOException
	 */
	public String getPersonalMessage() throws IOException {
		List<Data> list = studentService.getPersonalMessage();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JSONArray jsonArray = JSONArray.fromObject(list);
		response.getWriter().println(jsonArray);
		return NONE;
	}

	/**
	 * 判断学生是否存在
	 * 
	 * @return
	 * @throws IOException 
	 */
	public String judgeStudent() throws IOException {
		List<Student> list = studentService.findByProperty("identityId",
				this.student.getIdentityId());
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		if (null != list && list.size() > 0){
			response.getWriter().println("学生已存在！！！");
		} else {
			response.getWriter().print("");
		}
		return NONE;
	}

	/**
	 * 添加学生
	 * 
	 * @return
	 */
	public String addStudent() {
		Studentclass studentclass = studentclassService.findByProperty(
				"classNumber", student.getPassword()).get(0);
		student.setStudentclass(studentclass);
		studentService.addStudent(student);
		return NONE;
	}

	/**
	 * 根据条件查询学生记录数
	 * 
	 * @return
	 * @throws IOException
	 */
	public String queryAllStudentsNumber() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		int grade = Integer.parseInt(request.getParameter("nianji"));
		List<Studentclass> studentclasses = new ArrayList<Studentclass>();
		if (!request.getParameter("banji").equals("")) {
			int studentclassId = Integer
					.parseInt(request.getParameter("banji"));
			studentclasses.add(studentclassService.findByProperty("classId",
					(long) studentclassId).get(0));
		} else if (!request.getParameter("zhuanye").equals("")) {
			int specialtyId = Integer.parseInt(request.getParameter("zhuanye"));
			Specialty specialty = specialtyService.findById((long) specialtyId);
			studentclasses = studentclassService.findByProperty("specialty",
					specialty);
		} else if (!request.getParameter("xueyuan").equals("")) {
			int collegeId = Integer.parseInt(request.getParameter("xueyuan"));
			College college = collegeService.findById((long) collegeId);
			List<Specialty> list = specialtyService.querySpecialty(college);
			for (Specialty specialty : list) {
				studentclasses.addAll(studentclassService.findByProperty(
						"specialty", specialty));
			}
		}
		int count = studentService.queryAllStudent(studentclasses, grade, 1,
				Integer.MAX_VALUE).size();
		count = count / 10 + (count % 10 == 0 ? 0 : 1);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(count);
		return NONE;
	}

	/**
	 * 根据条件查询学生
	 * 
	 * @return
	 * @throws IOException
	 */
	public String queryAllStudents() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		int grade = Integer.parseInt(request.getParameter("nianji"));
		List<Studentclass> studentclasses = new ArrayList<Studentclass>();
		if (!request.getParameter("banji").equals("")) {
			int studentclassId = Integer
					.parseInt(request.getParameter("banji"));
			studentclasses.add(studentclassService.findByProperty("classId",
					(long) studentclassId).get(0));
		} else if (!request.getParameter("zhuanye").equals("")) {
			int specialtyId = Integer.parseInt(request.getParameter("zhuanye"));
			Specialty specialty = specialtyService.findById((long) specialtyId);
			studentclasses = studentclassService.findByProperty("specialty",
					specialty);
		} else if (!request.getParameter("xueyuan").equals("")) {
			int collegeId = Integer.parseInt(request.getParameter("xueyuan"));
			College college = collegeService.findById((long) collegeId);
			List<Specialty> list = specialtyService.querySpecialty(college);
			for (Specialty specialty : list) {
				studentclasses.addAll(studentclassService.findByProperty(
						"specialty", specialty));
			}
		}
		int page = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("num"));
		List<Student> list = studentService.queryAllStudent(studentclasses,
				grade, page, 10);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		JSONArray jsonArray = JSONArray.fromObject(list);
		response.getWriter().println(jsonArray);
		return NONE;
	}

	/**
	 * 修改学生信息
	 * 
	 * @return
	 */
	public String updateStudent() {
		Studentclass studentclass = studentclassService.findByProperty(
				"classNumber", student.getPassword()).get(0);
		student.setStudentclass(studentclass);
		studentService.updateStudent(student);
		return NONE;
	}

	/**
	 * 删除学生信息
	 * 
	 * @return
	 */
	public String deleteStudent() {
		studentService.deleteStudent(student);
		return NONE;
	}
}
