package com.xidian.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.xidian.dao.PayDAO;
import com.xidian.entity.Feetype;
import com.xidian.entity.Pay;
import com.xidian.entity.Student;
import com.xidian.service.PayService;

public class PayServiceImpl implements PayService {
	private PayDAO payDAO;
	
	public void setPayDAO(PayDAO payDAO) {
		this.payDAO = payDAO;
	}

	@Override
	public List<Pay> queryPay() {
		Student student = (Student) ServletActionContext.getRequest()
				.getSession().getAttribute("user");
		List<Pay> list = payDAO.findByProperty("student", student);
		return list;
	}

	@Override
	public void addPay(Feetype feetype) {
		Student student = (Student) ServletActionContext.getRequest()
				.getSession().getAttribute("user");
		Pay pay = new Pay();
		pay.setFeetype(feetype);
		pay.setStudent(student);
		pay.setValue(feetype.getValue());
		pay.setPayDate(new Date());
		payDAO.save(pay);
	}

	@Override
	public List<Pay> findPay(Student student) {
		return payDAO.findByProperty("student", student);
	}
}
