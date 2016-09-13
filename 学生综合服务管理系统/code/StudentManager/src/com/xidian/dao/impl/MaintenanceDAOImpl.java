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

import com.xidian.dao.MaintenanceDAO;
import com.xidian.entity.Maintenance;
import com.xidian.entity.Room;

/**
 * A data access object (DAO) providing persistence and search support for
 * Maintenance entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.xidian.dao.impl.Maintenance
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class MaintenanceDAOImpl implements MaintenanceDAO {
	private static final Logger log = LoggerFactory
			.getLogger(MaintenanceDAOImpl.class);

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
	public void save(Maintenance transientInstance) {
		log.debug("saving Maintenance instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void delete(Maintenance persistentInstance) {
		log.debug("deleting Maintenance instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<Maintenance> findByExample(Maintenance instance) {
		log.debug("finding Maintenance instance by example");
		try {
			List<Maintenance> results = (List<Maintenance>) getCurrentSession()
					.createCriteria("com.xidian.entity.Maintenance")
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
	public List<Maintenance> findByProperty(String propertyName, Object value,
			int page, int offset) {
		log.debug("finding Maintenance instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Maintenance as model where model."
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
	public List<Maintenance> findAll() {
		log.debug("finding all Maintenance instances");
		try {
			String queryString = "from Maintenance";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public void attachDirty(Maintenance instance) {
		log.debug("attaching dirty Maintenance instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Override
	public List<Maintenance> findByDivide(List<Room> list, String[] states,
			int page, int offset) {
		try {
			if (null == list || list.size() == 0) {
				return null;
			} else {
				StringBuffer sb = new StringBuffer();
				sb.append("from Maintenance as model where (model.room = ?");
				for (int i = 1; i < list.size(); i++) {
					sb.append(" or model.room = ?");
				}
				sb.append(") and ( model.state = ?");
				for (int i = 1; i < states.length; i++) {
					sb.append(" or model.state = ?");
				}
				sb.append(")");
				String queryString = sb.toString();
				Query queryObject = getCurrentSession()
						.createQuery(queryString)
						.setFirstResult((page - 1) * offset)
						.setMaxResults(offset);
				for (int i = 0; i < list.size(); i++) {
					queryObject.setParameter(i, list.get(i));
				}
				for (int i = 0; i < states.length; i++) {
					queryObject.setParameter(list.size() + i, states[i]);
				}
				return queryObject.list();
			}
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public static MaintenanceDAOImpl getFromApplicationContext(
			ApplicationContext ctx) {
		return (MaintenanceDAOImpl) ctx.getBean("MaintenanceDAO");
	}
}