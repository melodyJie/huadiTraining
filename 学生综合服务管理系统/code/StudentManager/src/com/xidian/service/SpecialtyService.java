package com.xidian.service;

import java.util.List;

import com.xidian.entity.College;
import com.xidian.entity.Specialty;

public interface SpecialtyService {

	/**
	 * 根据id查询专业
	 * @param specialtyId
	 * @return
	 */
	Specialty findById(Long specialtyId);

	/**
	 * 根据学院查询专业
	 * @param college
	 * @return
	 */
	List<Specialty> querySpecialty(College college);

	/**
	 * 查询所有专业
	 * @param page
	 * @param offset
	 * @return
	 */
	List<Specialty> queryAllSpecialty(int page, int offset);

}
