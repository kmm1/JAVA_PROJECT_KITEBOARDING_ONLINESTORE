package com.company.service;

import com.company.entity.Product;
import com.company.service.common.BaseService;

import java.util.List;

/**
 * Created by Kate M on 09.03.2018.
 */
public interface ProductService extends BaseService<Product> {
    List<Product> getByCategoryId(Long id);

}
