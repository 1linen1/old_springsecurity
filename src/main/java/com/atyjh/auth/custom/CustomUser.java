package com.atyjh.auth.custom;

import org.springframework.security.core.GrantedAuthority;
import com.atyjh.auth.entity.User;

import java.util.Collection;

/**
 * 自定义用户类
 */
public class CustomUser extends org.springframework.security.core.userdetails.User {

    /**
     * 我们自己的用户实体对象，要调取用户信息时直接获取这个实体对象
     */
    private User user;

    public CustomUser(User user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getName(), user.getPassword(), authorities);
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
