package org.javaboy.securitydy.service;

import org.javaboy.securitydy.entity.Menu;
import org.javaboy.securitydy.mapper.MenuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {
    @Autowired
    MenuDao menuDao;
    public List<Menu> getAllMenu(){
        return menuDao.getAllMenu();
    }
}
