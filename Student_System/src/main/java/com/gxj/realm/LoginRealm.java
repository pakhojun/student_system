package com.gxj.realm;

import com.gxj.mapper.RoleMapper;
import com.gxj.mapper.UserMapper;
import com.gxj.pojo.Role;
import com.gxj.pojo.Userlogin;
import com.gxj.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class LoginRealm extends AuthorizingRealm {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) getAvailablePrincipal(principalCollection);

        Userlogin userlogin = userMapper.selectUserByUsername(username);

        Integer roleId = userlogin.getRole();

        Role role = roleMapper.findOne(roleId);

        Set<String> roles=new HashSet<>();

        roles.add(role.getRolename());

        AuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo(roles);

        return authorizationInfo;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        String passwords = new String((char[]) authenticationToken.getCredentials());

        Userlogin userlogin = userMapper.selectUserByUsername(username);

        if(userlogin==null){
            //没有该用户名
            throw new UnknownAccountException();
        }else if(!passwords.equals(userlogin.getPassword())){
            //密码错误
            throw new IncorrectCredentialsException();
        }

        AuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo(username,passwords,getName());

        return authenticationInfo;
    }
}
