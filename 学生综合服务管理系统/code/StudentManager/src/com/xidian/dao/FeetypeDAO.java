package com.xidian.dao;

import java.util.List;

import com.xidian.entity.Fee;
import com.xidian.entity.Feetype;

/**
 	* A data access object (DAO) providing persistence and search support for Feetype entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.xidian.dao.impl.Feetype
  * @author MyEclipse Persistence Tools 
 */
   
public interface FeetypeDAO  {
	/**
	 * 添加费用类型
	 * @param transientInstance
	 */
    public void save(Feetype transientInstance);
    
    /**
     * 删除费用类型
     * @param persistentInstance
     */
	public void delete(Feetype persistentInstance);
    
    /**
     * 根据实例查询费用类型
     * @param instance
     * @return
     */
    public List<Feetype> findByExample(Feetype instance);    
    
    /**
     * 根据属性查询费用类型
     * @param propertyName
     * @param value
     * @param offset 
     * @param page 
     * @return
     */
	public List<Feetype> findByProperty(String propertyName, Object value,
			int page, int offset);

	/**
	 * 查询所有费用类型
	 * @return
	 */
	public List<Feetype> findAll();
	
	/**
	 * 更新费用类型
	 * @param instance
	 */
    public void attachDirty(Feetype instance);

    /**
     * 根据某些属性查询费用类型
     * @param propertyNames
     * @param values
     * @return
     */
	public List<Feetype> findByProperties(List<String> propertyNames,
			List<Object> values);

	 /**
	  * 根据费用删除费用类型
	  * @param fee
	  */
	public void deleteFeetype(Fee fee);
}