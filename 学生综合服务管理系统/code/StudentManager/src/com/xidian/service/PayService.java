package com.xidian.service;

import java.util.List;

import com.xidian.entity.Feetype;
import com.xidian.entity.Pay;
import com.xidian.entity.Student;

public interface PayService {

	/**
	 * 查询缴费记录
	 * @return
	 */
	List<Pay> queryPay();

	/**
	 * 添加缴费记录
	 * @param feetype
	 */
	void addPay(Feetype feetype);

	/**
	 * 查询缴费费用
	 * @param student
	 * @return
	 */
	List<Pay> findPay(Student student);
}
