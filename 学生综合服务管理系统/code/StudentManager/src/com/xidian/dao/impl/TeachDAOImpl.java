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

import com.xidian.dao.TeachDAO;
import com.xidian.entity.Course;
import com.xidian.entity.Teach;
import com.xidian.entity.Teacher;

/**
 * A data access object (DAO) providing persistence and search support for Teach
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.xidian.dao.impl.Teach
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class TeachDAOImpl implements TeachDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TeachDAOImpl.class);

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
	public void save(Teach transientInstance) {
		log.debug("saving Teach instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void delete(Teach persistentInstance) {
		log.debug("deleting Teach instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<Teach> findByExample(Teach instance) {
		log.debug("finding Teach instance by example");
		try {
			List<Teach> results = (List<Teach>) getCurrentSession()
					.createCriteria("com.xidian.entity.Teach")
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
	public List<Teach> findByProperty(String propertyName, Object value,
			int page, int offset) {
		log.debug("finding Teach instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Teach as model where model."
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
	public List<Teach> findAll() {
		log.debug("finding all Teach instances");
		try {
			String queryString = "from Teach";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public void attachDirty(Teach instance) {
		log.debug("attaching dirty Teach instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Override
	public List<Teach> findByProperties(List<String> propertyNames,
			List<Object> values, int page, int offset) {
		try {
			StringBuffer sb = new StringBuffer(
					"from Teach as model where model." + propertyNames.get(0)
							+ " = ?");
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
	
	@Override
	public List<Teach> findByCourses(List<Course> courses, int page, int offset) {
		try {
			StringBuffer sb = new StringBuffer(
					"from Teach as model where model.course = ?");
			for (int i = 1; i < courses.size(); i++) {
				sb.append(" or model.course = ?");
			}
			Query queryObject = getCurrentSession().createQuery(sb.toString())
					.setFirstResult((page - 1) * offset).setMaxResults(offset);
			for (int i = 0; i < courses.size(); i++) {
				queryObject.setParameter(i, courses.get(i));
			}
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	@Override
	public List<Teach> findByTeachers(List<Teacher> teachers) {
		try {
			StringBuffer sb = new StringBuffer(
					"from Teach as model where model.teacher = ?");
			for (int i = 1; i < teachers.size(); i++) {
				sb.append(" or model.teacher = ?");
			}
			sb.append(" order by model.teacher.teacherNumber asc");
			Query queryObject = getCurrentSession().createQuery(sb.toString());
			for (int i = 0; i < teachers.size(); i++) {
				queryObject.setParameter(i, teachers.get(i));
			}
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public static TeachDAOImpl getFromApplicationContext(ApplicationContext ctx) {
		return (TeachDAOImpl) ctx.getBean("TeachDAO");
	}

}