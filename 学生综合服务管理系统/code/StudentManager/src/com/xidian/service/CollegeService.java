package com.xidian.service;

import java.util.List;

import com.xidian.entity.College;

public interface CollegeService {

	/**
	 * 查询所有学院
	 * @return
	 */
	List<College> queryCollege();

	/**
	 * 查询学院
	 * @param collegeId
	 * @return
	 */
	College findById(Long collegeId);

}
