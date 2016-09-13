package com.xidian.service.impl;

import java.util.List;

import com.xidian.dao.ManagerDAO;
import com.xidian.entity.Manager;
import com.xidian.service.ManagerService;

public class ManagerServiceImpl implements ManagerService {
	private ManagerDAO managerDAO;
	
	public void setManagerDAO(ManagerDAO managerDAO) {
		this.managerDAO = managerDAO;
	}

	@Override
	public Manager queryManager(Manager m) {
		List<Manager> list = managerDAO.findByExample(m);
		if (list != null && list.size() > 0){
			return list.get(0);
		}
			return null;
	}
}
