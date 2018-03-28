package com.company.service;

import com.company.entity.Category;
import com.company.entity.EnumCategory;
import com.company.service.common.BaseServiceTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

/**
 * Created by Kate M on 07.03.2018.
 */
public class CategoryServiceTest extends BaseServiceTest<Category> {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void testFindAllCategory() {
        getTestDataImporter().importTestData();
        List<Category> allCategories = categoryService.findAll();
        List<EnumCategory> list = allCategories.stream().map(Category::getEnumCategory).collect(Collectors.toList());
        assertThat(list, hasSize(4));
        assertThat(list, containsInAnyOrder(EnumCategory.KITE, EnumCategory.KITEBOARD, EnumCategory.SNOWBOARD, EnumCategory.ACCESSORY));
    }

}