package com.gt.af.s.service;

import com.gt.af.s.mapper.SysPermissionMapper;
import com.gt.af.s.model.SysPermission;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class SysPermissionService {
    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    /**
     * 查询权限列表
     * @param userId
     * @return 对象列表
     */
    public List<SysPermission> selecPermissiontListByUser(String userId){
        return sysPermissionMapper.selecPermissiontListByUser(userId);
    }

    public List<SysPermission> selectPermissionListByPath(String url){
        return sysPermissionMapper.selectPermissionListByPath(url);
    }





}
