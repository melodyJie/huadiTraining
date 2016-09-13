package com.xidian.dao;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.xidian.entity.Achievement;

public interface AchievementDAO {
	/**
	 * 添加成绩
	 * 
	 * @param transientInstance
	 */
	public void save(Achievement transientInstance);

	/**
	 * 删除成绩
	 * 
	 * @param persistentInstance
	 */
	public void delete(Achievement persistentInstance);

	/**
	 * 根据实例查询成绩
	 * 
	 * @param instance
	 * @return
	 */
	public List<Achievement> findByExample(Achievement instance);

	/**
	 * 根据属性查询成绩
	 * 
	 * @param propertyName
	 * @param value
	 * @param offset
	 * @param page
	 * @return
	 */
	public List<Achievement> findByProperty(String propertyName, Object value,
			int page, int offset);

	/**
	 * 查询所有成绩
	 * 
	 * @return
	 */
	public List<Achievement> findAll();

	/**
	 * 更新成绩
	 * 
	 * @param instance
	 */
	public void attachDirty(Achievement instance);

	/**
	 * 根据某些属性查询成绩
	 * 
	 * @param propertyNames
	 * @param values
	 * @param offset 
	 * @param page 
	 * @return
	 */
	public List<Achievement> findByProperties(List<String> propertyNames,
			List<Object> values, int page, int offset);
}