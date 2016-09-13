package com.xidian.service;

import java.util.List;

import com.xidian.entity.Application;
import com.xidian.entity.Student;

public interface ApplicationService {

	/**
	 * 添加留校申请
	 * 
	 * @param application
	 */
	void addApplication(Application application);

	/**
	 * 查询留校申请
	 * 
	 * @param offset
	 * @param page
	 * @return
	 */
	List<Application> queryApplication(int page, int offset);

	/**
	 * 取消留校申请
	 * 
	 * @param application
	 */
	void cancelApplication(Application application);

	/**
	 * 根据状态查询所有留校申请
	 * 
	 * @param list
	 * @param states
	 * @param offset
	 * @param page
	 * @return
	 */
	List<Application> queryAllApplication(List<Student> list,
			String[] states, int page, int offset);

	/**
	 * 修改申请信息
	 * 
	 * @param application
	 */
	void updateApplication(Application application);

}
