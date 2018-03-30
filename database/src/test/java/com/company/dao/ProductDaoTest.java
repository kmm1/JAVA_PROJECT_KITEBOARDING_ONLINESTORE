package com.company.dao;

import com.company.dao.common.BaseDaoTest;
import com.company.entity.Category;
import com.company.entity.Product;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by Kate M on 07.03.2018.
 */
public class ProductDaoTest extends BaseDaoTest<Product> {

    @Autowired
    private ProductDao productDao;
    @Autowired
    private CategoryDao categoryDao;

    @Test
    public void testFindProductById() {
        Product category = productDao.findById(1L);
        assertThat(category, notNullValue());
    }

    @Test
    public void testSaveProduct() {
        Category category = categoryDao.findById(1L);
        Long id = productDao.save(new Product("TestProduct", "TestProductDescription", 100.00, 5, true, "no_image.png", category));
        Product product = productDao.findById(id);
        assertThat(product, notNullValue());
    }

    @Test
    public void testDeleteProduct() {
        Product category = productDao.findById(1L);
        assertThat(category, notNullValue());
        productDao.delete(category);
        Product category2 = productDao.findById(1L);
        assertThat(category2, nullValue());
    }

    @Test
    public void testFindAllProducts() {
        List<Product> allProducts = productDao.findAll();
        List<String> list = allProducts.stream().map(e -> e.getName()).collect(Collectors.toList());
        assertThat(list, hasSize(5));
        assertThat(list, containsInAnyOrder("50/FIFTY", "mr-big", "NO. 1", "T5", "Tride-NBL"));
    }
}

