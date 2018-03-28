package com.company.service;

import com.company.entity.Product;
import com.company.entity.Review;
import com.company.service.common.BaseService;

import java.util.List;

/**
 * Created by Kate M on 09.03.2018.
 */
public interface ReviewService extends BaseService<Review> {
    List<Review> getAllReviewByProduct(Product product);
}
