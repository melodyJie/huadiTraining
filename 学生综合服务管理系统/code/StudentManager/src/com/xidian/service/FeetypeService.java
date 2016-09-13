package com.xidian.service;

import java.util.List;

import com.xidian.data.Data;
import com.xidian.entity.Fee;
import com.xidian.entity.Feetype;

public interface FeetypeService {

	/**
	 * 查询费用类型
	 * @param fee
	 * @param offset 
	 * @param page 
	 * @return
	 */
	List<Data> queryFeetype(Fee fee, int page, int offset);

	/**
	 * 添加费用类型
	 * @param feetype2 
	 */
	void addFeetype(Feetype feetype2);
	
	/**
	 * 根据属性查询费用类型
	 * @param propertyName
	 * @param value
	 * @return
	 */
	List<Feetype> findByProperty(String propertyName, Object value);

	/**
	 * 根据某些属性查询费用类型
	 * @param propertyNames
	 * @param values
	 * @return
	 */
	List<Feetype> findByProperties(List<String> propertyNames,
			List<Object> values);

	/**
	 * 查询学生应交费用
	 * @param value
	 * @return
	 */
	List<Feetype> queryStudentFeetype(String value);

	/**
	 * 根据费用删除费用类型
	 * @param fee
	 */
	void deleteFeetype(Fee fee);

}
