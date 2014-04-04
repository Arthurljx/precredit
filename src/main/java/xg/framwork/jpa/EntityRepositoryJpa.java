package xg.framwork.jpa;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import xg.framework.common.InstanceFactory;
import xg.framework.domain.Entity;
import xg.framework.domain.EntityRepository;
import xg.framework.domain.ExampleSettings;
import xg.framework.domain.QueryImpl;
import xg.framework.domain.QuerySettings;
import xg.framework.exception.IocInstanceNotFoundException;
import xg.framwork.jpa.internal.JpaCriteriaQueryBuilder;


/**
 * 通用仓储接口的JPA实现。
 * 
 */
@SuppressWarnings({"unchecked"})
@Named("dddlib_entity_repository_jpa")
public class EntityRepositoryJpa implements EntityRepository {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(EntityRepositoryJpa.class);

	public EntityRepositoryJpa() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dayatang.domain.EntityRepository#save(com.dayatang.domain.Entity)
	 */
	@Override
	public <T extends Entity> T save(T entity) {
		if (entity.iSNew()) {
			getEntityManager().persist(entity);
			LOGGER.info("create a entity: " + entity.getClass() + "/"
					+ entity.getId() + ".");
			return entity;
		}
		T result = getEntityManager().merge(entity);
		LOGGER.info("update a entity: " + entity.getClass() + "/"
				+ entity.getId() + ".");
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dayatang.domain.EntityRepository#remove(com.dayatang.domain.Entity)
	 */
	@Override
	public void remove(Entity entity) {
		getEntityManager().remove(get(entity.getClass(), entity.getId()));
		LOGGER.info("remove a entity: " + entity.getClass() + "/"
				+ entity.getId() + ".");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dayatang.domain.EntityRepository#exists(java.io.Serializable)
	 */
	@Override
	public <T extends Entity> boolean exists(final Class<T> clazz,
			final Serializable id) {
		T entity = getEntityManager().find(clazz, id);
		return entity != null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dayatang.domain.EntityRepository#get(java.io.Serializable)
	 */
	@Override
	public <T extends Entity> T get(final Class<T> clazz, final Serializable id) {
		return getEntityManager().find(clazz, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dayatang.domain.EntityRepository#load(java.io.Serializable)
	 */
	@Override
	public <T extends Entity> T load(final Class<T> clazz, final Serializable id) {
		return getEntityManager().getReference(clazz, id);
	}

	@Override
	public <T extends Entity> T getUnmodified(final Class<T> clazz,
			final T entity) {
		getEntityManager().detach(entity);
		return get(clazz, entity.getId());
	}

	@Override
	public <T extends Entity> List<T> findAll(final Class<T> clazz) {
		String queryString = "select o from " + clazz.getName() + " as o";
		return getEntityManager().createQuery(queryString).getResultList();
	}

	@Override
	public <T extends Entity> List<T> find(final QuerySettings<T> settings) {
		CriteriaQuery<T> criteriaQuery = JpaCriteriaQueryBuilder.getInstance()
				.createCriteriaQuery(settings, getEntityManager());
		Query query = getEntityManager().createQuery(criteriaQuery);
		query.setFirstResult(settings.getFirstResult());
		if (settings.getMaxResults() > 0) {
			query.setMaxResults(settings.getMaxResults());
		}
		return query.getResultList();
	}

	@Override
	public <T> List<T> find(final String queryString, final Object[] params,
			final Class<T> resultClass) {
		Query query = getEntityManager().createQuery(queryString);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i + 1, params[i]);
		}
		return query.getResultList();
	}

	@Override
	public <T> List<T> find(final String queryString,
			final Map<String, Object> params, final Class<T> resultClass) {
		Query query = getEntityManager().createQuery(queryString);
		for (Map.Entry<String, Object> each : params.entrySet()) {
			query = query.setParameter(each.getKey(), each.getValue());
		}
		return query.getResultList();
	}

	@Override
	public <T> List<T> findByNamedQuery(final String queryName,
			final Object[] params, final Class<T> resultClass) {
		Query query = getEntityManager().createNamedQuery(queryName);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i + 1, params[i]);
		}
		return query.getResultList();
	}

	@Override
	public <T> List<T> findByNamedQuery(final String queryName,
			final Map<String, Object> params, final Class<T> resultClass) {
		Query query = getEntityManager().createNamedQuery(queryName);
		for (Map.Entry<String, Object> each : params.entrySet()) {
			query = query.setParameter(each.getKey(), each.getValue());
		}
		return query.getResultList();
	}

	@Override
	public <T extends Entity, E extends T> List<T> findByExample(
			final E example, final ExampleSettings<T> settings) {
		throw new RuntimeException("not implemented yet!");
	}

	@Override
	public <T extends Entity> List<T> findByProperty(Class<T> clazz, String propertyName, Object propertyValue) {
		QuerySettings<T> querySettings = QuerySettings.create(clazz).eq(propertyName, propertyValue);
		return find(querySettings);
	}

	@Override
	public <T extends Entity> List<T> findByProperties(Class<T> clazz, Map<String, Object> properties) {
		QuerySettings<T> querySettings = QuerySettings.create(clazz);
		for (Map.Entry<String, Object> each : properties.entrySet()) {
			querySettings = querySettings.eq(each.getKey(), each.getValue());
		}
		return find(querySettings);
	}

	@Override
	public <T extends Entity> T getSingleResult(QuerySettings<T> settings) {
		List<T> results = find(settings);
		return results.isEmpty() ? null : results.get(0);
	}

	@Override
	public <T> T getSingleResult(final String queryString,
			final Object[] params, Class<T> resultClass) {
		Query query = getEntityManager().createQuery(queryString);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i + 1, params[i]);
		}
		return (T) query.getSingleResult();
	}

	@Override
	public <T> T getSingleResult(final String queryString,
			final Map<String, Object> params, Class<T> resultClass) {
		Query query = getEntityManager().createQuery(queryString);
		for (Map.Entry<String, Object> each : params.entrySet()) {
			query = query.setParameter(each.getKey(), each.getValue());
		}
		return (T) query.getSingleResult();
	}

	@Override
	public void executeUpdate(final String queryString, final Object[] params) {
		Query query = getEntityManager().createQuery(queryString);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i + 1, params[i]);
		}
		query.executeUpdate();
	}

	@Override
	public void executeUpdate(final String queryString,
			final Map<String, Object> params) {
		Query query = getEntityManager().createQuery(queryString);
		for (Map.Entry<String, Object> each : params.entrySet()) {
			query = query.setParameter(each.getKey(), each.getValue());
		}
		query.executeUpdate();
	}

	@Override
	public void flush() {
		getEntityManager().flush();
	}

	@Override
	public void refresh(Entity entity) {
		getEntityManager().refresh(entity);
	}

	@Override
	public void clear() {
		getEntityManager().clear();
	}

    @Override
    public <T extends Entity> xg.framework.domain.Query<T> createQuery(Class<T> entityClass) {
        return new QueryImpl<T>(this, entityClass);
    }

    @Override
    public <T extends Entity> List<T> find(xg.framework.domain.Query<T> dddQuery) {
        return find(dddQuery, dddQuery.getEntityClass());
    }

    @Override
    public <T extends Entity> T getSingleResult(xg.framework.domain.Query<T> dddQuery) {
        return getSingleResult(dddQuery, dddQuery.getEntityClass());
    }

    @Override
    public <E, T extends Entity> List<E> find(xg.framework.domain.Query<T> dddQuery, Class<E> resultClass) {
        CriteriaQuery<T> criteriaQuery = JpaCriteriaQueryBuilder.getInstance()
                .createCriteriaQuery(dddQuery, getEntityManager());
        Query query = getEntityManager().createQuery(criteriaQuery);
        query.setFirstResult(dddQuery.getFirstResult());
        if (dddQuery.getMaxResults() > 0) {
            query.setMaxResults(dddQuery.getMaxResults());
        }
        return query.getResultList();
    }

    @Override
    public <E, T extends Entity> E getSingleResult(xg.framework.domain.Query<T> query, Class<E> resultClass) {
        List<E> results = find(query, resultClass);
        return results.isEmpty() ? null : results.get(0);
    }

    @Inject
	@PersistenceContext
	private EntityManager entityManager;

	private EntityManager getEntityManager() {
		if (entityManager != null) {
			return entityManager;
		}

		try {
			return InstanceFactory.getInstance(EntityManager.class);
		} catch (IocInstanceNotFoundException e) {
			EntityManagerFactory entityManagerFactory = InstanceFactory
					.getInstance(EntityManagerFactory.class);
			return entityManagerFactory.createEntityManager();
		}
	}

}
