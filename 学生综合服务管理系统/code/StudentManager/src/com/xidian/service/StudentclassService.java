package com.xidian.service;

import java.util.List;

import com.xidian.entity.Specialty;
import com.xidian.entity.Studentclass;

public interface StudentclassService {
	
	/**
	 * 根据属性查询班级
	 * @param propertyName
	 * @param value
	 * @return
	 */
	List<Studentclass> findByProperty(String propertyName, Object value);

	/**
	 * 查询所有班级
	 * @return
	 */
	List<Studentclass> queryAllClass();

	/**
	 * 根据专业和年份查询班级
	 * @param specialty
	 * @param grade
	 * @return
	 */
	List<Studentclass> findBySpecialtyYear(Specialty specialty, int grade);

}
