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

import com.xidian.entity.Fee;

public interface FeeDAO {
	/**
	 * 添加费用
	 * @param transientInstance
	 */
	public void save(Fee transientInstance);

	/**
	 * 删除费用
	 * @param persistentInstance
	 */
	public void delete(Fee persistentInstance);

	/**
	 * 根据实例查询费用
	 * @param instance
	 * @return
	 */
	public List<Fee> findByExample(Fee instance);

	/**
	 * 根据属性查询费用
	 * @param propertyName
	 * @param value
	 * @param offset 
	 * @param page 
	 * @return
	 */
	public List<Fee> findByProperty(String propertyName, Object value,
			int page, int offset);

	/**
	 * 查询所有费用
	 * @return
	 */
	public List<Fee> findAll();

	/**
	 * 更新费用
	 * @param instance
	 */
	public void attachDirty(Fee instance);
}