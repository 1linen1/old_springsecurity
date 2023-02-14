package com.atyjh.auth;

import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.atyjh.auth.entity.User;
import com.atyjh.auth.servie.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AuthProjectApplicationTests {

    @Autowired
    private IUserService userService;

    @Test
    void contextLoads() {
        User user = new User();
        user.setName("admin2");
        user.setPassword(SecureUtil.md5("admin2"));
        user.setRole("0");
        System.out.println(userService.save(user));
    }

    @Test
    void test() {
        JSONArray array = JSON.parseArray("[[{\"name\":\"业务类型\",\"value\":\"开通\"},{\"name\":\"带宽\",\"value\":\"10M\"}]]");
        System.out.println(array);
    }

}
