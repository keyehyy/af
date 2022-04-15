package com.gt.af.s.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @desc 请求路路径
 * @author zhukeyan
 * @date 2022/4/11
 */

@Data
public class SysPath implements Serializable {
    private static final long serialVersionUID = 478382589280105382L;
    /**
    * 主键
    */
    private String id;
    /**
    * 请求路径
    */
    private String url;
    /**
    * 路径描述
    */
    private String url_desc;

}
