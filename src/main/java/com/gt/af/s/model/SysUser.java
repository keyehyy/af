package com.gt.af.s.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysUser implements Serializable {
    private static final long serialVersionUID = 1L;
    public String id;
    public String username;
    public String password;
    private Date last_login_time;//上一次登录时间
    private Date create_time;//创建时间
    private Date update_time;//修改时间
    private String create_user;//创建人
    private String update_user;//修改人
    private String account_status;//账户状态（1：正常2：账户锁定3：账户过期 4:密码或证书过期）
}

