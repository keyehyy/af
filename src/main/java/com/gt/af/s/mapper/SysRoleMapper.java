package com.gt.af.s.mapper;
import com.gt.af.s.model.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * (SysRole)表数据库访问层
 * @author 
 * @since 2022-04-11 17:58:12
 */
@Mapper
public interface SysRoleMapper {

    /**
     * 通过ID查询单条数据
     * @param id 主键
     * @return 实例对象
     */
    SysRole querySysRoleById(String id);

    /**
     * 查询列表
     * @param map 实例对象
     * @return 对象列表
     */
    List<SysRole> querySysRoleAll(Map<String,Object> map);

    /**
     * 新增数据
     * @param sysRole 实例对象
     * @return 影响行数
     */
    int insertSysRole(SysRole sysRole);

    /**
     * 修改数据
     * @param sysRole 实例对象
     * @return 影响行数
     */
    int updateSysRole(SysRole sysRole);

    /**
     * 通过主键删除数据
     * @param id 主键
     * @return 影响行数
     */
    int deleteSysRoleById(String id);

}
