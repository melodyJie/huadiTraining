package com.xidian.dao;

import java.util.List;

import com.xidian.entity.Specialtycourse;
import com.xidian.entity.Teach;

public interface SpecialtycourseDAO {
	/**
	 * 添加专业课程
	 * @param transientInstance
	 */
	public void save(Specialtycourse transientInstance);

	/**
	 * 删除专业课程
	 * @param persistentInstance
	 */
	public void delete(Specialtycourse persistentInstance);

	/**
	 * 根据实例查询专业课程
	 * @param instance
	 * @return
	 */
	public List<Specialtycourse> findByExample(Specialtycourse instance);

	/**
	 * 根据属性查询专业课程
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public List<Specialtycourse> findByProperty(String propertyName, Object value);

	/**
	 * 查询所有专业课程
	 * @return
	 */
	public List<Specialtycourse> findAll();

	/**
	 * 更新专业课程
	 * @param instance
	 */
	public void attachDirty(Specialtycourse instance);

	/**
	 * 根据某些属性查询专业课程
	 * @param propertyNames
	 * @param values
	 * @return
	 */
	public List<Specialtycourse> findByProperties(List<String> propertyNames,
			List<Object> values);

	/**
	 * 根据授课查询所有专业课程
	 * @param teaches
	 * @param page
	 * @param offset
	 * @return
	 */
	public List<Specialtycourse> findByTeaches(List<Teach> teaches, int page,
			int offset);
}