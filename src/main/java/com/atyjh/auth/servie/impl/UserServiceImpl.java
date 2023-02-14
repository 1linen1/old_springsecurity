package com.atyjh.auth.servie.impl;

import com.atyjh.auth.entity.User;
import com.atyjh.auth.mapper.UserMapper;
import com.atyjh.auth.servie.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * Copyright: Copyright(c) 2022 iwhalecloud
 * <p>
 * 类说明：TODO
 * <p>
 * 类名称: UserService.java
 *
 * @author huang.yijie
 * 时间: 2023/2/11 21:30
 * <p>
 * Modification History:
 * Date Author Version Description
 * ------------------------------------------------------------
 * @version v1.0.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public User getUserInfoByUsername(String username) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getName, username);
        return baseMapper.selectOne(wrapper);
    }
}
