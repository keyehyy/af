package com.gt.af.s.service;

import com.gt.af.s.mapper.UserInfoMapper;
import com.gt.af.s.model.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    public UserInfo getUserInfoByUsername(String username){
        return userInfoMapper.selectUserInfo(username);
    }

    public  int addUserInfo(UserInfo u){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        u.setPassword(passwordEncoder.encode(u.getPassword()));
        return userInfoMapper.insertSelective(u);
    }
}
