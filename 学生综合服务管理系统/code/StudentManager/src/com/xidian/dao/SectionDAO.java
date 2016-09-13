package com.xidian.dao;

import java.util.List;

import com.xidian.entity.Section;

public interface SectionDAO {
	/**
	 * 添加片区
	 * @param transientInstance
	 */
	public void save(Section transientInstance);

	/**
	 * 删除片区
	 * @param persistentInstance
	 */
	public void delete(Section persistentInstance);

	/**
	 * 根据实例查询片区
	 * @param instance
	 * @return
	 */
	public List<Section> findByExample(Section instance);

	/**
	 * 根据属性查询片区
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public List<Section> findByProperty(String propertyName, Object value);

	/**
	 * 查询所有片区
	 * @return
	 */
	public List<Section> findAll();

	/**
	 * 更新片区
	 * @param instance
	 */
	public void attachDirty(Section instance);
}