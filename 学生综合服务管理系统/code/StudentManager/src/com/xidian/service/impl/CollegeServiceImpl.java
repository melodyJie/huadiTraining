package com.xidian.service.impl;

import java.util.List;

import com.xidian.dao.CollegeDAO;
import com.xidian.entity.College;
import com.xidian.service.CollegeService;

public class CollegeServiceImpl implements CollegeService {
	private CollegeDAO collegeDAO;

	public void setCollegeDAO(CollegeDAO collegeDAO) {
		this.collegeDAO = collegeDAO;
	}

	@Override
	public List<College> queryCollege() {
		List<College> list = collegeDAO.findAll();
		return list;
	}

	@Override
	public College findById(Long collegeId) {
		return collegeDAO.findByProperty("collegeId", collegeId).get(0);
	}
}
