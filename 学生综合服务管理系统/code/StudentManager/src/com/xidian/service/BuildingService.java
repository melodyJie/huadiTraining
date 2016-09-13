package com.xidian.service;

import java.util.List;

import com.xidian.entity.Building;

public interface BuildingService {

	/**
	 * 查询楼栋
	 * 
	 * @param buildingName
	 * @return
	 */
	List<Building> findByProperty(String propertyName, Object value);

	/**
	 * 查找所有楼栋
	 * 
	 * @return
	 */
	List<Building> queryBuilding();
}
