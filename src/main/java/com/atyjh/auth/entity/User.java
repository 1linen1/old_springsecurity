package com.atyjh.auth.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigInteger;

/**
 * Copyright: Copyright(c) 2022 iwhalecloud
 * <p>
 * 类说明：TODO
 * <p>
 * 类名称: User.java
 *
 * @author huang.yijie
 * 时间: 2023/2/11 20:24
 * <p>
 * Modification History:
 * Date Author Version Description
 * ------------------------------------------------------------
 * @version v1.0.0
 */
@Data
@TableName("User")
public class User {
    private BigInteger id;
    private String name;
    private String password;
    private String role;
    private int age;
    private String email;
}
