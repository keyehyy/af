package com.gt.af.s.controller;


import com.gt.af.s.model.SysUser;
import com.gt.af.s.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("sysUser")
@Slf4j
public class SysUserController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("getUserInfoByUsername")
    public SysUser getUserInfoByUsername(String username){
        return userInfoService.getUserInfoByUsername(username);
    }

    @RequestMapping("addUserInfo")
    public String addUserInfo(SysUser u){
        userInfoService.addUserInfo(u);
        return "注册成功";
    }


    @RequestMapping("getSessionUserInfo")
    public String addUserInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return null;
    }


}
