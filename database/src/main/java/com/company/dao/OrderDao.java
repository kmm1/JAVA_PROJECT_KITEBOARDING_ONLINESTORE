package com.company.dao;

import com.company.dao.common.BaseDao;
import com.company.entity.Orders;
import com.company.entity.Product;

import java.util.Map;

/**
 * Created by Kate M on 09.03.2018.
 */
public interface OrderDao extends BaseDao<Orders> {

    Long countProductsInCart(Long id);

    Double findCartTotal(Long id);

    Orders findUserCart(Long id);

    Map<Product, Integer> findInfoAboutProductsInCart(Long id);

    void updateProductsInUserCart(String userName, Long productId, int amountToUpdate);

    void deleteProductsInUserCart(String userName, Long productId);

     void addProductsToUserCart(String userName, Long productId);


    }
