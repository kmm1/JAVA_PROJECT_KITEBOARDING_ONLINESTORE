package com.company.dao;

import com.company.dao.common.BaseDaoTest;
import com.company.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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

    @Bean
    public static PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Test
    public void testFindUserById() {
        User user = userDao.findById(1L);
        assertThat(user, notNullValue());
        assertThat(user.getName(), is("admin"));
    }

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setName("test");
        user.setEmail("test@email.com");
        user.setPassword(encoder().encode("test"));
        Long id = userDao.save(user);
        assertThat(userDao.findById(id), notNullValue());
        assertThat(encoder().matches("test", userDao.findById(id).getPassword()), is(true));
    }

    @Test
    public void testDeleteUser() {
        Long id = 1L;
        User user = userDao.findById(id);
        assertThat(user, notNullValue());
        userDao.delete(user);
        assertThat(userDao.findById(id), nullValue());
    }

    @Test
    public void testFindAllUser() {
        List<User> allUsers = userDao.findAll();
        assertThat(allUsers, hasSize(3));
        List<String> list = allUsers.stream().map(e -> e.getName()).collect(Collectors.toList());
        assertThat(list, hasSize(3));
        assertThat(list, containsInAnyOrder("admin", "user1", "user2"));
    }

    @Test
    public void testFindUserByName() {
        User user = userDao.getUserByName("user1");
        assertThat(user.getName(), is("user1"));
    }

    @Test
    public void testFindUserByNameEmptyValue() {
        User user = userDao.getUserByName("");
        assertThat(user, notNullValue());
    }

    @Test
    public void testFindUserByEmail() {
        User user = userDao.getUserByEmail("km@gmail.com");
        assertThat(user.getEmail(), is("km@gmail.com"));
    }

    @Test
    public void testFindUserByEmailEmptyValue() {
        User user = userDao.getUserByEmail("");
        assertThat(user, notNullValue());
    }
}
