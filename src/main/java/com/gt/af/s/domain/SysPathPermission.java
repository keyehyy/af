package com.gt.af.s.domain;

import java.io.Serializable;

/**
 * @desc 路径权限关联表
 * @author zhukeyan
 * @date 2022/4/8
 */

public class SysPathPermission implements Serializable {
    private static final long serialVersionUID = -59197738311147860L;
    //主键id
    private Integer id;
    //请求路径id
    private Integer url_id;
    //权限id
    private Integer permission_id;

}