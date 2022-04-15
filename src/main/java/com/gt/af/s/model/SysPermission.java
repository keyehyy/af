package com.gt.af.s.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @desc 权限表
 * @author zhukeyan
 * @date 2022/4/8
 */

@Data
public class SysPermission implements Serializable {
    private static final long serialVersionUID = -71969734644822184L;
    //主键id
    private Integer id;
    //权限code
    private String permission_code;
    //权限名
    private String permission_name;
}