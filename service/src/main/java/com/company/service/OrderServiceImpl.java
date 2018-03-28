package com.company.service;

import com.company.dao.OrderDao;
import com.company.dao.common.BaseDao;
import com.company.entity.Orders;
import com.company.entity.Product;
import com.company.service.common.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Kate M on 07.03.2018.
 */
@Service
public class OrderServiceImpl extends BaseServiceImpl<Orders> implements OrderService {

    private final OrderDao orderDao;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    protected BaseDao<Orders> getDao() {
        return orderDao;
    }

    @Override
    public Long countProductsInCart(Long id) {
        return orderDao.countProductsInCart(id);
    }

    @Override
    public Double findCartTotal(Long id) {
        return orderDao.findCartTotal(id);
    }

    @Override
    public Orders findUserCart(Long id) {
        return orderDao.findUserCart(id);
    }

    @Override
    public Map<Product, Integer> findInfoAboutProductsInCart(Long id) {
        return orderDao.findInfoAboutProductsInCart(id);
    }

}
