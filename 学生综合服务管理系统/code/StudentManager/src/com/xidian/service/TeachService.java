package com.xidian.service;

import java.util.List;

import com.xidian.data.TeacherCourse;
import com.xidian.entity.Course;
import com.xidian.entity.Teach;
import com.xidian.entity.Teacher;

public interface TeachService {

	/**
	 * 查找授课
	 * 
	 * @param c
	 * @param teacher
	 * @return
	 */
	Teach findByTeach(Course course, Teacher teacher);

	/**
	 * 根据课程查询教师
	 * 
	 * @param teach
	 * @return
	 */
	List<Teach> queryTeacher(Teach teach);

	/**
	 * 更新教师授课
	 * 
	 * @param teacher
	 * @param courseId
	 */
	void updateTeacherCourse(Teacher teacher, String[] courseId);

	/**
	 * 根据属性查询授课
	 * 
	 * @param propertyName
	 * @param value
	 * @param page
	 * @param offset
	 * @return
	 */
	List<Teach> findByProperty(String propertyName, Object value, int page,
			int offset);

	/**
	 * 查询教师选修课
	 * 
	 * @param teacher
	 * @param offset
	 * @param page
	 * @return
	 */
	List<Teach> queryElectives(Teacher teacher, int page, int offset);

	/**
	 * 根据课程查询所有授课
	 * 
	 * @param courses
	 * @param page
	 * @param offset
	 * @return
	 */
	List<Teach> findByCourses(List<Course> courses, int page, int offset);

	/**
	 * 查询所有教师授课
	 * @param page
	 * @param offset
	 * @return
	 */
	List<TeacherCourse> queryTeacherCourse(int page, int offset);

}
