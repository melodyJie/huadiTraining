package com.xidian.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.xidian.entity.Manager;
import com.xidian.entity.Student;
import com.xidian.entity.Teacher;
import com.xidian.service.ManagerService;
import com.xidian.service.StudentService;
import com.xidian.service.TeacherService;

public class LoginAction extends ActionSupport {
	private String username;
	private String password;
	private String persontype;
	private StudentService studentService;
	private ManagerService managerService;
	private TeacherService teacherService;

	public ManagerService getManagerService() {
		return managerService;
	}

	public void setManagerService(ManagerService managerService) {
		this.managerService = managerService;
	}

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	public StudentService getStudentService() {
		return studentService;
	}

	public TeacherService getTeacherService() {
		return teacherService;
	}

	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPersontype() {
		return persontype;
	}

	public void setPersontype(String persontype) {
		this.persontype = persontype;
	}

	/**
	 * 登录
	 * 
	 * @return
	 * @throws IOException
	 */
	public String login() throws IOException {
		if (("staff").equals(this.getPersontype())) {
			Student s = new Student();

			s.setStudentNumber(this.getUsername());
			s.setPassword(this.getPassword());
			List<Student> list = studentService.queryStudent(s);
			if (null != list && list.size() > 0) {
				ServletActionContext.getRequest().getSession()
						.setAttribute("user", list.get(0));
				return "student";
			}
		} else if (("administrator").equals(this.getPersontype())) {
			Manager manager = new Manager();
			manager.setUsername(this.getUsername());
			manager.setPassword(this.getPassword());
			manager = managerService.queryManager(manager);
			if (null != manager) {
				ServletActionContext.getRequest().getSession()
						.setAttribute("user", manager);
				return "manager";
			}
		} else {
			Teacher teacher = new Teacher();
			teacher.setTeacherNumber(this.getUsername());
			teacher.setPassword(this.getPassword());
			teacher = teacherService.queryTeacher(teacher);
			if (null != teacher) {
				ServletActionContext.getRequest().getSession()
						.setAttribute("user", teacher);
				return "teacher";
			}
		}
		this.addActionMessage("用户名或密码错误！！！");
		return ERROR;
	}

	public String logoff() {
		ServletActionContext.getRequest().getSession()
				.setAttribute("user", null);
		return INPUT;
	}
}
