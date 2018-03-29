package com.company.dao;

import com.company.dao.common.BaseDaoTest;
import com.company.entity.EnumOrderStatus;
import com.company.entity.Orders;
import com.company.entity.Product;
import com.company.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Created by Kate M on 07.03.2018.
 */
public class OrderDaoTest extends BaseDaoTest<Orders> {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private UserDao userDao;

    @Test
    public void testSaveOrder() {
        getTestDataImporter().importTestData();
        Orders order = new Orders();
        order.setStatus(EnumOrderStatus.NEW);
        order.setUsers(userDao.findById(2L));
        Long id = orderDao.save(order);
        Assert.assertThat(orderDao.findById(id), notNullValue());
    }

    @Test
    public void testDeleteOrder() {
        getTestDataImporter().importTestData();
        Long id = 1L;
        Orders order = orderDao.findById(id);
        Assert.assertThat(order, notNullValue());
        orderDao.delete(order);
        Assert.assertThat(orderDao.findById(id), nullValue());
    }

    @Test
    public void testFindAllOrders() {
        getTestDataImporter().importTestData();
        List<Orders> allOrders = orderDao.findAll();
        Assert.assertThat(allOrders, hasSize(4));
    }

    @Test
    public void testCountProductsInCartByUserId() {
        getTestDataImporter().importTestData();
        Long numberOfProductsInCort = orderDao.countProductsInCart(2L);
        assertThat(numberOfProductsInCort, is(3L));
    }

    @Test
    public void testFindUserCartByUserId() {
        getTestDataImporter().importTestData();
        Double cartTotal = orderDao.findCartTotal(2L);
        assertThat(cartTotal, is(2717.00));
    }

    @Test
    public void testFindInfoAboutProductsInCartByUserId() {
        getTestDataImporter().importTestData();
        Map<Product, Integer> productsInCart = orderDao.findInfoAboutProductsInCart(2L);
        assertThat(productsInCart, notNullValue());
    }

    @Test
    public void testUpdateProductsInUserCartByUserId() {
        getTestDataImporter().importTestData();
        User user = userDao.findById(2L);
        Orders userCart = orderDao.findUserCart(user.getId());
        List<String> list = userCart.getProducts().stream().map(e -> e.getName()).collect(Collectors.toList());
        assertThat(userCart.getProducts().size(), is(3));
        assertThat(list, containsInAnyOrder("50/FIFTY", "50/FIFTY", "Tride-NBL"));
        orderDao.updateProductsInUserCart(user.getName(), 1L, 3);
        List<String> list2 = userCart.getProducts().stream().map(e -> e.getName()).collect(Collectors.toList());
        assertThat(userCart.getProducts().size(), is(4));
        assertThat(list2, containsInAnyOrder("50/FIFTY", "50/FIFTY", "50/FIFTY", "Tride-NBL"));
    }

    @Test
    public void testDeleteProductFromUserCartByUserId() {
        getTestDataImporter().importTestData();
        User user = userDao.findById(2L);
        Orders userCart = orderDao.findUserCart(user.getId());
        List<String> list = userCart.getProducts().stream().map(e -> e.getName()).collect(Collectors.toList());
        assertThat(userCart.getProducts().size(), is(3));
        assertThat(list, containsInAnyOrder("50/FIFTY", "50/FIFTY", "Tride-NBL"));
        orderDao.deleteProductsInUserCart(user.getName(), 1L);
        List<String> list2 = userCart.getProducts().stream().map(e -> e.getName()).collect(Collectors.toList());
        assertThat(userCart.getProducts().size(), is(1));
        assertThat(list2, containsInAnyOrder("Tride-NBL"));
    }

    @Test
    public void testAddProductsToUserCartByUserId() {
        getTestDataImporter().importTestData();
        User user = userDao.findById(2L);
        Orders userCart = orderDao.findUserCart(user.getId());
        List<String> list = userCart.getProducts().stream().map(e -> e.getName()).collect(Collectors.toList());
        assertThat(userCart.getProducts().size(), is(3));
        assertThat(list, containsInAnyOrder("50/FIFTY", "50/FIFTY", "Tride-NBL"));
        orderDao.addProductsToUserCart(user.getName(), 3L);
        List<String> list2 = userCart.getProducts().stream().map(e -> e.getName()).collect(Collectors.toList());
        assertThat(userCart.getProducts().size(), is(4));
        assertThat(list2, containsInAnyOrder("50/FIFTY", "50/FIFTY", "Tride-NBL", "NO. 1"));
    }

}
