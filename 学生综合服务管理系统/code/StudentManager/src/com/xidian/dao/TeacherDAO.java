package com.xidian.dao;

import java.util.List;

import com.xidian.entity.Teacher;

public interface TeacherDAO {
	/**
	 * 添加教师
	 * @param transientInstance
	 */
	public void save(Teacher transientInstance);

	/**
	 * 删除教师
	 * @param persistentInstance
	 */
	public void delete(Teacher persistentInstance);

	/**
	 * 通过实例查询教师
	 * @param instance
	 * @return
	 */
	public List<Teacher> findByExample(Teacher instance);

	/**
	 * 通过某属性查询教师
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public List<Teacher> findByProperty(String propertyName, Object value);

	/**
	 * 查询所有教师
	 * @param page
	 * @param offset
	 * @return
	 */
	public List<Teacher> findAll(int page, int offset);

	/**
	 * 更新教师
	 * @param instance
	 */
	public void attachDirty(Teacher instance);
}