package com.gt.af.s.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysRole implements Serializable {
    private static final long serialVersionUID = -15181983212501302L;
    /**
     * 角色id
     */
    private String id;
    /**
     * 角色名称
     */
    private String role_name;
    /**
     * 角色描述
     */
    private String role_desc;
}
