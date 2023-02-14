package com.atyjh.auth.dto;

import lombok.Data;

/**
 * Copyright: Copyright(c) 2022 iwhalecloud
 * <p>
 * 类说明：TODO
 * <p>
 * 类名称: LoginReq.java
 *
 * @author huang.yijie
 * 时间: 2023/2/11 22:11
 * <p>
 * Modification History:
 * Date Author Version Description
 * ------------------------------------------------------------
 * @version v1.0.0
 */
@Data
public class UserLoginReq {
    private String name;
    private String password;
}
