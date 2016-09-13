package com.xidian.dao.impl;

import static org.hibernate.criterion.Example.create;

import java.util.Date;
import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.xidian.dao.RoomscoreDAO;
import com.xidian.entity.Room;
import com.xidian.entity.Roomscore;

/**
 * A data access object (DAO) providing persistence and search support for
 * Roomscore entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.xidian.dao.impl.Roomscore
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class RoomscoreDAOImpl implements RoomscoreDAO {
	private static final Logger log = LoggerFactory
			.getLogger(RoomscoreDAOImpl.class);

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
	public void save(Roomscore transientInstance) {
		log.debug("saving Roomscore instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void delete(Roomscore persistentInstance) {
		log.debug("deleting Roomscore instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	@Override
	public List<Roomscore> findByExample(Roomscore instance) {
		log.debug("finding Roomscore instance by example");
		try {
			List<Roomscore> results = (List<Roomscore>) getCurrentSession()
					.createCriteria("com.xidian.entity.Roomscore")
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
	public List<Roomscore> findByProperty(String propertyName, Object value,
			int page, int offset) {
		log.debug("finding Roomscore instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Roomscore as model where model."
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
	public List<Roomscore> findAll() {
		log.debug("finding all Roomscore instances");
		try {
			String queryString = "from Roomscore";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public void attachDirty(Roomscore instance) {
		log.debug("attaching dirty Roomscore instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Override
	public List<Roomscore> findByDivide(List<Room> list, Date date, int page,
			int offset) {
		if (null == list || list.size() == 0) {
			return null;
		} else {
			try {
				StringBuffer sb = new StringBuffer();
				sb.append("from Roomscore as model where (model.room = ?");
				for (int i = 1; i < list.size(); i++) {
					sb.append(" or model.room = ?");
				}
				sb.append(")");
				if (date != null) {
					sb.append(" and model.scoreDate = ?");
				}
				String queryString = sb.toString();
				Query queryObject = getCurrentSession()
						.createQuery(queryString)
						.setFirstResult((page - 1) * offset)
						.setMaxResults(offset);
				for (int i = 0; i < list.size(); i++) {
					queryObject.setParameter(i, list.get(i));
				}
				if (date != null) {
					queryObject.setParameter(list.size(), date);
				}
				return queryObject.list();
			} catch (RuntimeException re) {
				log.error("find by property name failed", re);
				throw re;
			}
		}
	}

	public static RoomscoreDAOImpl getFromApplicationContext(
			ApplicationContext ctx) {
		return (RoomscoreDAOImpl) ctx.getBean("RoomscoreDAO");
	}
}