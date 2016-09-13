package com.xidian.service;

import java.util.List;

import com.xidian.entity.Achievement;

public interface AchievementService {

	/**
	 * 查询本学期成绩
	 * 
	 * @param offset
	 * @param page
	 * @return
	 */
	List<Achievement> queryNowGrade(int page, int offset);

	/**
	 * 查询全部成绩
	 * 
	 * @param offset
	 * @param page
	 * @return
	 */
	List<Achievement> queryAllGrade(int page, int offset);

	/**
	 * 添加成绩
	 * 
	 * @param achievement
	 */
	void addAchievement(Achievement achievement);

	/**
	 * 根据教师课程查看成绩
	 * 
	 * @param achievement
	 * @param specialtyName 
	 * @param offset
	 * @param page
	 * @return
	 */
	List<Achievement> queryAchievement(Achievement achievement, String specialtyName, int page,
			int offset);

	/**
	 * 修改成绩
	 * 
	 * @param achievement
	 */
	void updateAchievement(Achievement achievement);

}
