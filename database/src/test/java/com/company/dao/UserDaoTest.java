package com.company.dao;

import com.company.dao.common.BaseDaoTest;
import com.company.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by Kate M on 07.03.2018.
 */
public class UserDaoTest extends BaseDaoTest<User> {

    @Autowired
    private UserDao userDao;

    @Test
    public void testFindUserById() {
//        getTestDataImporter().importTestData();
//        User user = userDao.findById(1L);
//        assertThat(user, notNullValue());
//        assertThat(user.getName(), is("admin"));
    }

    @Test
    public void testSaveUser() {
//        getTestDataImporter().importTestData();
//        Long id = userDao.save(new User());
//        User user = userDao.findById(id);
//        assertThat(user, notNullValue());
    }

    @Test
    public void testDeleteUser() {
//        getTestDataImporter().importTestData();
//        Long id = userDao.save(new User());
//        User user = userDao.findById(id);
//        assertThat(user, notNullValue());
//        userDao.delete(user);
//        User user2 = userDao.findById(id);
//        assertThat(user2, nullValue());
    }

    @Test
    public void testFindAllUser() {
//        getTestDataImporter().importTestData();
//        List<User> allUsers = userDao.findAll();
//        assertThat(allUsers, hasSize(3));
//        List<String> list = allUsers.stream().map(e -> e.getName()).collect(Collectors.toList());
//        assertThat(list, hasSize(3));
//        assertThat(list, containsInAnyOrder("admin", "user1", "user2"));
    }

    @Test
    public void testFindUserByName() {
//        getTestDataImporter().importTestData();
//        User user = userDao.getUserByName("user1");
//        assertThat(user.getName(), is("user1"));
    }

    @Test
    public void testFindUserByNameExpectingNull() {
//        getTestDataImporter().importTestData();
//        User user = userDao.getUserByName("");
//        System.out.println("-------------------------------------------");
//        System.out.println("------------------------" + user);
//        System.out.println("-------------------------------------------");
//        assertThat(user, nullValue());
    }

    @Test
    public void testFindUserByEmail() {
//        getTestDataImporter().importTestData();
//        User user = userDao.getUserByEmail("km@gmail.com");
//        assertThat(user.getEmail(), is("km@gmail.com"));
    }

    @Test
    public void testFindUserByEmailExpectingNull() {
//        getTestDataImporter().importTestData();
//        User user = userDao.getUserByEmail("");
//        System.out.println("-------------------------------------------");
//        System.out.println("------------------------" + user.getEmail());
//        System.out.println("-------------------------------------------");
//        assertThat(user, nullValue());
    }

}
