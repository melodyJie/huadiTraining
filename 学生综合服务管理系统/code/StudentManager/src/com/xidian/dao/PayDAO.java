package com.xidian.dao;

import java.util.List;

import com.xidian.entity.Pay;

public interface PayDAO {
	/**
	 * 添加缴费
	 * @param transientInstance
	 */
	public void save(Pay transientInstance);

	/**
	 * 删除缴费
	 * @param persistentInstance
	 */
	public void delete(Pay persistentInstance);

	/**
	 * 根据实例查询缴费
	 * @param instance
	 * @return
	 */
	public List<Pay> findByExample(Pay instance);

	/**
	 * 根据属性查询缴费
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public List<Pay> findByProperty(String propertyName, Object value);

	/**
	 * 查询所有缴费
	 * @return
	 */
	public List<Pay> findAll();

	/**
	 * 更新缴费
	 * @param instance
	 */
	public void attachDirty(Pay instance);
}