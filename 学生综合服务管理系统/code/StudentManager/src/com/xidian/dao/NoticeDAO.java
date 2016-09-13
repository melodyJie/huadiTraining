package com.xidian.dao;

import java.util.List;

import com.xidian.entity.Notice;

public interface NoticeDAO {
	/**
	 * 添加公告
	 * @param transientInstance
	 */
	public void save(Notice transientInstance);

	/**
	 * 删除公告
	 * @param persistentInstance
	 */
	public void delete(Notice persistentInstance);

	/**
	 * 根据实例查询公告
	 * @param instance
	 * @return
	 */
	public List<Notice> findByExample(Notice instance);

	/**
	 * 根据属性查询公告
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public List<Notice> findByProperty(String propertyName, Object value);

	/**
	 * 查询所有公告
	 * @return
	 */
	public List<Notice> findAll();

	/**
	 * 更新公告
	 * @param instance
	 */
	public void attachDirty(Notice instance);
}