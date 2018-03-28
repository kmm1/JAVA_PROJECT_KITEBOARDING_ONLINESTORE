package com.company.service;

import com.company.dao.ProductDao;
import com.company.dao.common.BaseDao;
import com.company.entity.Product;
import com.company.service.common.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Kate M on 07.03.2018.
 */
@Service
public class ProductServiceImpl extends BaseServiceImpl<Product> implements ProductService {

    private final ProductDao productDao;

    @Autowired
    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }


    @Override
    public List<Product> getByCategoryId(Long id) {
        return productDao.getByCategoryId(id);
    }

    @Override
    protected BaseDao<Product> getDao() {
        return productDao;
    }
}
