package com.gt.af.security;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gt.common.constant.ReturnCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @desc 登录失败处理
 * @author zhukeyan
 * @date 2022/3/31
 */

@Component("loginFailureHandler")
@Slf4j
public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Resource
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        //这里写你登录失败后的逻辑，可加验证码验证等
        String errorInfo = "";
        String code = "0";
        if ((exception instanceof BadCredentialsException) ||
                (exception instanceof UsernameNotFoundException)) {
            code = ReturnCode.accountNotFound.getCode();
            errorInfo = ReturnCode.accountNotFound.getMsg();
        } else if (exception instanceof LockedException) {
            code = ReturnCode.accountLocked.getCode();
            errorInfo = ReturnCode.accountLocked.getMsg();
        } else if (exception instanceof CredentialsExpiredException) {
            code = ReturnCode.accountPasswordExpired.getCode();
            errorInfo = ReturnCode.accountPasswordExpired.getMsg();
        } else if (exception instanceof AccountExpiredException) {
            code = ReturnCode.accountExpired.getCode();
            errorInfo = ReturnCode.accountExpired.getMsg();
        } else if (exception instanceof DisabledException) {
            code = ReturnCode.accountDisabled.getCode();
            errorInfo = ReturnCode.accountDisabled.getMsg();
        } else {
            code = ReturnCode.accountLoginFailure.getCode();
            errorInfo = ReturnCode.accountLoginFailure.getMsg();
        }
        logger.info("登录失败原因：" + errorInfo);
        //ajax请求认证方式
        JSONObject resultObj = new JSONObject();
        resultObj.put("code", code);
        resultObj.put("msg",errorInfo);
        response.getWriter().write(resultObj.toString());
    }
}