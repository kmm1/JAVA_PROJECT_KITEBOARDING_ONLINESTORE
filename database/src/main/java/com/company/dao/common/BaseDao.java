package com.company.dao.common;

import com.company.entity.BaseEntity;

import java.util.List;

/**
 * Created by Kate M on 09.03.2018.
 */
public interface BaseDao<T extends BaseEntity> {
    T findById(Long id);

    Long save(T entity);

    void delete(T entity);

    void update(T entity);

    List<T> findAll();


}
