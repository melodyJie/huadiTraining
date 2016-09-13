package com.xidian.dao.impl;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import static org.hibernate.criterion.Example.create;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.xidian.dao.FeetypeDAO;
import com.xidian.entity.Fee;
import com.xidian.entity.Feetype;

/**
 * A data access object (DAO) providing persistence and search support for
 * Feetype entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.xidian.dao.impl.Feetype
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class FeetypeDAOImpl implements FeetypeDAO {
	private static final Logger log = LoggerFactory
			.getLogger(FeetypeDAOImpl.class);

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
	public void save(Feetype transientInstance) {
		log.debug("saving Feetype instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void delete(Feetype persistentInstance) {
		log.debug("deleting Feetype instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<Feetype> findByExample(Feetype instance) {
		log.debug("finding Feetype instance by example");
		try {
			List<Feetype> results = (List<Feetype>) getCurrentSession()
					.createCriteria("com.xidian.dao.impl.Feetype")
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
	public List<Feetype> findByProperty(String propertyName, Object value,
			int page, int offset) {
		log.debug("finding Feetype instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Feetype as model where model."
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
	public List<Feetype> findAll() {
		log.debug("finding all Feetype instances");
		try {
			String queryString = "from Feetype";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public void attachDirty(Feetype instance) {
		log.debug("attaching dirty Feetype instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static FeetypeDAOImpl getFromApplicationContext(
			ApplicationContext ctx) {
		return (FeetypeDAOImpl) ctx.getBean("FeetypeDAO");
	}

	@Override
	public List<Feetype> findByProperties(List<String> propertyNames,
			List<Object> values) {
		try {
			StringBuffer sb = new StringBuffer(
					"from Feetype as model where model." + propertyNames.get(0)
							+ " = ?");
			for (int i = 1; i < propertyNames.size(); i++) {
				sb.append(" and model." + propertyNames.get(i) + " = ?");
			}
			Query queryObject = getCurrentSession().createQuery(sb.toString())
					.setFirstResult(0).setMaxResults(10);
			for (int i = 0; i < propertyNames.size(); i++) {
				queryObject.setParameter(i, values.get(i));
			}
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	@Override
	public void deleteFeetype(Fee fee) {
		try {
			String queryString = "delete from Feetype as model where model.fee = ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, fee);
			queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
}