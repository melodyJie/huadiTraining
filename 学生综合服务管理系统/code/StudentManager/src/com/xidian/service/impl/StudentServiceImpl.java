package com.xidian.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.xidian.dao.AchievementDAO;
import com.xidian.dao.StudentDAO;
import com.xidian.data.Data;
import com.xidian.entity.Achievement;
import com.xidian.entity.Specialty;
import com.xidian.entity.Specialtycourse;
import com.xidian.entity.Student;
import com.xidian.entity.Studentclass;
import com.xidian.service.StudentService;
import com.xidian.service.StudentclassService;

public class StudentServiceImpl implements StudentService {
	private StudentDAO studentDAO;
	private StudentclassService studentclassService;
	private AchievementDAO achievementDAO;

	public void setStudentDAO(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}

	public void setStudentclassService(StudentclassService studentclassService) {
		this.studentclassService = studentclassService;
	}

	public void setAchievementDAO(AchievementDAO achievementDAO) {
		this.achievementDAO = achievementDAO;
	}

	@Override
	public List<Student> queryStudent(Student s) {
		List<Student> list = studentDAO.findByExample(s);
		return list;
	}

	@Override
	public void update(Student student) {
		studentDAO.attachDirty(student);
	}

	@Override
	public List<Data> getPersonalMessage() {
		List<Data> list = new ArrayList<Data>();
		Data data = new Data();
		Student s = (Student) ServletActionContext.getRequest().getSession()
				.getAttribute("user");
		Student student = studentDAO.findByProperty("studentId",
				s.getStudentId()).get(0);
		list.add(new Data("用户类别", "学生"));
		list.add(new Data("身份证号", student.getIdentityId()));
		list.add(new Data("姓名", student.getName()));
		list.add(new Data("性别", student.getSex()));
		list.add(new Data("学院", student.getStudentclass().getSpecialty()
				.getCollege().getCollegeName()));
		list.add(new Data("专业", student.getStudentclass().getSpecialty()
				.getSpecialtyName()));
		list.add(new Data("学号", student.getStudentNumber()));
		list.add(new Data("班级", student.getStudentclass().getClassNumber()));
		return list;
	}

	@Override
	public void addStudent(Student student) {
		List<Student> list = studentDAO.findByProperty("studentclass",
				student.getStudentclass());
		String s = "";
		if (null != list && list.size() > 0) {
			Collections.sort(list, new Comparator<Student>() {
				@Override
				public int compare(Student o1, Student o2) {
					return o1.getStudentId().compareTo(o2.getStudentId());
				}
			});
			String str = list.get(list.size() - 1).getStudentNumber().substring(6);
			int i = 0;
			for (; i < str.length(); i++){
				if (str.charAt(i) != '0'){
					break;
				}
			}
			int count = Integer.parseInt(str.substring(i));
			s = (count + 1) + "";
			while (s.length() < 4) {
				s = "0" + s;
			}
		} else {
			s = "0001";
		}
		student.setStudentNumber(student.getStudentclass().getClassNumber() + s);
		String identityId = student.getIdentityId();
		student.setPassword(identityId.substring(identityId.length() - 6));
		studentDAO.save(student);
	}

	@Override
	public List<Student> queryAllStudent(List<Studentclass> studentclasses,
			int grade, int page, int offset) {
		Calendar calendar = Calendar.getInstance();
		int month = calendar.get(Calendar.MONTH) + 1;
		int nowYear = calendar.get(Calendar.YEAR);
		int year = month >= 9 ? (nowYear - grade + 1) : (nowYear - grade);
		List<Student> list = studentDAO.findByDivide(studentclasses, null, year
				+ "", page, offset);
		return list;
	}

	@Override
	public void updateStudent(Student student) {
		Student s = studentDAO.findByProperty("studentNumber",
				student.getStudentNumber()).get(0);
		s.setName(student.getName());
		s.setSex(student.getSex());
		s.setIdentityId(student.getIdentityId());
		s.setStudentclass(student.getStudentclass());
		s.setPassword(student.getIdentityId().substring(
				student.getIdentityId().length() - 6));
		studentDAO.attachDirty(s);
	}

	@Override
	public void deleteStudent(Student student) {
		Student s = studentDAO.findByProperty("studentNumber",
				student.getStudentNumber()).get(0);
		studentDAO.delete(s);
	}

	@Override
	public List<Student> findByProperty(String propertyName, Object value) {
		return studentDAO.findByProperty(propertyName, value);
	}

	@Override
	public List<Student> queryStudentCourse(
			Specialtycourse specialtycourse, int page, int offset) {
		List<String> propertyNames = new ArrayList<String>();
		propertyNames.add("course");
		propertyNames.add("teacher");
		propertyNames.add("term");
		List<Object> values = new ArrayList<Object>();
		values.add(specialtycourse.getTeach().getCourse());
		values.add(specialtycourse.getTeach().getTeacher());
		values.add(specialtycourse.getTerm());
		List<Achievement> achievements = achievementDAO.findByProperties(
				propertyNames, values, 1, Integer.MAX_VALUE);
		List<Student> students = new ArrayList<Student>();
		for (Achievement achievement : achievements) {
			students.add(achievement.getStudent());
		}
		int grade = (specialtycourse.getTerm() + 1) / 2;
		Calendar calendar = Calendar.getInstance();
		int month = calendar.get(Calendar.MONTH) + 1;
		int nowYear = calendar.get(Calendar.YEAR);
		int year = month >= 9 ? (nowYear - grade + 1) : (nowYear - grade);
		Specialty specialty = specialtycourse.getSpecialty();
		List<Studentclass> list = studentclassService.findByProperty(
				"specialty", specialty);
		List<Student> list2 = studentDAO.findByDivide(list, students,
				year + "", page, offset);
		return list2;
	}
}
