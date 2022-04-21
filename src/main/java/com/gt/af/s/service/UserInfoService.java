package com.gt.af.s.service;

import com.gt.af.s.mapper.SysUserMapper;
import com.gt.af.s.model.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
@Slf4j
public class UserInfoService {
    @Autowired
    private SysUserMapper sysUserMapper;

    public SysUser getUserInfoByUsername(String username){
        return sysUserMapper.selectSysUser(username,null);
    }

    public  String addUserInfo(SysUser u){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        u.setPassword(passwordEncoder.encode(u.getPassword()));
        sysUserMapper.insertSysUser(u);
        return "新增成功";
    }


}
