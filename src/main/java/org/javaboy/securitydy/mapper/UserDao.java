package org.javaboy.securitydy.mapper;

import org.javaboy.securitydy.entity.Role;
import org.javaboy.securitydy.entity.User;

import java.util.List;

public interface UserDao {
    User loadUserByUsername(String username);

    List<Role> getUserRolesById(Integer id);
}
