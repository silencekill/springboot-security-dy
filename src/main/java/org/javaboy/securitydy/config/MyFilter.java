package org.javaboy.securitydy.config;

import org.javaboy.securitydy.entity.Menu;
import org.javaboy.securitydy.entity.Role;
import org.javaboy.securitydy.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

@Component
public class MyFilter implements FilterInvocationSecurityMetadataSource {
    //用于路径匹配验证
    AntPathMatcher pathMatcher = new AntPathMatcher();

    @Autowired
    MenuService menuService;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        // 每次请求都会走这个方法
        // 强转之后获取请求的url
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        // 查询所有的菜单信息数据加入缓存
        List<Menu> allMenu = menuService.getAllMenu();
        // 判断此url地址需要哪些角色
        for(Menu menu:allMenu){
            // 循环菜单，菜单的路径与此次请求的路径匹配
            if(pathMatcher.match(menu.getPatten(),requestUrl)){
                // 获取可以访问此菜单的所有角色
                List<Role> roleList = menu.getRoleList();
                String[] roleStr = new String[roleList.size()];
                // 循环角色，将角色名转化为数组
                for (int i = 0; i < roleList.size(); i++) {
                    roleStr[i]=roleList.get(i).getName();
                }
                //返回集合
                return SecurityConfig.createList(roleStr);
            }
        }
        //否则返回自定义角色
        return SecurityConfig.createList("ROLE_login");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
