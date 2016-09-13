package com.xidian.dao;

import java.util.List;

import com.xidian.entity.Course;

public interface CourseDAO {
	/**
	 * 添加课程
	 * @param transientInstance
	 */
	public void save(Course transientInstance);

	/**
	 * 删除课程
	 * @param persistentInstance
	 */
	public void delete(Course persistentInstance);

	/**
	 * 根据实例查询课程
	 * @param instance
	 * @return
	 */
	public List<Course> findByExample(Course instance);

	/**
	 * 根据属性查询课程
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public List<Course> findByProperty(String propertyName, Object value);

	/**
	 * 查询所有课程
	 * @param input 
	 * @param offset 
	 * @param page 
	 * @return
	 */
	public List<Course> findAll(String input, int page, int offset);

	/**
	 * 更新课程
	 * @param instance
	 */
	public void attachDirty(Course instance);
}