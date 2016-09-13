package com.xidian.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.xidian.dao.AchievementDAO;
import com.xidian.entity.Achievement;
import com.xidian.entity.Student;
import com.xidian.service.AchievementService;

public class AchievementServiceImpl implements AchievementService {
	private AchievementDAO achievementDAO;

	public void setAchievementDAO(AchievementDAO achievementDAO) {
		this.achievementDAO = achievementDAO;
	}

	@Override
	public List<Achievement> queryNowGrade(int page, int offset) {
		Student student = (Student) ServletActionContext.getRequest()
				.getSession().getAttribute("user");
		int year = Integer.parseInt(student.getYear());
		Calendar calendar = Calendar.getInstance();
		int month = calendar.get(Calendar.MONTH) + 1;
		int nowYear = calendar.get(Calendar.YEAR);
		int grade = month >= 9 ? (nowYear - year + 1) : (nowYear - year);
		int term;
		if (month >= 3 && month < 9) {
			term = grade * 2;
		} else {
			term = grade * 2 - 1;
		}
		List<String> propertyNames = new ArrayList<String>();
		List<Object> values = new ArrayList<Object>();
		propertyNames.add("student");
		propertyNames.add("term");
		values.add(student);
		values.add(term);
		List<Achievement> list = achievementDAO.findByProperties(propertyNames,
				values, page, offset);
		return list;
	}

	@Override
	public List<Achievement> queryAllGrade(int page, int offset) {
		Student student = (Student) ServletActionContext.getRequest()
				.getSession().getAttribute("user");
		List<Achievement> list = achievementDAO.findByProperty("student",
				student, page, offset);
		return list;
	}

	@Override
	public void addAchievement(Achievement achievement) {
		if (null == achievement.getTerm()) {
			int year = Integer.parseInt(achievement.getStudent().getYear());
			Calendar calendar = Calendar.getInstance();
			int month = calendar.get(Calendar.MONTH) + 1;
			int nowYear = calendar.get(Calendar.YEAR);
			int grade = month >= 9 ? (nowYear - year + 1) : (nowYear - year);
			int term;
			if (month >= 3 && month < 9) {
				term = grade * 2;
			} else {
				term = grade * 2 - 1;
			}
			achievement.setTerm(term);
		}
		achievementDAO.save(achievement);
	}

	@Override
	public List<Achievement> queryAchievement(Achievement achievement,
			String specialtyName, int page, int offset) {
		List<String> propertyNames = new ArrayList<String>();
		propertyNames.add("course");
		propertyNames.add("teacher");
		List<Object> values = new ArrayList<Object>();
		values.add(achievement.getCourse());
		values.add(achievement.getTeacher());
		if (null != specialtyName) {
			propertyNames.add("student.studentclass.specialty.specialtyName");
			values.add(specialtyName);
		}
		if (null != achievement.getTerm()) {
			propertyNames.add("term");
			values.add(achievement.getTerm());
		}
		List<Achievement> list = achievementDAO.findByProperties(propertyNames,
				values, page, offset);
		return list;
	}

	@Override
	public void updateAchievement(Achievement achievement) {
		Achievement achievement2 = achievementDAO.findByProperty(
				"achievementId", achievement.getAchievementId(), 1,
				Integer.MAX_VALUE).get(0);
		achievement2.setScore(achievement.getScore());
		achievementDAO.attachDirty(achievement2);
	}
}
