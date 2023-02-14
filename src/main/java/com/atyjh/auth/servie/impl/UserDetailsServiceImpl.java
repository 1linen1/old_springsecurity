package com.atyjh.auth.servie.impl;

import com.atyjh.auth.custom.CustomUser;
import com.atyjh.auth.entity.User;
import com.atyjh.auth.servie.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserInfoByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
//        //根据userid查询操作权限数据
//        List<String> userPermsList = sysMenuService.getUserButtonList(sysUser.getId());
//        //转换security要求格式数据
//        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        for (String perm:userPermsList) {
//            authorities.add(new SimpleGrantedAuthority(perm.trim())); // 配置该用户所拥有的的权限
//        }
//        return new CustomUser(sysUser, authorities);

        return new CustomUser(user, Collections.emptyList());
    }
}







