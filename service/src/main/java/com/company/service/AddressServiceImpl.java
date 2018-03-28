package com.company.service;

import com.company.dao.AddressDao;
import com.company.dao.common.BaseDao;
import com.company.entity.Address;
import com.company.entity.User;
import com.company.service.common.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Kate M on 21.03.2018.
 */
@Service
public class AddressServiceImpl extends BaseServiceImpl<Address> implements AddressService {

    private final AddressDao addressDao;

    @Autowired
    public AddressServiceImpl(AddressDao addressDao) {
        this.addressDao = addressDao;
    }

    @Override
    protected BaseDao<Address> getDao() {
        return addressDao;
    }


    @Override
    public List<Address> getAddressByUserLogin(User user) {
        return addressDao.getAddressByUserLogin(user);
    }
}
