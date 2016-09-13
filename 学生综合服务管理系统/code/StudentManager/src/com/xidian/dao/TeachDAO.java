package com.xidian.dao;

import java.util.List;
import java.util.Map;

import com.xidian.entity.Course;
import com.xidian.entity.Teach;
import com.xidian.entity.Teacher;

public interface TeachDAO {
	/**
	 * 添加授课
	 * 
	 * @param transientInstance
	 */
	public void save(Teach transientInstance);

	/**
	 * 删除授课
	 * 
	 * @param persistentInstance
	 */
	public void delete(Teach persistentInstance);

	/**
	 * 根据实例查询授课
	 * 
	 * @param instance
	 * @return
	 */
	public List<Teach> findByExample(Teach instance);

	/**
	 * 根据属性查询授课
	 * 
	 * @param propertyName
	 * @param value
	 * @param page
	 * @param offset
	 * @return
	 */
	public List<Teach> findByProperty(String propertyName, Object value,
			int page, int offset);

	/**
	 * 查询所有授课
	 * 
	 * @return
	 */
	public List<Teach> findAll();

	/**
	 * 更新授课
	 * 
	 * @param instance
	 */
	public void attachDirty(Teach instance);

	/**
	 * 根据某些属性查询
	 * 
	 * @param propertyNames
	 * @param values
	 * @param offset 
	 * @param page 
	 * @return
	 */
	public List<Teach> findByProperties(List<String> propertyNames,
			List<Object> values, int page, int offset);

	/**
	 * 根据课程查询所有授课
	 * @param courses
	 * @param page
	 * @param offset
	 * @return
	 */
	public List<Teach> findByCourses(List<Course> courses, int page, int offset);

	/**
	 * 根据教师查询所有授课
	 * @param teachers
	 * @return
	 */
	public List<Teach> findByTeachers(List<Teacher> teachers);
}