package com.company.service;

import com.company.entity.User;
import com.company.service.common.BaseService;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by Kate M on 09.03.2018.
 */
public interface UserService extends BaseService<User>, UserDetailsService {

    User getUserByName(String name);

    User getUserByEmail(String email);

}
