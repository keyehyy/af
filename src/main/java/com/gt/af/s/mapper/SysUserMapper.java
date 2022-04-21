package com.gt.af.s.mapper;

import com.gt.af.s.model.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface SysUserMapper{
    /**
     * @desc 查询单个用户信息
     * @author zhukeyan
     * @date 2022/4/14
     */
    SysUser selectSysUser(@Param("username") String username, @Param("id") String id);


    /**
     * @desc 查询单个用户和角色信息
     * @author zhukeyan
     * @date 2022/4/14
     */
    SysUser selectUserAndRole(@Param("username") String username, @Param("id") String id);

    /**
     * @desc 新增用户信息
     * @author zhukeyan
     * @date 2022/4/14
     */
    int insertSysUser(SysUser sysUser);

}
