package com.xidian.dao;

import java.util.List;

import com.xidian.entity.Application;
import com.xidian.entity.Student;

public interface ApplicationDAO {
	/**
	 * 添加申请
	 * 
	 * @param transientInstance
	 */
	public void save(Application transientInstance);

	/**
	 * 删除申请
	 * 
	 * @param persistentInstance
	 */
	public void delete(Application persistentInstance);

	/**
	 * 根据实例查询申请
	 * 
	 * @param instance
	 * @return
	 */
	public List<Application> findByExample(Application instance);

	/**
	 * 根据属性查询申请
	 * 
	 * @param propertyName
	 * @param value
	 * @param offset
	 * @param page
	 * @return
	 */
	public List<Application> findByProperty(String propertyName, Object value,
			int page, int offset);

	/**
	 * 查询所有申请
	 * 
	 * @return
	 */
	public List<Application> findAll();

	/**
	 * 更新申请
	 * 
	 * @param instance
	 */
	public void attachDirty(Application instance);

	/**
	 * 分页查询
	 * 
	 * @param list
	 * @param states
	 * @param page
	 * @param offset 
	 * @return
	 */
	public List<Application> findByDivide(List<Student> list, String[] states,
			int page, int offset);
}