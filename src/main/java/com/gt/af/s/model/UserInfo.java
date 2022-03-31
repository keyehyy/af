package com.gt.af.s.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Table(name = "user_info")
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    public String id;
    public String username;
    public String password;
    @Transient
    public String rid;//角色id
    @Transient
    public String rname;//角色名称
}

