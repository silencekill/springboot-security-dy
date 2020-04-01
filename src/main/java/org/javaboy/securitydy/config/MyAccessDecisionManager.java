package org.javaboy.securitydy.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class MyAccessDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        // myFilter中返回的角色集合 循环
        for(ConfigAttribute attribute:collection){
            // 如果是自定义的角色
            if("ROLE_login".equals(attribute.getAttribute())){
                // 判断是否是未登录的访问
                if(authentication instanceof AnonymousAuthenticationToken){
                    // 是的话抛异常信息
                    throw new AccessDeniedException("非法请求");
                }else{
                    // 允许访问接口
                    return;
                }
            }
            // 不是自定义的角色

            // 获取登录的信息的角色
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            // 循环角色
            for(GrantedAuthority authority:authorities){
                // 如果有角色里有 与 myFilter中返回的集合中的一项匹配的
                if(authority.getAuthority().equals(attribute.getAttribute())){
                    // 允许访问接口
                    return;
                }
            }
        }
        throw new AccessDeniedException("非法请求");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
