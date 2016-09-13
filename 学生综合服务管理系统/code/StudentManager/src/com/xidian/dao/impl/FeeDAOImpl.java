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

import com.xidian.dao.FeeDAO;
import com.xidian.entity.Fee;

/**
 * A data access object (DAO) providing persistence and search support for Fee
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.xidian.dao.impl.Fee
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class FeeDAOImpl implements FeeDAO {
	private static final Logger log = LoggerFactory.getLogger(FeeDAOImpl.class);

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
	public void save(Fee transientInstance) {
		log.debug("saving Fee instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void delete(Fee persistentInstance) {
		log.debug("deleting Fee instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<Fee> findByExample(Fee instance) {
		log.debug("finding Fee instance by example");
		try {
			List<Fee> results = (List<Fee>) getCurrentSession()
					.createCriteria("com.xidian.entity.Fee")
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
	public List<Fee> findByProperty(String propertyName, Object value,
			int page, int offset) {
		log.debug("finding Fee instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Fee as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString)
					.setFirstResult((page - 1) * offset).setMaxResults(offset);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	@Override
	public List<Fee> findAll() {
		log.debug("finding all Fee instances");
		try {
			String queryString = "from Fee";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public void attachDirty(Fee instance) {
		log.debug("attaching dirty Fee instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static FeeDAOImpl getFromApplicationContext(ApplicationContext ctx) {
		return (FeeDAOImpl) ctx.getBean("FeeDAO");
	}
}