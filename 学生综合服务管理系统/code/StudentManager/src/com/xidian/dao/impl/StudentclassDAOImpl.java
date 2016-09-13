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

import com.xidian.dao.StudentclassDAO;
import com.xidian.entity.Specialty;
import com.xidian.entity.Studentclass;

/**
 * A data access object (DAO) providing persistence and search support for
 * Studentclass entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.xidian.dao.impl.Studentclass
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class StudentclassDAOImpl implements StudentclassDAO {
	private static final Logger log = LoggerFactory
			.getLogger(StudentclassDAOImpl.class);

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
	public void save(Studentclass transientInstance) {
		log.debug("saving Studentclass instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void delete(Studentclass persistentInstance) {
		log.debug("deleting Studentclass instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<Studentclass> findByExample(Studentclass instance) {
		log.debug("finding Studentclass instance by example");
		try {
			List<Studentclass> results = (List<Studentclass>) getCurrentSession()
					.createCriteria("com.xidian.entity.Studentclass")
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
	public List<Studentclass> findByProperty(String propertyName, Object value) {
		log.debug("finding Studentclass instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Studentclass as model where model."
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
	public List<Studentclass> findAll() {
		log.debug("finding all Studentclass instances");
		try {
			String queryString = "from Studentclass";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<Studentclass> findByProperties(List<String> list) {
		try {
			StringBuffer sb = new StringBuffer(
					"from Studentclass as model where model.classNumber like ?");
			for (int i = 1; i < list.size(); i++) {
				sb.append(" or model.classNumber like ?");
			}
			Query queryObject = getCurrentSession().createQuery(sb.toString());
			for (int i = 0; i < list.size(); i++) {
				queryObject.setParameter(i, list.get(i) + "%");
			}
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	@Override
	public void attachDirty(Studentclass instance) {
		log.debug("attaching dirty Studentclass instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static StudentclassDAOImpl getFromApplicationContext(
			ApplicationContext ctx) {
		return (StudentclassDAOImpl) ctx.getBean("StudentclassDAO");
	}

	@Override
	public List<Studentclass> findBySpecialtyYear(Specialty specialty,
			String year) {
		try {
			StringBuffer sb = new StringBuffer(
					"from Studentclass as model where model.classNumber like ? and model.specialty"
							+ " = ?");
			Query queryObject = getCurrentSession().createQuery(sb.toString());
			queryObject.setParameter(0, year + "%");
			queryObject.setParameter(1, specialty);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
}