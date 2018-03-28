package com.company.dao;

import com.company.dao.common.BaseDaoImpl;
import com.company.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Kate M on 05.03.2018.
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

    @Override
    public User getUserByName(String name) {
        List<User> list = getSessionFactory().getCurrentSession().createQuery("SELECT u FROM User u where u.name=:name")
                .setParameter("name", name)
                .getResultList();
        return list.size() > 0 ? list.get(0) : new User();
    }

    @Override
    public User getUserByEmail(String email) {
        List<User> list = getSessionFactory().getCurrentSession().createQuery("SELECT u FROM User u where u.email =:email")
                .setParameter("email", email)
                .getResultList();
        return list.size() > 0 ? list.get(0) : new User();
    }

}
