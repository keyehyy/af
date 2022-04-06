package com.gt.af.security;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ClassName : AuthenticationAccessDeniedHandler
 * @Description : security用户权限不足时的处理
 * @Author : CJH
 * @Date: 2020-08-31 16:59
 */
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest req, HttpServletResponse resp, AccessDeniedException e) throws IOException, ServletException {
        JSONObject resultObj = new JSONObject();
        resultObj.put("code", HttpServletResponse.SC_FORBIDDEN);
        resultObj.put("msg","权限不足,请联系管理员!");
        resp.getWriter().write(resultObj.toString());
    }
}


