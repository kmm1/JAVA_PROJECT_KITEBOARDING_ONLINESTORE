package com.company.dao;

import com.company.dao.common.BaseDao;
import com.company.entity.Address;
import com.company.entity.User;

import java.util.List;

/**
 * Created by Kate M on 09.03.2018.
 */
public interface AddressDao extends BaseDao<Address> {
    List<Address> getAddressByUserLogin(User user);

}
