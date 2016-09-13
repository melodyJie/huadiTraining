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

import com.xidian.dao.CourseDAO;
import com.xidian.entity.Course;

/**
 * A data access object (DAO) providing persistence and search support for
 * Course entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.xidian.dao.impl.Course
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class CourseDAOImpl implements CourseDAO {
	private static final Logger log = LoggerFactory
			.getLogger(CourseDAOImpl.class);

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
	public void save(Course transientInstance) {
		log.debug("saving Course instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void delete(Course persistentInstance) {
		log.debug("deleting Course instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<Course> findByExample(Course instance) {
		log.debug("finding Course instance by example");
		try {
			List<Course> results = (List<Course>) getCurrentSession()
					.createCriteria("com.xidian.entity.Course")
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
	public List<Course> findByProperty(String propertyName, Object value) {
		log.debug("finding Course instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Course as model where model."
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
	public List<Course> findAll(String input, int page, int offset) {
		log.debug("finding all Course instances");
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("from Course");
			if (null != input && !input.equals("")) {
				sb.append(" as model where model.courseName like ? "
						+ "or model.courseType like ? ");
			}
			Query queryObject = getCurrentSession().createQuery(sb.toString())
					.setFirstResult((page - 1) * offset).setMaxResults(offset);
			if (null != input && !input.equals("")){
				for (int i = 0; i < 2; i++) {
					queryObject.setParameter(i, "%" + input + "%");
				}
			}
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public void attachDirty(Course instance) {
		log.debug("attaching dirty Course instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static CourseDAOImpl getFromApplicationContext(ApplicationContext ctx) {
		return (CourseDAOImpl) ctx.getBean("CourseDAO");
	}

}