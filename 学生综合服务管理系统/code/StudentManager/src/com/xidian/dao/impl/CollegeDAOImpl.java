package com.xidian.dao.impl;

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

import com.xidian.dao.CollegeDAO;
import com.xidian.entity.College;

/**
 * A data access object (DAO) providing persistence and search support for
 * College entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.xidian.dao.impl.College
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class CollegeDAOImpl implements CollegeDAO {
	private static final Logger log = LoggerFactory
			.getLogger(CollegeDAOImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	protected void initDao() {
		// do nothing
	}

	@Override
	public void save(College transientInstance) {
		log.debug("saving College instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void delete(College persistentInstance) {
		log.debug("deleting College instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<College> findByExample(College instance) {
		log.debug("finding College instance by example");
		try {
			List<College> results = (List<College>) getCurrentSession()
					.createCriteria("com.xidian.entity.College")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	@Override
	public List<College> findByProperty(String propertyName, Object value) {
		log.debug("finding College instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from College as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	@Override
	public List<College> findAll() {
		log.debug("finding all College instances");
		try {
			String queryString = "from College";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public void attachDirty(College instance) {
		log.debug("attaching dirty College instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static CollegeDAOImpl getFromApplicationContext(
			ApplicationContext ctx) {
		return (CollegeDAOImpl) ctx.getBean("CollegeDAO");
	}
}