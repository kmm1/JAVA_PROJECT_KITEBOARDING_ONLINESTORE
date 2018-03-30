package com.company.dao;


import com.company.dao.common.BaseDaoTest;
import com.company.entity.Category;
import com.company.entity.EnumCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Unit test for simple App.
 */

public class CategoryDaoTest extends BaseDaoTest<Category> {

    @Autowired
    private CategoryDao categoryDao;

    @Test
    public void testFindCategoryById() {
        Category category = categoryDao.findById(1L);
        assertThat(category, notNullValue());
        assertThat(category, instanceOf(Category.class));
        assertThat(category.getEnumCategory(), is(EnumCategory.KITE));
    }

    @Test
    public void testSaveCategory() {
        Long id = categoryDao.save(new Category(EnumCategory.TEST));
        Category category = categoryDao.findById(id);
        assertThat(category, notNullValue());
        assertThat(category, instanceOf(Category.class));
        assertThat(category.getEnumCategory(), is(EnumCategory.TEST));
    }

    @Test
    public void testDeleteCategory() {
        Long id = categoryDao.save(new Category(EnumCategory.TEST));
        Category category = categoryDao.findById(id);
        assertThat(category, notNullValue());
        categoryDao.delete(category);
        assertThat(categoryDao.findById(id), nullValue());
    }

    @Test
    public void testFindAllCategory() {
        List<Category> allCategories = categoryDao.findAll();
        assertThat(allCategories, hasSize(4));
        List<EnumCategory> list = allCategories.stream().map(e -> e.getEnumCategory()).collect(Collectors.toList());
        assertThat(list, hasSize(4));
        assertThat(list, containsInAnyOrder(EnumCategory.KITE, EnumCategory.KITEBOARD, EnumCategory.SNOWBOARD, EnumCategory.ACCESSORY));
    }
}