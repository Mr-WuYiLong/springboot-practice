package com.wyl.springbootshiro.shiro;

import cn.hutool.json.JSONUtil;
import com.wyl.springbootshiro.Util.JwtUtil;
import com.wyl.springbootshiro.dao.PermissionDao;
import com.wyl.springbootshiro.dao.RoleDao;
import com.wyl.springbootshiro.dao.RolePermissionDao;
import com.wyl.springbootshiro.dao.UserDao;
import com.wyl.springbootshiro.exception.InvalidTokenException;
import com.wyl.springbootshiro.model.Permission;
import com.wyl.springbootshiro.model.Role;
import com.wyl.springbootshiro.model.RolePermission;
import com.wyl.springbootshiro.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.pam.UnsupportedTokenException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName CustomRealm
 * @Description
 * @Author yilongwu
 * @DATE 2020-03-11 15:06
 * @Version 1.0.0
 **/
@Slf4j
public class CustomRealm extends AuthorizingRealm {

    @Resource(name = "userDao")
    private UserDao userDao;

    @Resource(name = "rolePermissionDao")
    private RolePermissionDao rolePermissionDao;

    @Resource(name = "permissionDao")
    private PermissionDao permissionDao;

    @Resource(name = "roleDao")
    private RoleDao roleDao;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("**************authenticationToken->{}", authenticationToken);
        if (authenticationToken.getPrincipal() == null) {
            return null;
        }
        String token = authenticationToken.getPrincipal().toString();
        // 获得用户名
        String username = JwtUtil.validateToken(token);
        // token异常
        if (StringUtils.isBlank(username)) {
            throw new InvalidTokenException();
        }

        User user = userDao.findByUsername(username);
        // 账户异常
        if (user == null) {
            throw new UnknownAccountException();
        }

        // 认证信息
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username, token, getName());
        log.info("**************返回认证结果->{}", JSONUtil.toJsonStr(simpleAuthenticationInfo));
        return simpleAuthenticationInfo;
    }

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("**********principalCollection->{}", principalCollection);
        // 获得用户名
        String username = principalCollection.getPrimaryPrincipal().toString();
//        String username = JwtUtil.validateToken(token);
        User user = userDao.findByUsername(username);
        // 授权信息
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // 添加角色
        Role role = roleDao.findById(user.getRoleId()).get();
        simpleAuthorizationInfo.addRole(role.getRoleName());
        // 添加权限
        List<RolePermission> rolePermissionListList = rolePermissionDao.findByRoleId(role.getId());
        rolePermissionListList.stream()
                .map(rolePermission -> permissionDao.findById(rolePermission.getPermissionId()).get())
                .map(Permission::getAction)
                .forEach(simpleAuthorizationInfo::addStringPermission);

        log.info("*********返回授权结果->{}", JSONUtil.toJsonStr(simpleAuthorizationInfo));
        return simpleAuthorizationInfo;

    }

}
