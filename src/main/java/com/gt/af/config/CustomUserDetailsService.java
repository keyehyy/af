package com.gt.af.config;

import com.gt.af.s.model.SysPermission;
import com.gt.af.s.model.SysUser;
import com.gt.af.s.service.SysPermissionService;
import com.gt.af.s.service.UserInfoService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;



@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private SysPermissionService sysPermissionService;

    public CustomUserDetailsService(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Boolean isEnabled = true; // 账户是否可用 默认可用
        Boolean idAccountNonExpired = true; // 账户是否过期 默认未过期
        Boolean isAccountNonLocked = true;// 账户是否锁定 默认未锁定
        Boolean isCredentialsNonExpired = true;// 证书（密码）是否过期
        //通过userName获取用户信息
        SysUser sysUser = userInfoService.getUserInfoByUsername(username);
        if(sysUser == null) {
            throw new UsernameNotFoundException("查询不到用户信息");
        }
        //定义权限列表.
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        //获取该用户所拥有的权限
        List<SysPermission> sysPermissions = sysPermissionService.selecPermissiontListByUser(sysUser.getId());
        // 声明用户授权
        sysPermissions.forEach(sysPermission -> {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(sysPermission.getPermission_code());
            authorities.add(grantedAuthority);
        });

        switch (sysUser.getAccount_status()) {
            case "2":
                isAccountNonLocked = false;
                break;
            case "3":
                idAccountNonExpired = false;
                break;
            case "4":
                isCredentialsNonExpired = false;
                break;
            default:
                break;
        }
        return new User(sysUser.getUsername(), sysUser.getPassword(), isEnabled,idAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired, authorities);
    }
}
