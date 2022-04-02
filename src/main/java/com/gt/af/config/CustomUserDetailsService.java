package com.gt.af.config;

import com.gt.af.s.model.UserInfo;
import com.gt.af.s.service.UserInfoService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author qt
 * @Date 2021/3/25
 * @Description
 */

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private PasswordEncoder passwordEncoder;

    public CustomUserDetailsService(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /**
         * 1/通过userName 获取到userInfo信息
         * 2/通过User（UserDetails）返回details。
         */
        //通过userName获取用户信息
        UserInfo userInfo = userInfoService.getUserInfoByUsername(username);
        if(userInfo == null) {
            throw new UsernameNotFoundException("查询不到用户信息");
        }
        //定义权限列表.
        List<GrantedAuthority> authorities = new ArrayList<>();
        // 用户可以访问的资源名称（或者说用户所拥有的权限） 注意：必须"ROLE_"开头
        authorities.addAll(userInfo.getAuthorities());
        User userDetails = new User(userInfo.getUsername(),passwordEncoder.encode(userInfo.getPassword()),authorities);
        return userDetails;
    }
}
