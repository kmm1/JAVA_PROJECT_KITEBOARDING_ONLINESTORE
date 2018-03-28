package com.company.service;

import com.company.entity.Address;
import com.company.entity.User;
import com.company.service.common.BaseService;

import java.util.List;

/**
 * Created by Kate M on 09.03.2018.
 */
public interface AddressService extends BaseService<Address> {
    List<Address> getAddressByUserLogin(User user);

}
