package com.xidian.service.impl;

import java.util.List;

import com.xidian.dao.SpecialtyDAO;
import com.xidian.entity.College;
import com.xidian.entity.Specialty;
import com.xidian.service.SpecialtyService;

public class SpecialtyServiceImpl implements SpecialtyService {
	private SpecialtyDAO specialtyDAO;

	public void setSpecialtyDAO(SpecialtyDAO specialtyDAO) {
		this.specialtyDAO = specialtyDAO;
	}

	@Override
	public Specialty findById(Long specialtyId) {
		return specialtyDAO.findByProperty("specialtyId", specialtyId).get(0);
	}

	@Override
	public List<Specialty> querySpecialty(College college) {
		List<Specialty> list = specialtyDAO.findByProperty("college", college);
		return list;
	}

	@Override
	public List<Specialty> queryAllSpecialty(int page, int offset) {
		List<Specialty> list = specialtyDAO.findAll(page, offset);
		return list;
	}

}
