package com.xidian.dao;

import java.util.List;

import com.xidian.entity.Register;

public interface RegisterDAO {
	/**
	 * 添加注册信息
	 * @param transientInstance
	 */
	public void save(Register transientInstance);

	/**
	 * 删除注册信息
	 * @param persistentInstance
	 */
	public void delete(Register persistentInstance);

	/**
	 * 根据实例查询注册信息
	 * @param instance
	 * @return
	 */
	public List<Register> findByExample(Register instance);

	/**
	 * 根据属性查询注册信息
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public List<Register> findByProperty(String propertyName, Object value);

	/**
	 * 查询所有注册信息
	 * @return
	 */
	public List<Register> findAll();

	/**
	 * 更新注册信息
	 * @param instance
	 */
	public void attachDirty(Register instance);
}