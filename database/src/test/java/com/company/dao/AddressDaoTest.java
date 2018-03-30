package com.company.dao;

import com.company.dao.common.BaseDaoTest;
import com.company.entity.Address;
import com.company.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by Kate M on 07.03.2018.
 */
public class AddressDaoTest extends BaseDaoTest<Address> {

    @Autowired
    private AddressDao addressDao;
    @Autowired
    private UserDao userDao;

    @Test
    public void testFindAddressById() {
        Address address = addressDao.findById(2L);
        assertThat(address, notNullValue());
        assertThat(address.getUser().getName(), is("user2"));
    }

    @Test
    public void testSaveAddress() {
        User user = userDao.findById(1L);
        Long id = addressDao.save(new Address());
        Address address = addressDao.findById(id);
        assertThat(address, notNullValue());
    }

    @Test
    public void testDeleteAddress() {
        final Long id = 1L;
        Address address = addressDao.findById(id);
        assertThat(address, notNullValue());
        addressDao.delete(address);
        Address profile2 = addressDao.findById(id);
        assertThat(profile2, nullValue());
    }
}

