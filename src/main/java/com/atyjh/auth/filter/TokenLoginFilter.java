package com.atyjh.auth.filter;

import com.atyjh.auth.custom.CustomUser;
import com.atyjh.auth.dto.UserLoginReq;
import com.atyjh.auth.utils.JwtHelper;
import com.atyjh.auth.utils.ResponseUtil;
import com.atyjh.auth.utils.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthorityAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录过滤器，继承UsernamePasswordAuthenticationFilter，对用户名密码进行登录校验
 */
public class TokenLoginFilter extends UsernamePasswordAuthenticationFilter {

//    @Autowired
//    AuthenticationManager authenticationManager;

    public TokenLoginFilter(AuthenticationManager authenticationManager) {
        this.setAuthenticationManager(authenticationManager);
        this.setPostOnly(false);
        //指定登录接口及提交方式，可以指定任意路径
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/user/login","POST"));
    }
//    public TokenLoginFilter() {
//        this.setAuthenticationManager(authenticationManager);
//        this.setPostOnly(false);
//        //指定登录接口及提交方式，可以指定任意路径
//        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/user/login","POST"));
//    }

    /**
     * 登录认证
     *
     * @param req
     * @param res
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException {
        try {
            UserLoginReq loginVo = new ObjectMapper().readValue(req.getInputStream(), UserLoginReq.class);

            Authentication authenticationToken = new UsernamePasswordAuthenticationToken(loginVo.getName(), loginVo.getPassword());
//            authenticate方法会调用loadUserByUsername
            return this.getAuthenticationManager().authenticate(authenticationToken);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 登录成功
     *
     * @param request
     * @param response
     * @param chain
     * @param auth
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        // 获取认证对象
        CustomUser customUser = (CustomUser) auth.getPrincipal();

        // 生成token
        String token = JwtHelper.createToken(customUser.getUser().getId(), customUser.getUser().getName());

        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        ResponseUtil.out(response, Result.success(map, "登陆成功"));
    }

    /**
     * 登录失败
     *
     * @param request
     * @param response
     * @param e
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException e) throws IOException, ServletException {

        if(e.getCause() instanceof RuntimeException) {
            ResponseUtil.out(response, Result.error(401, e.getMessage()));
        } else {
            logger.error(e.getMessage());
            ResponseUtil.out(response, Result.error("未知错误!请联系管理员"));
        }
    }
}
