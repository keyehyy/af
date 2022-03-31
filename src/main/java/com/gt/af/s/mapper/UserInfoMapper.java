package com.gt.af.s.mapper;

import com.gt.af.s.model.UserInfo;
import tk.mybatis.mapper.common.Mapper;


@org.apache.ibatis.annotations.Mapper
public interface UserInfoMapper extends Mapper<UserInfo>{
    UserInfo selectUserInfo(String username);
}
