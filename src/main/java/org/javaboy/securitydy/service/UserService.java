package org.javaboy.securitydy.service;

import org.javaboy.securitydy.entity.User;
import org.javaboy.securitydy.mapper.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserDao userDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.loadUserByUsername(username);
        if(null == user){
            throw  new UsernameNotFoundException("用户不存在");
        }
        user.setRoleList(userDao.getUserRolesById(user.getId()));
        return user;
    }
}
