package com.company.dao;

import com.company.dao.common.BaseDaoImpl;
import com.company.entity.Product;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Kate M on 07.03.2018.
 */
@Repository
@Transactional
public class ProductDaoImpl extends BaseDaoImpl<Product> implements ProductDao {

    @Override
    public List<Product> getByCategoryId(Long id) {
        return getSessionFactory().getCurrentSession().createQuery("SELECT p FROM Product p where p.category.id=:id " +
                "order by p.name", Product.class)
                .setParameter("id", id)
                .getResultList();
    }
}
