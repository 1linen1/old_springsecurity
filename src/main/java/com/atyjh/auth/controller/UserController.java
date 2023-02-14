package com.atyjh.auth.controller;

import com.atyjh.auth.dto.UserLoginReq;
import com.atyjh.auth.entity.User;
import com.atyjh.auth.servie.IUserService;
import com.atyjh.auth.utils.JwtHelper;
import com.atyjh.auth.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

/**
 * Copyright: Copyright(c) 2022 iwhalecloud
 * <p>
 * 类说明：TODO
 * <p>
 * 类名称: UserController.java
 *
 * @author huang.yijie
 * 时间: 2023/2/11 22:00
 * <p>
 * Modification History:
 * Date Author Version Description
 * ------------------------------------------------------------
 * @version v1.0.0
 */
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody UserLoginReq req) {
        User user = userService.lambdaQuery()
                .eq(User::getName, req.getName())
                .eq(User::getPassword, req.getPassword())
                .one();
        if (user == null) {
            return Result.error("用户名或密码错误!");
        }
        String token = JwtHelper.createToken(user.getId(), user.getName());
        return Result.success(token, "登陆成功!");
    }

    @GetMapping("/{id}")
    public Result<User> getUserById(@PathVariable("id") BigInteger id) {
        User user = userService.getById(id);
        System.out.println(user);
        return Result.success(user);
    }
}
