package com.company.dao;

import com.company.dao.common.BaseDaoTest;
import com.company.entity.EnumOrderStatus;
import com.company.entity.Orders;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
    public void test() {
        getTestDataImporter().importTestData();
        Long numberOfProductsInCort = orderDao.countProductsInCart(2L);
        System.out.println(numberOfProductsInCort);
    }

}
