package com.xidian.service;

import java.util.List;

import com.xidian.entity.Maintenance;
import com.xidian.entity.Room;
import com.xidian.entity.Student;

public interface MaintenanceService {

	/**
	 * 添加维护申请
	 * 
	 * @param maintenance
	 */
	void addMaintenance(Maintenance maintenance);

	/**
	 * 查看维修进度
	 * @param student 
	 * 
	 * @param offset
	 * @param page
	 * @return
	 */
	List<Maintenance> queryMaintenance(Student student, int page, int offset);

	/**
	 * 取消维护
	 * 
	 * @param maintenance
	 */
	void cancelMaintenance(Maintenance maintenance);

	/**
	 * 查询所有维修记录
	 * 
	 * @param list
	 * @param states
	 * @param offset
	 * @param page
	 * @return
	 */
	List<Maintenance> queryAllMaintenance(List<Room> list, String[] states,
			int page, int offset);

	/**
	 * 更新维护信息
	 * 
	 * @param maintenance
	 */
	void updateMaintenance(Maintenance maintenance);

}
