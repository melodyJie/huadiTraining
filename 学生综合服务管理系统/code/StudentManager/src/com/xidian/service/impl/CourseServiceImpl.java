package com.xidian.service.impl;

import java.util.List;

import com.xidian.dao.CourseDAO;
import com.xidian.entity.Choosing;
import com.xidian.entity.Course;
import com.xidian.entity.Teach;
import com.xidian.service.CourseService;
import com.xidian.service.TeachService;

public class CourseServiceImpl implements CourseService {
	private CourseDAO courseDAO;
	private TeachService teachService;

	public void setCourseDAO(CourseDAO courseDAO) {
		this.courseDAO = courseDAO;
	}

	public void setTeachService(TeachService teachService) {
		this.teachService = teachService;
	}

	@Override
	public List<Teach> queryCourse(int page, int offset) {
		List<Course> list = courseDAO.findByProperty("courseType", "选修课");
		List<Teach> list2 = teachService.findByCourses(list, page, offset);
		return list2;
	}

	@Override
	public boolean judgeCourse(Course course, List<Choosing> list) {
		boolean flag = false;
		for (Choosing choosing : list) {
			if (choosing.getTeach().getCourse().getCourseName()
					.equals(course.getCourseName())) {
				flag = true;
			}
		}
		return flag;
	}

	@Override
	public List<Course> findByProperty(String propertyName, Object value) {
		return courseDAO.findByProperty(propertyName, value);
	}

	@Override
	public void addCourse(Course course) {
		courseDAO.save(course);
	}

	@Override
	public void deleteCourse(Course course) {
		courseDAO.delete(courseDAO.findByProperty("courseId",
				course.getCourseId()).get(0));
	}

	@Override
	public List<Course> queryAllCourses(String input, int page, int offset) {
		List<Course> list = courseDAO.findAll(input, page, offset);
		return list;
	}

}
