package com.xidian.service.impl;

import java.util.List;

import com.xidian.dao.TeacherDAO;
import com.xidian.entity.Teacher;
import com.xidian.service.TeacherService;

public class TeacherServiceImpl implements TeacherService {
	private TeacherDAO teacherDAO;

	public void setTeacherDAO(TeacherDAO teacherDAO) {
		this.teacherDAO = teacherDAO;
	}

	@Override
	public List<Teacher> findByProperty(String propertyName, Object value) {
		return teacherDAO.findByProperty(propertyName, value);
	}

	@Override
	public void addTeacher(Teacher teacher) {
		List<Teacher> list = teacherDAO.findAll(1, Integer.MAX_VALUE);
		String s = "";
		if (null != list && list.size() > 0) {
			String str = list.get(list.size() - 1).getTeacherNumber();
			int i = 0;
			for (; i < str.length(); i++){
				if (str.charAt(i) != '0'){
					break;
				}
			}
			int count = Integer.parseInt(str.substring(i));
			s = (count + 1) + "";
			while (s.length() < 6) {
				s = "0" + s;
			}
		} else {
			s = "000001";
		}
		teacher.setTeacherNumber(s);
		String identityId = teacher.getIdentityId();
		teacher.setPassword(identityId.substring(identityId.length() - 6));
		teacherDAO.save(teacher);
	}

	@Override
	public List<Teacher> findAll(int page, int offset) {
		return teacherDAO.findAll(page, offset);
	}

	@Override
	public Teacher queryTeacher(Teacher teacher) {
		List<Teacher> list = teacherDAO.findByExample(teacher);
		if (null != list && list.size() > 0){
			return list.get(0);
		}else {
			return null;
		}
	}

}
