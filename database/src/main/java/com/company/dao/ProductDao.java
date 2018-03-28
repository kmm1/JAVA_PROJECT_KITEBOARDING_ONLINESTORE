package com.company.dao;

import com.company.dao.common.BaseDao;
import com.company.entity.Product;

import java.util.List;

/**
 * Created by Kate M on 09.03.2018.
 */
public interface ProductDao extends BaseDao<Product> {
    List<Product> getByCategoryId(Long id);

}
