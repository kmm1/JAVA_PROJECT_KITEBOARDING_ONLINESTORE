package com.company.service;

import com.company.dao.UserDao;
import com.company.dao.common.BaseDao;
import com.company.entity.User;
import com.company.service.common.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Kate M on 05.03.2018.
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {


    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    protected BaseDao<User> getDao() {
        return userDao;
    }

    @Override
    public User getUserByName(String name) {
        return userDao.getUserByName(name);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User foundUser = userDao.getUserByName(s);
        if (foundUser == null) {
            throw new UsernameNotFoundException("Can't find user by provided name");
        }
        return new org.springframework.security.core.userdetails.User(foundUser.getName(),
                foundUser.getPassword(), getUserAuthority(foundUser));
    }

    private Set<GrantedAuthority> getUserAuthority(User user) {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(String.valueOf(user.getRole())));
        return grantedAuthorities;
    }
}
