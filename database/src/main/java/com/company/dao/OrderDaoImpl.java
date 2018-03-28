package com.company.dao;

import com.company.dao.common.BaseDaoImpl;
import com.company.entity.EnumOrderStatus;
import com.company.entity.Orders;
import com.company.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Kate M on 07.03.2018.
 */
@Repository
public class OrderDaoImpl extends BaseDaoImpl<Orders> implements OrderDao {

    @Override
    public Long countProductsInCart(Long id) {
        List<Long> resultList = getSessionFactory().getCurrentSession().createQuery(
                "SELECT count (po) FROM ProductOrders po JOIN po.product p JOIN po.order as o WHERE o.status=:status and o.users.id=:id", Long.class)
                .setParameter("status", EnumOrderStatus.NEW)
                .setParameter("id", id)
                .getResultList();
        return resultList.size() > 0 ? resultList.get(0) : 0L;
    }

    @Override
    public Double findCartTotal(Long id) {
        List<Double> resultList = getSessionFactory().getCurrentSession().createQuery(
                "SELECT sum(p.price) FROM  ProductOrders po JOIN po.product p JOIN po.order o WHERE o.status=:status AND o.users.id=:id", Double.class)
                .setParameter("id", id)
                .setParameter("status", EnumOrderStatus.NEW)
                .getResultList();
        return resultList.size() > 0 ? resultList.get(0) : 0.00;
    }

    @Override
    public Orders findUserCart(Long id) {
        List<Orders> resultList = getSessionFactory().getCurrentSession().createQuery(
                "SELECT o FROM Orders o WHERE o.status=:status and o.users.id=:id", Orders.class)
                .setParameter("status", EnumOrderStatus.NEW)
                .setParameter("id", id)
                .getResultList();
        return resultList.size() > 0 ? resultList.get(0) : new Orders();
    }

    @Override
    public Map<Product, Integer> findInfoAboutProductsInCart(Long id) {
        List<Product> productList = getSessionFactory().getCurrentSession().createQuery(
                "SELECT p FROM  ProductOrders po JOIN po.product p JOIN po.order o WHERE o.status=:status AND o.users.id=:id", Product.class)
                .setParameter("id", id)
                .setParameter("status", EnumOrderStatus.NEW)
                .getResultList();
        Map<Product, Integer> map = new HashMap<>();
        for (Product product : productList) {
            Integer counter = map.get(product);
            map.put(product, map.get(product) == null ? 1 : map.get(product)+1);
        }
        return map;
    }


}
