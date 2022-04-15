package com.gt.af.s.mapper;
import com.gt.af.s.model.SysPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


@Mapper
public interface SysPermissionMapper {
    /**
     * 查询用户权限列表
     * @param userId
     * @return 对象列表
     */
    List<SysPermission> selecPermissiontListByUser(String userId);

    /**
     * 根据查询权限列表
     * @param url
     * @return 对象列表
     */
    List<SysPermission> selectPermissionListByPath(String url);


    /**
     * 查询权限表列表
     * @param map 实例对象
     * @return 对象列表
     */
    List<SysPermission> querySysPermissionAll(Map<String,Object> map);

    /**
     * 新增权限表数据
     * @param sysPermission 实例对象
     * @return 影响行数
     */
    int insertSysPermission(SysPermission sysPermission);

    /**
     * 修改权限表数据
     * @param sysPermission 实例对象
     * @return 影响行数
     */
    int updateSysPermission(SysPermission sysPermission);

    /**
     * 通过主键删除权限表数据
     * @param id 主键
     * @return 影响行数
     */
    int deleteSysPermissionById(String id);

}
