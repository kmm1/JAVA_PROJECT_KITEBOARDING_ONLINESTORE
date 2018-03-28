package com.company.dao;

import com.company.dao.common.BaseDao;
import com.company.entity.Product;
import com.company.entity.Review;

import java.util.List;

/**
 * Created by Kate M on 09.03.2018.
 */
public interface ReviewDao extends BaseDao<Review> {
    List<Review> getAllReviewByProduct(Product product);
}
