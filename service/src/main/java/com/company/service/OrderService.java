package com.company.service;

import com.company.entity.EnumOrderStatus;
import com.company.entity.Orders;
import com.company.entity.Product;
import com.company.service.common.BaseService;

import java.util.Map;

/**
 * Created by Kate M on 09.03.2018.
 */
public interface OrderService extends BaseService<Orders> {

    Long countProductsInCart(Long id);

    Double findCartTotal(Long id);

    Orders findUserCart(Long id);

    Map<Product, Integer> findInfoAboutProductsInCart(Long id);


}
