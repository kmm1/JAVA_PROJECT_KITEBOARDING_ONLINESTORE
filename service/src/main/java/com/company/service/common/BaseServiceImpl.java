package com.company.service.common;

import com.company.dao.common.BaseDao;
import com.company.entity.BaseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by Kate M on 06.03.2018.
 */
@Transactional
public abstract class BaseServiceImpl<T extends BaseEntity> implements BaseService<T> {

    protected abstract BaseDao<T> getDao();

    @Override
    public Long save(T entity) {
        return getDao().save(entity);
    }

    @Override
    public T findById(Long id) {
        return getDao().findById(id);
    }

    @Override
    public void update(T entity) {
        getDao().update(entity);
    }

    @Override
    public void delete(T entity) {
        getDao().delete(entity);
    }

    @Override
    public List<T> findAll() {
        return getDao().findAll();
    }
}