package com.xidian.service;

import java.util.List;

import com.xidian.entity.Teacher;

public interface TeacherService {

	/**
	 * 通过属性查找教师
	 * @param propertyName
	 * @param value
	 * @return
	 */
	List<Teacher> findByProperty(String propertyName, Object value);

	/**
	 * 添加教师
	 * @param teacher
	 */
	void addTeacher(Teacher teacher);

	/**
	 *查询所有教师
	 * @param offset 
	 * @param page 
	 * @return
	 */
	List<Teacher> findAll(int page, int offset);

	/**
	 * 查询教师
	 * @param teacher
	 * @return
	 */
	Teacher queryTeacher(Teacher teacher);

}
