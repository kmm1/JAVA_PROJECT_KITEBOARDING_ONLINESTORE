package com.company.dao;

import com.company.dao.common.BaseDaoImpl;
import com.company.entity.EnumOrderStatus;
import com.company.entity.Orders;
import com.company.entity.Product;
import com.company.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Kate M on 07.03.2018.
 */
@Repository
@Transactional
public class OrderDaoImpl extends BaseDaoImpl<Orders> implements OrderDao {


    @Autowired
    private UserDao userDao;
    @Autowired
    private ProductDao productDao;

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
                "SELECT p FROM  ProductOrders po JOIN po.product p JOIN po.order o WHERE o.status=:status AND o.users.id=:id order by p.name", Product.class)
                .setParameter("id", id)
                .setParameter("status", EnumOrderStatus.NEW)
                .getResultList();
        Map<Product, Integer> map = new HashMap<>();
        for (Product product : productList) {
            Integer counter = map.get(product);
            map.put(product, map.get(product) == null ? 1 : map.get(product) + 1);
        }
        return map;
    }

    @Override
    public void updateProductsInUserCart(String userName, Long productId, int amountToUpdate) {
        User foundUser = userDao.getUserByName(userName);
        Map<Product, Integer> productsInCart = findInfoAboutProductsInCart(foundUser.getId());
        Orders foundOrder = findUserCart(foundUser.getId());
        Product foundProduct = productDao.findById(productId);
        for (Map.Entry<Product, Integer> p : productsInCart.entrySet()) {
            if (p.getKey().getId().equals(productId)) {
                Integer productsInCourt = p.getValue();
                if (amountToUpdate > productsInCourt) {
                    for (int i = 1; i <= amountToUpdate - productsInCourt; i++) {
                        foundOrder.getProducts().add(foundProduct);
                    }
                    getSessionFactory().getCurrentSession().update(foundOrder);
                }
                if (amountToUpdate < productsInCourt) {
                    Iterator<Product> iterator = foundOrder.getProducts().iterator();
                    while (iterator.hasNext()) {
                        Product next = iterator.next();
                        if (next.getId() == foundProduct.getId()) {
                            iterator.remove();
                        }
                    }
                    for (int i = 1; i <= amountToUpdate; i++) {
                        foundOrder.getProducts().add(foundProduct);
                    }
                }
                getSessionFactory().getCurrentSession().update(foundOrder);
            }
        }
    }

    @Override
    public void deleteProductsInUserCart(String userName, Long productId) {
        User foundUser = userDao.getUserByName(userName);
        Map<Product, Integer> productsInCart = findInfoAboutProductsInCart(foundUser.getId());
        Orders foundOrder = findUserCart(foundUser.getId());
        Product foundProduct = productDao.findById(productId);
        for (Map.Entry<Product, Integer> p : productsInCart.entrySet()) {
            if (p.getKey().getId().equals(productId)) {
                Iterator<Product> iterator = foundOrder.getProducts().iterator();
                while (iterator.hasNext()) {
                    Product next = iterator.next();
                    if (next.getId() == foundProduct.getId()) {
                        iterator.remove();
                    }
                }
            }
            getSessionFactory().getCurrentSession().update(foundOrder);
        }
    }

    @Override
    public void addProductsToUserCart(String userName, Long productId) {
        User foundUser = userDao.getUserByName(userName);
        Product foundProduct = productDao.findById(productId);
        Orders foundOrder = findUserCart(foundUser.getId());
        List<Product> list = foundOrder.getProducts();
        list.add(foundProduct);
        getSessionFactory().getCurrentSession().update(foundOrder);
    }


}






