package com.xidian.dao;

import java.util.List;

import com.xidian.entity.Choosing;
import com.xidian.entity.Student;
import com.xidian.entity.Teach;

public interface ChoosingDAO {
	/**
	 * 添加选课
	 * @param transientInstance
	 */
	public void save(Choosing transientInstance);

	/**
	 * 删除选课
	 * @param persistentInstance
	 */
	public void delete(Choosing persistentInstance);

	/**
	 * 根据实例查询选课
	 * @param instance
	 * @return
	 */
	public List<Choosing> findByExample(Choosing instance);

	/**
	 * 根据属性查询选课
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public List<Choosing> findByProperty(String propertyName, Object value);

	/**
	 * 查询所有选课
	 * @return
	 */
	public List<Choosing> findAll();

	/**
	 * 更新选课
	 * @param instance
	 */
	public void attachDirty(Choosing instance);

	/**
	 * 分页查询
	 * @param students
	 * @param teach
	 * @param offset 
	 * @param page 
	 * @return
	 */
	public List<Choosing> findByDivide(List<Student> students, Teach teach,
			int page, int offset);
}