package com.xidian.service.impl;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.xidian.dao.ApplicationDAO;
import com.xidian.entity.Application;
import com.xidian.entity.Student;
import com.xidian.service.ApplicationService;

public class ApplicationServiceImpl implements ApplicationService {
	private ApplicationDAO applicationDAO;

	public void setApplicationDAO(ApplicationDAO applicationDAO) {
		this.applicationDAO = applicationDAO;
	}

	@Override
	public void addApplication(Application application) {
		Student student = (Student) ServletActionContext.getRequest()
				.getSession().getAttribute("user");
		application.setStudent(student);
		application.setState("审核中");
		applicationDAO.save(application);
	}

	@Override
	public List<Application> queryApplication(int page, int offset) {
		Student student = (Student) ServletActionContext.getRequest()
				.getSession().getAttribute("user");
		List<Application> list = applicationDAO.findByProperty("student",
				student, page, offset);
		return list;
	}

	@Override
	public void cancelApplication(Application application) {
		applicationDAO.delete(application);
	}

	@Override
	public List<Application> queryAllApplication(List<Student> list,
			String[] states, int page, int offset) {
		List<Application> list2 = applicationDAO.findByDivide(list, states,
				page, offset);
		return list2;
	}

	@Override
	public void updateApplication(Application application) {
		Application a = applicationDAO.findByProperty("applicationId",
				application.getApplicationId(), 1, Integer.MAX_VALUE).get(0);
		a.setState(application.getState());
		applicationDAO.attachDirty(a);
	}
}
