package com.xidian.service;

import java.util.List;

import com.xidian.entity.Course;
import com.xidian.entity.Specialtycourse;
import com.xidian.entity.Teach;

public interface SpecialtycourseService {

	/**
	 * 查询专业课程
	 * 
	 * @param propertyNames
	 * @param values
	 * @return
	 */
	List<Specialtycourse> querySpecialtycourse(List<String> propertyNames,
			List<Object> values);

	/**
	 * 查询可添加课程
	 * 
	 * @param propertyNames
	 * @param values
	 * @return
	 */
	List<Course> queryChooseCourse(List<String> propertyNames,
			List<Object> values);

	/**
	 * 添加专业课程
	 * 
	 * @param s
	 */
	void addSpecialtycourse(Specialtycourse specialtycourse);

	/**
	 * 删除专业课程
	 * 
	 * @param specialtycourse
	 */
	void deleteSpecialtycourse(Specialtycourse specialtycourse);

	/**
	 * 根据属性查询专业课程
	 * 
	 * @param propertyName
	 * @param value
	 * @return
	 */
	List<Specialtycourse> findByProperty(String propertyName, Object value);

	/**
	 * 查询教师专业课程
	 * 
	 * @param list
	 * @param offset
	 * @param page
	 * @return
	 */
	List<Specialtycourse> queryTeacherCourse(List<Teach> list, int page,
			int offset);

}
