package com.gt.af.s.controller;


import com.gt.af.s.model.UserInfo;
import com.gt.af.s.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("userInfo")
@Slf4j
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("getUserInfoByUsername")
    public UserInfo getUserInfoByUsername(String username){
        return userInfoService.getUserInfoByUsername(username);
    }

    @RequestMapping("addUserInfo")
    public int addUserInfo(UserInfo u){
        return userInfoService.addUserInfo(u);
    }

}
