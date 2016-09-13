package com.xidian.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.xidian.dao.StudentclassDAO;
import com.xidian.entity.Specialty;
import com.xidian.entity.Studentclass;
import com.xidian.service.StudentclassService;

public class StudentclassServiceImpl implements StudentclassService {
	private StudentclassDAO studentclassDAO;

	public void setStudentclassDAO(StudentclassDAO studentclassDAO) {
		this.studentclassDAO = studentclassDAO;
	}

	@Override
	public List<Studentclass> findByProperty(String propertyName, Object value) {
		return studentclassDAO.findByProperty(propertyName, value);
	}

	@Override
	public List<Studentclass> queryAllClass() {
		Calendar calendar = Calendar.getInstance();
		List<String> list = new ArrayList<String>();
		int month = calendar.get(Calendar.MONTH) + 1;
		int year = calendar.get(Calendar.YEAR);
		if (month >= 7) {
			for (int i = year - 3; i <= year; i++) {
				String str = (i + "").substring(2);
				list.add(str);
			}
		} else {
			for (int i = year - 4; i < year; i++) {
				String str = (i + "").substring(2);
				list.add(str);
			}
		}
		List<Studentclass> list2 = studentclassDAO.findByProperties(list);
		return list2;
	}

	@Override
	public List<Studentclass> findBySpecialtyYear(Specialty specialty,
			int grade) {
		Calendar calendar = Calendar.getInstance();
		int month = calendar.get(Calendar.MONTH) + 1;
		int nowYear = calendar.get(Calendar.YEAR);
		int year = month >= 9 ? (nowYear - grade + 1) : (nowYear - grade);
		List<Studentclass> list = studentclassDAO.findBySpecialtyYear(
				specialty, (year + "").substring(2));
		return list;
	}

}
