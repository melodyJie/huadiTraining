package com.xidian.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.xidian.dao.TeachDAO;
import com.xidian.data.TeacherCourse;
import com.xidian.entity.Course;
import com.xidian.entity.Specialtycourse;
import com.xidian.entity.Teach;
import com.xidian.entity.Teacher;
import com.xidian.service.CourseService;
import com.xidian.service.SpecialtycourseService;
import com.xidian.service.TeachService;
import com.xidian.service.TeacherService;

public class TeachServiceImpl implements TeachService {
	private TeachDAO teachDAO;
	private CourseService courseService;
	private TeacherService teacherService;
	private SpecialtycourseService specialtycourseService;

	public void setTeachDAO(TeachDAO teachDAO) {
		this.teachDAO = teachDAO;
	}

	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}

	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}

	public void setSpecialtycourseService(
			SpecialtycourseService specialtycourseService) {
		this.specialtycourseService = specialtycourseService;
	}

	@Override
	public Teach findByTeach(Course course, Teacher teacher) {
		List<String> propertyNames = new ArrayList<String>();
		List<Object> values = new ArrayList<Object>();
		propertyNames.add("course");
		values.add(course);
		propertyNames.add("teacher");
		values.add(teacher);
		List<Teach> list = teachDAO.findByProperties(propertyNames, values, 1,
				Integer.MAX_VALUE);
		if (null != list && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<Teach> queryTeacher(Teach teach) {
		Course course = courseService.findByProperty("courseId",
				teach.getTeachId()).get(0);
		List<Teach> list = teachDAO.findByProperty("course", course, 1,
				Integer.MAX_VALUE);
		return list;
	}

	@Override
	public void updateTeacherCourse(Teacher teacher, String[] courseId) {
		List<Teach> list = teachDAO.findByProperty("teacher", teacher, 1,
				Integer.MAX_VALUE);
		if (null != list && list.size() > 0) {
			for (Teach teach : list) {
				teach.setState(1);
				teachDAO.attachDirty(teach);
			}
		}
		if (null != courseId && courseId.length > 0) {
			for (int i = 0; i < courseId.length; i++) {
				Course course = courseService.findByProperty("courseId",
						Long.parseLong(courseId[i])).get(0);
				Teach teach = this.findByTeach(course, teacher);
				if (null != teach) {
					teach.setState(0);
					teachDAO.attachDirty(teach);
				} else {
					Teach t = new Teach();
					t.setCourse(course);
					t.setTeacher(teacher);
					t.setState(0);
					teachDAO.save(t);
				}
			}
		}
		List<Teach> list2 = teachDAO.findByProperty("state", 1, 1,
				Integer.MAX_VALUE);
		if (null != list2 && list2.size() > 0) {
			for (Teach teach : list2) {
				teachDAO.delete(teach);
			}
		}
	}

	@Override
	public List<Teach> findByProperty(String propertyName, Object value,
			int page, int offset) {
		return teachDAO.findByProperty(propertyName, value, page, offset);
	}

	@Override
	public List<Teach> queryElectives(Teacher teacher, int page, int offset) {
		List<String> propertyNames = new ArrayList<String>();
		List<Object> values = new ArrayList<Object>();
		propertyNames.add("teacher");
		propertyNames.add("course.courseType");
		values.add(teacher);
		values.add("选修课");
		List<Teach> list = teachDAO.findByProperties(propertyNames, values,
				page, offset);
		return list;
	}

	@Override
	public List<Teach> findByCourses(List<Course> courses, int page, int offset) {
		List<Teach> list = teachDAO.findByCourses(courses, page, offset);
		return list;
	}

	@Override
	public List<TeacherCourse> queryTeacherCourse(int page, int offset) {
		List<Teacher> list = teacherService.findAll(page, offset);
		List<TeacherCourse> list5 = new ArrayList<TeacherCourse>();
		for (Teacher teacher : list) {
			List<Teach> list2 = teachDAO.findByProperty("teacher", teacher, 1,
					Integer.MAX_VALUE);
			List<Course> list3 = new ArrayList<Course>();
			StringBuffer sb = new StringBuffer();
			for (Teach teach : list2) {
				list3.add(teach.getCourse());
				List<Specialtycourse> list4 = specialtycourseService
						.findByProperty("teach", teach);
				if (null != list4 && list4.size() > 0){
					sb.append("1");
				} else {
					sb.append("0");
				}
			}
			TeacherCourse teacherCourse = new TeacherCourse();
			teacherCourse.setTeacher(teacher);
			teacherCourse.setCourses(list3);
			teacherCourse.setAvailable(sb.toString());
			list5.add(teacherCourse);
		}
		return list5;
	}
}
