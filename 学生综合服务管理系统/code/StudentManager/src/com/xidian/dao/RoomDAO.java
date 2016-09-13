package com.xidian.dao;

import java.util.List;

import com.xidian.entity.Building;
import com.xidian.entity.Room;

public interface RoomDAO {
	/**
	 * 添加宿舍
	 * @param transientInstance
	 */
	public void save(Room transientInstance);

	/**
	 * 删除宿舍
	 * @param persistentInstance
	 */
	public void delete(Room persistentInstance);

	/**
	 * 根据实例查询宿舍
	 * @param instance
	 * @return
	 */
	public List<Room> findByExample(Room instance);

	/**
	 * 根据属性查询宿舍
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public List<Room> findByProperty(String propertyName, Object value);

	/**
	 * 查询所有宿舍
	 * @return
	 */
	public List<Room> findAll();
	
	/**
	 * 更新宿舍
	 * @param instance
	 */
	public void attachDirty(Room instance);

	/**
	 * 分页查询
	 * @param list2
	 * @param page
	 * @param offset
	 * @return
	 */
	public List<Room> findByDivide(List<Room> list2, Building building, int page, int offset);
}