package com.gt.af.s.mapper;
import com.gt.af.s.model.SysPath;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 请求路径表(SysPath)表数据库访问层
 * @author 
 * @since 2022-04-11 17:55:50
 */
@Mapper
public interface SysPathMapper {
    /**
     * 查询请求路径表列表
     * @param map 实例对象
     * @return 对象列表
     */
    List<SysPath> querySysPathAll(Map<String,Object> map);

    /**
     * 新增请求路径表数据
     * @param sysPath 实例对象
     * @return 影响行数
     */
    int insertSysPath(SysPath sysPath);

    /**
     * 修改请求路径表数据
     * @param sysPath 实例对象
     * @return 影响行数
     */
    int updateSysPath(SysPath sysPath);

    /**
     * 通过主键删除请求路径表数据
     * @param id 主键
     * @return 影响行数
     */
    int deleteSysPathById(String id);

}
