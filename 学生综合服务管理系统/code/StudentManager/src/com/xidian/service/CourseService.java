package com.xidian.service;

import java.util.List;

import com.xidian.entity.Choosing;
import com.xidian.entity.Course;
import com.xidian.entity.Teach;

public interface CourseService {

	/**
	 * 根据类型查询课程
	 * @param offset 
	 * @param page 
	 * @return
	 */
	List<Teach> queryCourse(int page, int offset);

	/**
	 * 判断该学生是否重复选课
	 * @param course
	 * @param list 
	 * @return
	 */
	boolean judgeCourse(Course course, List<Choosing> list);

	/**
	 * 通过属性查询课程
	 * @param propertyName
	 * @param value
	 * @return
	 */
	List<Course> findByProperty(String propertyName, Object value);

	/**
	 * 添加课程
	 * @param course
	 */
	void addCourse(Course course);

	/**
	 * 删除课程
	 * @param course
	 */
	void deleteCourse(Course course);

	/**
	 * 查询所有课程
	 * @param input 
	 * @param offset 
	 * @param page 
	 * @return
	 */
	List<Course> queryAllCourses(String input, int page, int offset);

}
