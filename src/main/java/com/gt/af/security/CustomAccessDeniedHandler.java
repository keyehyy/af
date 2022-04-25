package com.gt.af.security;

import com.alibaba.fastjson.JSONObject;
import com.gt.common.constant.ReturnCode;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @desc 用户已登录权限不足处理
 * @author zhukeyan
 * @date 2022/4/7
 */

public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest req, HttpServletResponse resp, AccessDeniedException e) throws IOException, ServletException {
        resp.setContentType("application/json;charset=UTF-8");
        JSONObject resultObj = new JSONObject();
        resultObj.put("code", ReturnCode.insufficientPermissions.getCode());
        resultObj.put("msg",ReturnCode.insufficientPermissions.getMsg());
        resp.getWriter().write(resultObj.toString());
    }
}


