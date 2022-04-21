package com.gt.af.s.service;

import com.gt.af.s.mapper.SysUserMapper;
import com.gt.af.s.model.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Transactional
@Slf4j
public class UserInfoService {
    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * @desc 查詢用戶信息
     * @author zhukeyan
     * @date 2022/4/21
     */
    public SysUser selectSysUser(String username) {
        return sysUserMapper.selectSysUser(username, null);
    }
    /**
     * @desc 查詢用戶和角色信息
     * @author zhukeyan
     * @date 2022/4/21
     */
    public SysUser selectUserAndRole(String username){
        return sysUserMapper.selectUserAndRole(username,null);
    }


    public  String addUserInfo(SysUser u){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        sdf.format(new Date());
        u.setId(sdf.format(new Date()));
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        u.setPassword(passwordEncoder.encode(u.getPassword()));
        sysUserMapper.insertSysUser(u);
        return "新增成功";
    }
}
