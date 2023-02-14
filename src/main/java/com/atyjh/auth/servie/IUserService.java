package com.atyjh.auth.servie;

import com.atyjh.auth.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

public interface IUserService extends IService<User> {
    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名称
     * @return 用户信息
     */
    User getUserInfoByUsername(String username);
}
