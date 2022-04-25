package com.gt.af.security;

import com.alibaba.fastjson.JSONObject;
import com.gt.common.constant.ReturnCode;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * @desc 用户未登录处理
 * @author zhukeyan
 * @date 2022/4/7
 */

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
        resp.setContentType("application/json;charset=UTF-8");
        JSONObject resultObj = new JSONObject();
        resultObj.put("code", ReturnCode.userUnauthorized.getCode());
        resultObj.put("msg",ReturnCode.userUnauthorized.getMsg());
        resp.getWriter().write(resultObj.toString());
    }
}
