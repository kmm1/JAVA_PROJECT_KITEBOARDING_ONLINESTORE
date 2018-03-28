package com.company.dao;

import com.company.dao.common.BaseDaoImpl;
import com.company.entity.Product;
import com.company.entity.Review;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Kate M on 07.03.2018.
 */
@Repository
public class ReviewDaoImpl extends BaseDaoImpl<Review> implements ReviewDao {

    @Override
    public List<Review> getAllReviewByProduct(Product product) {
        return getSessionFactory().getCurrentSession().createQuery("SELECT r FROM Review r where r.product.id=:id " +
                "order by r.localDateTime", Review.class)
                .setParameter("id", product.getId())
                .getResultList();
    }
}
