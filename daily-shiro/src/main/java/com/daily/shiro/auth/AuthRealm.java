package com.daily.shiro.auth;

import com.daily.shiro.model.Permission;
import com.daily.shiro.model.Role;
import com.daily.shiro.model.User;
import com.daily.shiro.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by idea
 * description :
 *
 * @author Loyaill
 * @version 2.0.0
 * CreateDate 2018-05-07-16:20
 * @since 1.8JDK
 */
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    //授权 在登陆成功之后

    /**
     * 为当前登录的Subject授予角色和权限
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User user = (User) principals.fromRealm(this.getClass().getName()).iterator().next(); // 相当于从session中获取用户
        List<String> permissions = new ArrayList<>();
        List<String> roles = new ArrayList<>();
        Set<Role> roleSet = user.getRoles();
        if (CollectionUtils.isNotEmpty(roleSet)) {
            for (Role role : roleSet) {
                roles.add(role.getRname());
                Set<Permission> permissionSet = role.getPermissions();
                if (CollectionUtils.isNotEmpty(permissionSet)) {
                    for (Permission per : permissionSet) {
                        permissions.add(per.getName());
                    }
                }
            }
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissions);
        info.addRoles(roles);
        return info;
    }

    //认证登录

    /**
     * 验证当前登录的Subject
     *
     * @param token 用户名和密码的令牌
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String username = usernamePasswordToken.getUsername();
        User user = userService.findByUsername(username);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), this.getClass().getName());
        return info;
    }
}
