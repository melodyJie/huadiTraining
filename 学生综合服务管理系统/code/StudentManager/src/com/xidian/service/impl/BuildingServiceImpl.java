package com.xidian.service.impl;

import java.util.List;

import com.xidian.dao.BuildingDAO;
import com.xidian.entity.Building;
import com.xidian.service.BuildingService;

public class BuildingServiceImpl implements BuildingService {
	private BuildingDAO buildingDAO;

	public void setBuildingDAO(BuildingDAO buildingDAO) {
		this.buildingDAO = buildingDAO;
	}

	@Override
	public List<Building> findByProperty(String propertyName, Object value) {
		return buildingDAO.findByProperty(propertyName, value);
	}

	@Override
	public List<Building> queryBuilding() {
		List<Building> list = buildingDAO.findAll();
//		StringBuffer sb = new StringBuffer();
//		for (Building building : list) {
//			String buildingName = building.getBuildingName();
//			sb.append(buildingName).append(" ");
//		}
//		return sb.toString().trim();
		return list;
	}
}
