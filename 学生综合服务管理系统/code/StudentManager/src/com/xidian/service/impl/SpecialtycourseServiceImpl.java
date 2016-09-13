package com.xidian.service.impl;

import java.util.List;

import com.xidian.dao.SpecialtycourseDAO;
import com.xidian.entity.Course;
import com.xidian.entity.Specialtycourse;
import com.xidian.entity.Teach;
import com.xidian.service.CourseService;
import com.xidian.service.SpecialtycourseService;

public class SpecialtycourseServiceImpl implements SpecialtycourseService {
	private SpecialtycourseDAO specialtycourseDAO;
	private CourseService courseService;

	public void setSpecialtycourseDAO(SpecialtycourseDAO specialtycourseDAO) {
		this.specialtycourseDAO = specialtycourseDAO;
	}

	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}

	@Override
	public List<Specialtycourse> querySpecialtycourse(List<String> propertyNames,
			List<Object> values) {
		List<Specialtycourse> list = specialtycourseDAO.findByProperties(
				propertyNames, values);
		return list;
	}

	@Override
	public List<Course> queryChooseCourse(List<String> propertyNames,
			List<Object> values) {
		List<Specialtycourse> list = specialtycourseDAO.findByProperties(
				propertyNames, values);
		List<Course> list2 = courseService.findByProperty("courseType", "必修课");
		for (Specialtycourse specialtycourse : list) {
			list2.remove(specialtycourse.getTeach().getCourse());
		}
		return list2;
	}

	@Override
	public void addSpecialtycourse(Specialtycourse specialtycourse) {
		specialtycourseDAO.save(specialtycourse);
	}

	@Override
	public void deleteSpecialtycourse(Specialtycourse specialtycourse) {
		specialtycourseDAO.delete(specialtycourseDAO.findByProperty(
				"specialtyCourseId", specialtycourse.getSpecialtyCourseId())
				.get(0));
	}

	@Override
	public List<Specialtycourse> findByProperty(String propertyName,
			Object value) {
		return specialtycourseDAO.findByProperty(propertyName, value);
	}

	@Override
	public List<Specialtycourse> queryTeacherCourse(List<Teach> list, int page,
			int offset) {
		List<Specialtycourse> list2 = specialtycourseDAO.findByTeaches(list,
				page, offset);
		return list2;
	}
}
