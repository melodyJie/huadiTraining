package com.xidian.dao.impl;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.xidian.dao.StudentDAO;
import com.xidian.entity.Student;
import com.xidian.entity.Studentclass;

/**
 * A data access object (DAO) providing persistence and search support for
 * Student entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.xidian.dao.impl.Student
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class StudentDAOImpl implements StudentDAO {
	private static final Logger log = LoggerFactory
			.getLogger(StudentDAOImpl.class);

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
	public void save(Student transientInstance) {
		log.debug("saving Student instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void delete(Student persistentInstance) {
		log.debug("deleting Student instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<Student> findByExample(Student instance) {
		log.debug("finding Student instance by example");
		try {
			List<Student> results = (List<Student>) getCurrentSession()
					.createCriteria("com.xidian.entity.Student")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	@Override
	public List<Student> findByProperty(String propertyName, Object value) {
		log.debug("finding Student instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Student as model where model."
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
	public List<Student> findAll() {
		log.debug("finding all Student instances");
		try {
			String queryString = "from Student";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public void attachDirty(Student instance) {
		log.debug("attaching dirty Student instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Override
	public List<Student> findByDivide(List<Studentclass> parameters,
			List<Student> students, String year, int page, int offset) {
		try {
			if (null == parameters || parameters.size() == 0) {
				StringBuffer sb = new StringBuffer();
				sb.append("from Student as model");
				if (null != year) {
					sb.append(" where model.year = ?");
				}
				sb.append(" order by model.studentNumber asc");
				String queryString = sb.toString();
				Query queryObject = getCurrentSession()
						.createQuery(queryString)
						.setFirstResult((page - 1) * offset)
						.setMaxResults(offset);
				if (null != year) {
					queryObject.setParameter(0, year);
				}
				return queryObject.list();
			} else {
				int studentSize = 0;
				StringBuffer sb = new StringBuffer();
				sb.append("from Student as model where (model.studentclass = ?");
				for (int i = 1; i < parameters.size(); i++) {
					sb.append(" or model.studentclass = ?");
				}
				sb.append(")");
				if (null != students && students.size() > 0) {
					studentSize = students.size();
					for (int i = 0; i < students.size(); i++) {
						sb.append(" and model.studentId != ?");
					}
				}
				if (null != year) {
					sb.append(" and model.year = ?");
				}
				sb.append(" order by model.studentNumber asc");
				String queryString = sb.toString();
				Query queryObject = getCurrentSession()
						.createQuery(queryString)
						.setFirstResult((page - 1) * offset)
						.setMaxResults(offset);
				for (int i = 0; i < parameters.size(); i++) {
					queryObject.setParameter(i, parameters.get(i));
				}
				if (null != students && students.size() > 0) {
					for (int i = 0; i < students.size(); i++) {
						queryObject.setParameter(parameters.size() + i,
								students.get(i).getStudentId());
					}
				}
				if (null != year) {
					queryObject.setParameter(parameters.size() + studentSize,
							year);
				}
				return queryObject.list();
			}
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	@Override
	public List<Student> findByProperties(List<String> propertyNames,
			List<Object> values) {
		try {
			StringBuffer sb = new StringBuffer(
					"from Student as model where model." + propertyNames.get(0)
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

	public static StudentDAOImpl getFromApplicationContext(
			ApplicationContext ctx) {
		return (StudentDAOImpl) ctx.getBean("StudentDAO");
	}
}