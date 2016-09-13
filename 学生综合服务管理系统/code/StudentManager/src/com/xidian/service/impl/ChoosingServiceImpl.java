package com.xidian.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.xidian.dao.AchievementDAO;
import com.xidian.dao.ChoosingDAO;
import com.xidian.entity.Achievement;
import com.xidian.entity.Choosing;
import com.xidian.entity.Course;
import com.xidian.entity.Student;
import com.xidian.entity.Teach;
import com.xidian.entity.Teacher;
import com.xidian.service.ChoosingService;
import com.xidian.service.TeachService;

public class ChoosingServiceImpl implements ChoosingService {
	private ChoosingDAO choosingDAO;
	private AchievementDAO achievementDAO;
	private TeachService teachService;

	public void setChoosingDAO(ChoosingDAO choosingDAO) {
		this.choosingDAO = choosingDAO;
	}

	public void setAchievementDAO(AchievementDAO achievementDAO) {
		this.achievementDAO = achievementDAO;
	}

	public void setTeachService(TeachService teachService) {
		this.teachService = teachService;
	}

	@Override
	public void saveChoosing(Teach teach) {
		Student student = (Student) ServletActionContext.getRequest()
				.getSession().getAttribute("user");
		Choosing choosing = new Choosing();
		choosing.setTeach(teach);
		choosing.setStudent(student);
		choosingDAO.save(choosing);
	}

	@Override
	public void deleteChoosing(Choosing choosing) {
		Choosing choosing2 = choosingDAO.findByProperty("choosingId",
				choosing.getChoosingId()).get(0);
		choosingDAO.delete(choosing2);
	}

	@Override
	public List<Choosing> findChoosings(Student student) {
		return choosingDAO.findByProperty("student", student);
	}

	@Override
	public List<Choosing> queryResult() {
		Student student = (Student) ServletActionContext.getRequest()
				.getSession().getAttribute("user");
		List<Choosing> list = choosingDAO.findByProperty("student", student);
		return list;
	}

	@Override
	public List<Choosing> queryStudentElectives(Course course, Teacher teacher,
			int page, int offset) {
		List<String> propertyNames = new ArrayList<String>();
		propertyNames.add("course");
		propertyNames.add("teacher");
		List<Object> values = new ArrayList<Object>();
		values.add(course);
		values.add(teacher);
		List<Achievement> achievements = achievementDAO.findByProperties(
				propertyNames, values, 1, Integer.MAX_VALUE);
		List<Student> students = new ArrayList<Student>();
		for (Achievement achievement : achievements) {
			students.add(achievement.getStudent());
		}
		Teach teach = teachService.findByTeach(course, teacher);
		List<Choosing> list = choosingDAO.findByDivide(students, teach, page,
				offset);
		return list;
	}

}
