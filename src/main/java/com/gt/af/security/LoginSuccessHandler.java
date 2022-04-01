package com.gt.af.security;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/** @Author qt  * @Date 2021/3/24
 * @Description 登录成功处理
 */
@Component("loginSuccessHandler")
@Slf4j
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Resource
    private ObjectMapper objectMapper;
    private RequestCache requestCache;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        // 获取前端传到后端的全部参数
        Enumeration enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String paraName = (String) enu.nextElement(); System.out.println("参数- " + paraName + " : " + request.getParameter(paraName));
        }
        logger.info("登录认证成功");
        //这里写你登录成功后的逻辑，可以验证其他信息，如验证码等。
        response.setContentType("application/json;charset=UTF-8");
        JSONObject resultObj = new JSONObject();
        resultObj.put("code", HttpStatus.OK.value());
        resultObj.put("msg","登录成功");
        resultObj.put("authentication",objectMapper.writeValueAsString(authentication));
        response.getWriter().write(resultObj.toString());
        this.getRedirectStrategy().sendRedirect(request, response, "/index");//重定向
    }
}

