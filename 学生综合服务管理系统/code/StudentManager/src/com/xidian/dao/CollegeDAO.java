package com.xidian.dao;

import java.util.List;
import com.xidian.entity.College;

public interface CollegeDAO {
	/**
	 * 添加学院
	 * @param transientInstance
	 */
	public void save(College transientInstance);

	/**
	 * 删除学院
	 * @param persistentInstance
	 */
	public void delete(College persistentInstance);

	/**
	 * 根据实例查询学院
	 * @param instance
	 * @return
	 */
	public List<College> findByExample(College instance);

	/**
	 * 根据属性查询学院
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public List<College> findByProperty(String propertyName, Object value);

	/**
	 * 查询所有学院
	 * @return
	 */
	public List<College> findAll();

	/**
	 * 更新学院
	 * @param instance
	 */
	public void attachDirty(College instance);
}