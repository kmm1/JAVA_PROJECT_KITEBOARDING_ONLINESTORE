package com.company.service;


import com.company.dao.ReviewDao;
import com.company.dao.common.BaseDao;
import com.company.entity.Product;
import com.company.entity.Review;
import com.company.service.common.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Kate M on 07.03.2018.
 */
@Service
public class ReviewServiceImpl extends BaseServiceImpl<Review> implements ReviewService {

    private final ReviewDao reviewDao;

    @Autowired
    public ReviewServiceImpl(ReviewDao reviewDao) {
        this.reviewDao = reviewDao;
    }


    @Override
    public List<Review> getAllReviewByProduct(Product product) {
        return reviewDao.getAllReviewByProduct(product);
    }

    @Override
    protected BaseDao<Review> getDao() {
        return reviewDao;
    }
}
