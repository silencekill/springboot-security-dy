package org.javaboy.securitydy;

import org.javaboy.securitydy.entity.Menu;
import org.javaboy.securitydy.service.MenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SecurityDyApplicationTests {

    @Autowired
    MenuService menuService;
    @Test
    public void contextLoads() {
        List<Menu> allMenu = menuService.getAllMenu();
        System.out.println(123);
    }

}
