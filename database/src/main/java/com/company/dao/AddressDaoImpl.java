package com.company.dao;

import com.company.dao.common.BaseDaoImpl;
import com.company.entity.Address;
import com.company.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Kate M on 21.03.2018.
 */
@Repository
public class AddressDaoImpl extends BaseDaoImpl<Address> implements AddressDao {

    @Override
    public List<Address> getAddressByUserLogin(User user) {
        return getSessionFactory().getCurrentSession().createQuery("SELECT a FROM Address a where a.user.id=:id", Address.class)
                .setParameter("id", user.getId()).getResultList();
    }
}
