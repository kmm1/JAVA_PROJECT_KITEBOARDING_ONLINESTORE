package com.company.dao;

import com.company.dao.common.BaseDaoTest;
import com.company.entity.Review;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by Kate M on 07.03.2018.
 */
public class ReviewDaoTest extends BaseDaoTest<Review> {

    @Autowired
    private ReviewDao reviewDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private UserDao userDao;

    @Test
    public void testFindReviewById() {
        getTestDataImporter().importTestData();
        Review review = reviewDao.findById(1L);
        assertThat(review, notNullValue());
        assertThat(review.getContent(), is("The best kite ever"));
    }

    @Test
    public void testSaveReview() {
        getTestDataImporter().importTestData();
        Long id = reviewDao.save(new Review(userDao.findById(1L), productDao.findById(1L), "testComment", LocalDateTime.now()));
        Review category = reviewDao.findById(id);
        assertThat(category, notNullValue());
    }

    @Test
    public void testDeleteReview() {
        getTestDataImporter().importTestData();
        Review review = reviewDao.findById(1L);
        assertThat(review, notNullValue());
        reviewDao.delete(review);
        Review review2 = reviewDao.findById(1L);
        assertThat(review2, nullValue());
    }

    @Test
    public void testFindAllReview() {
        getTestDataImporter().importTestData();
        List<Review> allReviews = reviewDao.findAll();
        List<String> list = allReviews.stream().map(e -> e.getContent()).collect(Collectors.toList());
        assertThat(list, hasSize(1));
        assertThat(list, containsInAnyOrder("The best kite ever"));
    }

}
