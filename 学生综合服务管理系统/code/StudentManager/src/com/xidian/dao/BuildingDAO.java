package com.xidian.dao;

import java.util.List;
import com.xidian.entity.Building;

public interface BuildingDAO {
	/**
	 * 添加楼栋
	 * @param transientInstance
	 */
	public void save(Building transientInstance);

	/**
	 * 删除楼栋
	 * @param persistentInstance
	 */
	public void delete(Building persistentInstance);
	
	/**
	 * 根据实例查询楼栋
	 * @param instance
	 * @return
	 */
	public List<Building> findByExample(Building instance);

	/**
	 * 根据属性查询楼栋
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public List<Building> findByProperty(String propertyName, Object value);

	/**
	 * 查询所有楼栋
	 * @return
	 */
	public List findAll();

	/**
	 * 更新楼栋
	 * @param instance
	 */
	public void attachDirty(Building instance);
}