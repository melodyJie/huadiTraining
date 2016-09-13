package com.xidian.dao;

import java.util.List;

import com.xidian.entity.Student;
import com.xidian.entity.Studentclass;

public interface StudentDAO {
	/**
	 * 添加学生
	 * 
	 * @param transientInstance
	 */
	public void save(Student transientInstance);

	/**
	 * 删除学生
	 * 
	 * @param persistentInstance
	 */
	public void delete(Student persistentInstance);

	/**
	 * 根据实例查询学生
	 * 
	 * @param instance
	 * @return
	 */
	public List<Student> findByExample(Student instance);

	/**
	 * 根据属性查询学生
	 * 
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public List<Student> findByProperty(String propertyName, Object value);

	/**
	 * 查询所有学生
	 * 
	 * @return
	 */
	public List<Student> findAll();

	/**
	 * 更新学生
	 * 
	 * @param instance
	 */
	public void attachDirty(Student instance);

	/**
	 * 分页查询
	 * 
	 * @param parameters
	 * @param students 
	 * @param year
	 * @param page
	 * @param offset
	 * @return
	 */
	public List<Student> findByDivide(List<Studentclass> parameters,
			List<Student> students, String year, int page, int offset);

	/**
	 * 根据某些属性查询学生
	 * 
	 * @param propertyNames
	 * @param values
	 * @return
	 */
	public List<Student> findByProperties(List<String> propertyNames,
			List<Object> values);
}