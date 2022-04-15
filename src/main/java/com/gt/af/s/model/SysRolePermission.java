package com.gt.af.s.model;

import lombok.Data;

import java.io.Serializable;
/**
 * @desc 角色权限关联
 * @author zhukeyan
 * @date 2022/4/11
 */

@Data
public class SysRolePermission implements Serializable {
    private static final long serialVersionUID = 285339439019159912L;
    /**
    * 主键
    */
    private String id;
    /**
    * 角色id
    */
    private String rid;
    /**
    * 权限id
    */
    private String permission_id;

}
