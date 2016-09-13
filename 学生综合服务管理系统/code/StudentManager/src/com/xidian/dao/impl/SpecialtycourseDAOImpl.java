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

import com.xidian.dao.SpecialtycourseDAO;
import com.xidian.entity.Specialtycourse;
import com.xidian.entity.Teach;

/**
 * A data access object (DAO) providing persistence and search support for
 * Specialtycourse entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.xidian.dao.impl.Specialtycourse
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class SpecialtycourseDAOImpl implements SpecialtycourseDAO {
	private static final Logger log = LoggerFactory
			.getLogger(SpecialtycourseDAOImpl.class);

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
	public void save(Specialtycourse transientInstance) {
		log.debug("saving Specialtycourse instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void delete(Specialtycourse persistentInstance) {
		log.debug("deleting Specialtycourse instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<Specialtycourse> findByExample(Specialtycourse instance) {
		log.debug("finding Specialtycourse instance by example");
		try {
			List<Specialtycourse> results = (List<Specialtycourse>) getCurrentSession()
					.createCriteria("com.xidian.entity.Specialtycourse")
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
	public List<Specialtycourse> findByProperty(String propertyName,
			Object value) {
		log.debug("finding Specialtycourse instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Specialtycourse as model where model."
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
	public List<Specialtycourse> findAll() {
		log.debug("finding all Specialtycourse instances");
		try {
			String queryString = "from Specialtycourse";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public void attachDirty(Specialtycourse instance) {
		log.debug("attaching dirty Specialtycourse instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Override
	public List<Specialtycourse> findByProperties(List<String> propertyNames,
			List<Object> values) {
		try {
			StringBuffer sb = new StringBuffer(
					"from Specialtycourse as model where model."
							+ propertyNames.get(0) + " = ?");
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
	public List<Specialtycourse> findByTeaches(List<Teach> teaches, int page,
			int offset) {
		try {
			StringBuffer sb = new StringBuffer(
					"from Specialtycourse as model where model.teach = ?");
			for (int i = 1; i < teaches.size(); i++) {
				sb.append(" or model.teach = ?");
			}
			Query queryObject = getCurrentSession().createQuery(sb.toString())
					.setFirstResult((page - 1) * offset).setMaxResults(offset);
			for (int i = 0; i < teaches.size(); i++) {
				queryObject.setParameter(i, teaches.get(i));
			}
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public static SpecialtycourseDAOImpl getFromApplicationContext(
			ApplicationContext ctx) {
		return (SpecialtycourseDAOImpl) ctx.getBean("SpecialtycourseDAO");
	}
}