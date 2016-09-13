package com.xidian.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.xidian.dao.MaintenanceDAO;
import com.xidian.entity.Maintenance;
import com.xidian.entity.Room;
import com.xidian.entity.Student;
import com.xidian.service.MaintenanceService;

public class MaintenanceServiceImpl implements MaintenanceService {
	private MaintenanceDAO maintenanceDAO;

	public void setMaintenanceDAO(MaintenanceDAO maintenanceDAO) {
		this.maintenanceDAO = maintenanceDAO;
	}

	@Override
	public void addMaintenance(Maintenance maintenance) {
		maintenance.setMaintenanceDate(new Date());
		maintenance.setState("审核中");
		maintenanceDAO.save(maintenance);
	}

	@Override
	public List<Maintenance> queryMaintenance(Student student, int page, int offset) {
		List<Maintenance> list = maintenanceDAO.findByProperty("room",
				student.getRoom(), page, offset);
		return list;
	}

	@Override
	public void cancelMaintenance(Maintenance maintenance) {
		maintenanceDAO.delete(maintenance);
	}

	@Override
	public List<Maintenance> queryAllMaintenance(List<Room> list,
			String[] states, int page, int offset) {
		List<Maintenance> list2 = maintenanceDAO.findByDivide(list, states,
				page, offset);
		return list2;
	}

	@Override
	public void updateMaintenance(Maintenance maintenance) {
		Maintenance m = maintenanceDAO.findByProperty("maintenanceId",
				maintenance.getMaintenanceId(), 1, Integer.MAX_VALUE).get(0);
		m.setState(maintenance.getState());
		maintenanceDAO.attachDirty(m);
	}
}
