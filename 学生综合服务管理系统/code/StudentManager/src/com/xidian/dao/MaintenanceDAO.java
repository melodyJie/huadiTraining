package com.xidian.dao;

import java.util.List;

import com.xidian.entity.Maintenance;
import com.xidian.entity.Room;

public interface MaintenanceDAO {
	/**
	 * 添加维修
	 * 
	 * @param transientInstance
	 */
	public void save(Maintenance transientInstance);

	/**
	 * 删除维修
	 * 
	 * @param persistentInstance
	 */
	public void delete(Maintenance persistentInstance);

	/**
	 * 根据实例查询维修
	 * 
	 * @param instance
	 * @return
	 */
	public List<Maintenance> findByExample(Maintenance instance);

	/**
	 * 根据属性查询维修
	 * 
	 * @param propertyName
	 * @param value
	 * @param offset
	 * @param page
	 * @return
	 */
	public List<Maintenance> findByProperty(String propertyName, Object value,
			int page, int offset);

	/**
	 * 查询所有维修
	 * 
	 * @return
	 */
	public List<Maintenance> findAll();

	/**
	 * 更新维修
	 * 
	 * @param instance
	 */
	public void attachDirty(Maintenance instance);

	/**
	 * 分页查询
	 * 
	 * @param list
	 * @param states
	 * @param page
	 * @param offset 
	 * @return
	 */
	public List<Maintenance> findByDivide(List<Room> list, String[] states,
			int page, int offset);
}