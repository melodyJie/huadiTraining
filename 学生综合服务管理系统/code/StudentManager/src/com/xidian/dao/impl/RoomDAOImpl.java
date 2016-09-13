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

import com.xidian.dao.RoomDAO;
import com.xidian.entity.Building;
import com.xidian.entity.Room;

/**
 * A data access object (DAO) providing persistence and search support for Room
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.xidian.dao.impl.Room
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class RoomDAOImpl implements RoomDAO {
	private static final Logger log = LoggerFactory
			.getLogger(RoomDAOImpl.class);

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
	public void save(Room transientInstance) {
		log.debug("saving Room instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void delete(Room persistentInstance) {
		log.debug("deleting Room instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<Room> findByExample(Room instance) {
		log.debug("finding Room instance by example");
		try {
			List<Room> results = (List<Room>) getCurrentSession()
					.createCriteria("com.xidian.entity.Room")
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
	public List<Room> findByProperty(String propertyName, Object value) {
		log.debug("finding Room instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Room as model where model."
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
	public List<Room> findAll() {
		log.debug("finding all Room instances");
		try {
			String queryString = "from Room";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public void attachDirty(Room instance) {
		log.debug("attaching dirty Room instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Override
	public List<Room> findByDivide(List<Room> list2, Building building,
			int page, int offset) {
		try {
			if (null == list2 || list2.size() == 0) {
				if (building == null) {
					return this.findAll();
				} else {
					return this.findByProperty("building", building);
				}
			} else {
				StringBuffer sb = new StringBuffer();
				sb.append("from Room as model where model != ?");
				for (int i = 1; i < list2.size(); i++) {
					sb.append(" and model != ?");
				}
				if (building != null) {
					sb.append(" and model.building = ?");
				}
				String queryString = sb.toString();
				Query queryObject = getCurrentSession()
						.createQuery(queryString)
						.setFirstResult((page - 1) * offset)
						.setMaxResults(offset);
				for (int i = 0; i < list2.size(); i++) {
					queryObject.setParameter(i, list2.get(i));
				}
				if (building != null) {
					queryObject.setParameter(list2.size(), building);
				}
				return queryObject.list();
			}
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public static RoomDAOImpl getFromApplicationContext(ApplicationContext ctx) {
		return (RoomDAOImpl) ctx.getBean("RoomDAO");
	}
}