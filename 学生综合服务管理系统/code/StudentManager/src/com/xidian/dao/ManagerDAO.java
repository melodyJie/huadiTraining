package com.xidian.dao;

import java.util.List;
import com.xidian.entity.Manager;

public interface ManagerDAO {
	/**
	 * 添加管理员
	 * @param transientInstance
	 */
	public void save(Manager transientInstance);
	
	/**
	 * 删除管理员
	 * @param persistentInstance
	 */
	public void delete(Manager persistentInstance);

	/**
	 * 根据实例查询管理员
	 * @param instance
	 * @return
	 */
	public List<Manager> findByExample(Manager instance);

	/**
	 * 根据属性查询管理员
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public List<Manager> findByProperty(String propertyName, Object value);
	
	/**
	 * 查询所有管理员
	 * @return
	 */
	public List<Manager> findAll();

	/**
	 * 更新管理员
	 * @param instance
	 */
	public void attachDirty(Manager instance);
}