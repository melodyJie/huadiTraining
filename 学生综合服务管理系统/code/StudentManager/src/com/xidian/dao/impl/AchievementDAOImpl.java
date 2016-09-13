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

import com.xidian.dao.AchievementDAO;
import com.xidian.entity.Achievement;

/**
 * A data access object (DAO) providing persistence and search support for
 * Achievement entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.xidian.dao.impl.Achievement
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class AchievementDAOImpl implements AchievementDAO {
	private static final Logger log = LoggerFactory
			.getLogger(AchievementDAOImpl.class);

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
	public void save(Achievement transientInstance) {
		log.debug("saving Achievement instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void delete(Achievement persistentInstance) {
		log.debug("deleting Achievement instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<Achievement> findByExample(Achievement instance) {
		log.debug("finding Achievement instance by example");
		try {
			List<Achievement> results = (List<Achievement>) getCurrentSession()
					.createCriteria("com.xidian.dao.impl.Achievement")
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
	public List<Achievement> findByProperty(String propertyName, Object value,
			int page, int offset) {
		log.debug("finding Achievement instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Achievement as model where model."
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
	public List<Achievement> findAll() {
		log.debug("finding all Achievement instances");
		try {
			String queryString = "from Achievement";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public void attachDirty(Achievement instance) {
		log.debug("attaching dirty Achievement instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Override
	public List<Achievement> findByProperties(List<String> propertyNames,
			List<Object> values, int page, int offset) {
		try {
			StringBuffer sb = new StringBuffer(
					"from Achievement as model where model."
							+ propertyNames.get(0) + " = ?");
			for (int i = 1; i < propertyNames.size(); i++) {
				sb.append(" and model." + propertyNames.get(i) + " = ?");
			}
			Query queryObject = getCurrentSession().createQuery(sb.toString())
					.setFirstResult((page - 1) * offset).setMaxResults(offset);
			for (int i = 0; i < propertyNames.size(); i++) {
				queryObject.setParameter(i, values.get(i));
			}
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public static AchievementDAOImpl getFromApplicationContext(
			ApplicationContext ctx) {
		return (AchievementDAOImpl) ctx.getBean("AchievementDAO");
	}

}