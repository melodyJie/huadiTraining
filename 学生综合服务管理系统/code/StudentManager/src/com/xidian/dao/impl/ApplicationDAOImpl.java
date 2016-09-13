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

import com.xidian.dao.ApplicationDAO;
import com.xidian.entity.Application;
import com.xidian.entity.Student;

/**
 * A data access object (DAO) providing persistence and search support for
 * Application entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.xidian.dao.impl.Application
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class ApplicationDAOImpl implements ApplicationDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ApplicationDAOImpl.class);

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
	public void save(Application transientInstance) {
		log.debug("saving Application instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void delete(Application persistentInstance) {
		log.debug("deleting Application instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<Application> findByExample(Application instance) {
		log.debug("finding Application instance by example");
		try {
			List<Application> results = (List<Application>) getCurrentSession()
					.createCriteria("com.xidian.entity.Application")
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
	public List<Application> findByProperty(String propertyName, Object value,
			int page, int offset) {
		log.debug("finding Application instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Application as model where model."
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
	public List<Application> findAll() {
		log.debug("finding all Application instances");
		try {
			String queryString = "from Application";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public void attachDirty(Application instance) {
		log.debug("attaching dirty Application instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Override
	public List<Application> findByDivide(List<Student> list, String[] states,
			int page, int offset) {
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("from Application as model where (model.state = ?");
			for (int i = 1; i < states.length; i++) {
				sb.append(" or model.state = ?");
			}
			sb.append(")");
			if (null != list && list.size() > 0) {
				sb.append(" and (model.student = ?");
				for (int i = 1; i < list.size(); i++) {
					sb.append(" or model.student = ?");
				}
				sb.append(")");
			}
			String queryString = sb.toString();
			Query queryObject = getCurrentSession().createQuery(queryString)
					.setFirstResult((page - 1) * offset).setMaxResults(offset);
			for (int i = 0; i < states.length; i++) {
				queryObject.setParameter(i, states[i]);
			}
			if (null != list && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					queryObject.setParameter(states.length + i, list.get(i));
				}
			}
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public static ApplicationDAOImpl getFromApplicationContext(
			ApplicationContext ctx) {
		return (ApplicationDAOImpl) ctx.getBean("ApplicationDAO");
	}
}