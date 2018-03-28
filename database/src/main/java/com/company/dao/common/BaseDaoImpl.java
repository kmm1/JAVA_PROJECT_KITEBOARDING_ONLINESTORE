package com.company.dao.common;

import com.company.entity.BaseEntity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;

import java.util.List;


/**
 * Created by Kate M on 06.03.2018.
 */
public abstract class BaseDaoImpl<T extends BaseEntity> implements BaseDao<T> {

    @Autowired
    private SessionFactory sessionFactory;
    private Class<T> entityClass;

    @SuppressWarnings("unchecked")
    public BaseDaoImpl() {
        this.entityClass = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), BaseDaoImpl.class);
    }

    protected SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    public T findById(Long id) {
        return sessionFactory.getCurrentSession().get(entityClass, id);
    }

    @Override
    public Long save(T entity) {
        return (Long) sessionFactory.getCurrentSession().save(entity);
    }

    @Override
    public void delete(T entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    @Override
    public void update(T entity) {
        sessionFactory.getCurrentSession().update(entity);
    }

    @Override
    public List<T> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from " + entityClass.getSimpleName(), entityClass).getResultList();
    }
}
