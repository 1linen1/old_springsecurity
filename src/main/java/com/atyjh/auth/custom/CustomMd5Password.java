package com.atyjh.auth.custom;

import cn.hutool.crypto.SecureUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 自定义密码组件
 */
@Component
public class CustomMd5Password implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return SecureUtil.md5(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(SecureUtil.md5(rawPassword.toString()));
    }
}
