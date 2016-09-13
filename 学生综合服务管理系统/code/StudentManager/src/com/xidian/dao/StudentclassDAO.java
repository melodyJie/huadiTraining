package com.xidian.dao;

import java.util.List;

import com.xidian.entity.Specialty;
import com.xidian.entity.Studentclass;

public interface StudentclassDAO {
	/**
	 * 添加班级
	 * @param transientInstance
	 */
	public void save(Studentclass transientInstance);

	/**
	 * 删除班级
	 * @param persistentInstance
	 */
	public void delete(Studentclass persistentInstance);

	/**
	 * 根据实例查询班级
	 * @param instance
	 * @return
	 */
	public List<Studentclass> findByExample(Studentclass instance);

	/**
	 * 根据属性查询班级
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public List<Studentclass> findByProperty(String propertyName, Object value);

	/**
	 * 查询所有班级
	 * @return
	 */
	public List<Studentclass> findAll();

	/**
	 * 更新班级
	 * @param instance
	 */
	public void attachDirty(Studentclass instance);

	/**
	 * 根据某些条件查询班级
	 * @param list
	 * @return
	 */
	public List<Studentclass> findByProperties(List<String> list);

	/**
	 * 根据专业和年份查询班级
	 * @param specialty
	 * @param year
	 * @return
	 */
	public List<Studentclass> findBySpecialtyYear(Specialty specialty,
			String year);
}