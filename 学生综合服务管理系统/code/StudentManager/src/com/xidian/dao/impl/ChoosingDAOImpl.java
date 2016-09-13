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

import com.xidian.dao.ChoosingDAO;
import com.xidian.entity.Choosing;
import com.xidian.entity.Student;
import com.xidian.entity.Teach;

/**
 * A data access object (DAO) providing persistence and search support for
 * Choosing entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.xidian.dao.impl.Choosing
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class ChoosingDAOImpl implements ChoosingDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ChoosingDAOImpl.class);

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
	public void save(Choosing transientInstance) {
		log.debug("saving Choosing instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void delete(Choosing persistentInstance) {
		log.debug("deleting Choosing instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<Choosing> findByExample(Choosing instance) {
		log.debug("finding Choosing instance by example");
		try {
			List<Choosing> results = (List<Choosing>) getCurrentSession()
					.createCriteria("com.xidian.entity.Choosing")
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
	public List<Choosing> findByProperty(String propertyName, Object value) {
		log.debug("finding Choosing instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Choosing as model where model."
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
	public List<Choosing> findAll() {
		log.debug("finding all Choosing instances");
		try {
			String queryString = "from Choosing";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<Choosing> findByDivide(List<Student> students, Teach teach,
			int page, int offset) {
		try {
			if (null == students || students.size() == 0) {
				String queryString = "from Choosing as model where model.teach = ?"
						+ " order by model.student.studentNumber asc";
				Query queryObject = getCurrentSession()
						.createQuery(queryString)
						.setFirstResult((page - 1) * offset)
						.setMaxResults(offset);
				queryObject.setParameter(0, teach);
				return queryObject.list();
			} else {
				StringBuffer sb = new StringBuffer();
				sb.append("from Choosing as model where model.student != ?");
				for (int i = 1; i < students.size(); i++) {
					sb.append(" and model.student != ?");
				}
				sb.append(" and model.teach = ? order by model.student.studentNumber asc");
				String queryString = sb.toString();
				Query queryObject = getCurrentSession()
						.createQuery(queryString)
						.setFirstResult((page - 1) * offset)
						.setMaxResults(offset);
				for (int i = 0; i < students.size(); i++) {
					queryObject.setParameter(i, students.get(i));
				}
				queryObject.setParameter(students.size(), teach);
				return queryObject.list();
			}
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	@Override
	public void attachDirty(Choosing instance) {
		log.debug("attaching dirty Choosing instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ChoosingDAOImpl getFromApplicationContext(
			ApplicationContext ctx) {
		return (ChoosingDAOImpl) ctx.getBean("ChoosingDAO");
	}
}