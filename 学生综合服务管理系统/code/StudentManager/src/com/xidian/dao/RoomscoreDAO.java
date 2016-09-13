package com.xidian.dao;

import java.util.Date;
import java.util.List;

import com.xidian.entity.Room;
import com.xidian.entity.Roomscore;

public interface RoomscoreDAO {
	/**
	 * 添加宿舍评分
	 * 
	 * @param transientInstance
	 */
	public void save(Roomscore transientInstance);

	/**
	 * 删除宿舍评分
	 * 
	 * @param persistentInstance
	 */
	public void delete(Roomscore persistentInstance);

	/**
	 * 根据实例查询宿舍评分
	 * 
	 * @param instance
	 * @return
	 */
	public List<Roomscore> findByExample(Roomscore instance);

	/**
	 * 根据属性查询宿舍评分
	 * 
	 * @param propertyName
	 * @param value
	 * @param offset
	 * @param page
	 * @return
	 */
	public List<Roomscore> findByProperty(String propertyName, Object value,
			int page, int offset);

	/**
	 * 查询所有宿舍评分
	 * 
	 * @return
	 */
	public List<Roomscore> findAll();

	/**
	 * 更新宿舍评分
	 * 
	 * @param instance
	 */
	public void attachDirty(Roomscore instance);

	/**
	 * 分页查询
	 * 
	 * @param list
	 * @param date
	 * @param page
	 * @param offset
	 * @return
	 */
	public List<Roomscore> findByDivide(List<Room> list, Date date, int page,
			int offset);
}