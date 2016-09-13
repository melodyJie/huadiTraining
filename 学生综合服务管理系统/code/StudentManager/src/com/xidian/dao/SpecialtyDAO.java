package com.xidian.dao;

import java.util.List;

import com.xidian.entity.Specialty;

public interface SpecialtyDAO {
	/**
	 * 添加专业
	 * @param transientInstance
	 */
	public void save(Specialty transientInstance);

	/**
	 * 删除专业
	 * @param persistentInstance
	 */
	public void delete(Specialty persistentInstance);

	/**
	 * 根据实例查询专业
	 * @param instance
	 * @return
	 */
	public List<Specialty> findByExample(Specialty instance);

	/**
	 * 根据属性查询专业
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public List<Specialty> findByProperty(String propertyName, Object value);

	/**
	 * 查询所有专业
	 * @param offset 
	 * @param page 
	 * @return
	 */
	public List<Specialty> findAll(int page, int offset);

	/**
	 * 更新专业
	 * @param instance
	 */
	public void attachDirty(Specialty instance);
}