//package com.gt.af.security;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * @desc 退出登录处理
// * @author zhukeyan
// * @date
// */
//
//@Slf4j
//public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {
//
//    @Override
//    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        System.out.println("退出成功!");
//        //这里写你登录成功后的逻辑
//        response.setStatus(HttpStatus.OK.value());
//        response.setContentType("application/json;charset=UTF-8");
//        response.getWriter().write("退出成功!");
//    }
//}