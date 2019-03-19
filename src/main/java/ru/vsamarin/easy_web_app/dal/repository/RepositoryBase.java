package ru.vsamarin.easy_web_app.dal.repository;


import ru.vsamarin.easy_web_app.dal.entity.EntityBase;
import ru.vsamarin.easy_web_app.dal.filter.CriteriaQueryContainer;
import ru.vsamarin.easy_web_app.dal.filter.FilterBase;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class RepositoryBase<TEntity extends EntityBase> {

    private Class<TEntity> entityClass;

    @SuppressWarnings("unchecked")
    public RepositoryBase() {
        entityClass = (Class<TEntity>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected abstract EntityManager em();

    protected Class<TEntity> getEntityClass() {
        return entityClass;
    }

    @SuppressWarnings("unchecked")
    public long getCount(FilterBase<TEntity> filter) {
        EntityManager em = null;
        try {
            em = em();
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<Long> query = builder.createQuery(Long.class);
            Root<TEntity> root = query.from(getEntityClass());

            Predicate predicate = filter == null ?
                    null : filter.apply(new CriteriaQueryContainer(builder, query, root));

            query.select(builder.countDistinct(root));

            if (predicate != null) {
                query.where(predicate);
            }

            return em.createQuery(query).setMaxResults(1).getSingleResult();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    public long getCount() {
        return getCount(null);
    }

    public List<TEntity> getAll() {
        EntityManager em = null;
        try {
            em = em();
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<TEntity> query = builder.createQuery(getEntityClass());
            Root<TEntity> root = query.from(getEntityClass());
            TypedQuery<TEntity> typedQuery = em().createQuery(query.select(root));
            return typedQuery.getResultList();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    public List<TEntity> getList(FilterBase<TEntity> filter) {
        EntityManager em = null;
        try {
            em = em();
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<TEntity> query = builder.createQuery(getEntityClass());
            Root<TEntity> root = query.from(getEntityClass());

            Predicate predicate = filter.apply(new CriteriaQueryContainer<>(builder, query, root));

            if (predicate != null) {
                query.where(predicate);
            }

            if (filter.getOrderBy() != null) {
                query.orderBy(filter.getAscending()
                        ? builder.asc(root.get(filter.getOrderBy()))
                        : builder.desc(root.get(filter.getOrderBy()))
                );
            }

            Integer limit = filter.getRecordsOnPage();
            Integer offset = filter.getOffset();

            TypedQuery<TEntity> typedQuery = em().createQuery(query);
            if (offset != null) {
                typedQuery = typedQuery.setFirstResult(offset).setMaxResults(limit);
            }

            return typedQuery.getResultList();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    public TEntity getById(Long id) {
        if (id == null) {
            return null;
        }
        EntityManager em = null;
        try {
            em = em();
            return em().find(getEntityClass(), id);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    public TEntity save(TEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("entity is null");
        }
        EntityManager em = null;
        EntityTransaction etx = null;
        try {
            em = em();
            etx = em.getTransaction();
            etx.begin();
            TEntity newEntity = em.merge(entity);
            etx.commit();
            return newEntity;
        } catch (Exception e) {
            if (etx != null && etx.isActive()) {
                etx.rollback();
            }
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
        return null;
    }

    public void delete(TEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("entity is null");
        }
        EntityManager em = null;
        EntityTransaction etx = null;
        try {
            em = em();
            etx = em.getTransaction();
            etx.begin();
            em.remove(entity);
            etx.commit();
        } catch (Exception e) {
            if (etx != null && etx.isActive()) {
                etx.rollback();
            }
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

}
