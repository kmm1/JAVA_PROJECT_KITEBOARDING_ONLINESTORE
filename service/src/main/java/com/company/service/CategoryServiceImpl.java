package com.company.service;

import com.company.dao.CategoryDao;
import com.company.dao.common.BaseDao;
import com.company.entity.Category;
import com.company.service.common.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Kate M on 07.03.2018.
 */
@Service
public class CategoryServiceImpl extends BaseServiceImpl<Category> implements CategoryService {

    private final CategoryDao categoryDao;

    @Autowired
    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    protected BaseDao<Category> getDao() {
        return categoryDao;
    }


}
