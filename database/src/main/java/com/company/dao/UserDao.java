package com.company.dao;

import com.company.dao.common.BaseDao;
import com.company.entity.User;

/**
 * Created by Kate M on 09.03.2018.
 */
public interface UserDao extends BaseDao<User> {

    User getUserByName(String name);

    User getUserByEmail(String email);
}
