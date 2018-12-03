package com.baizhi.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * Created by wdwhwn on 2018/11/2.
 */
public class MyRealm extends AuthorizingRealm{
//授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String primaryPrincipal= (String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        return simpleAuthorizationInfo;
    }
// 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String principal= (String) authenticationToken.getPrincipal();
        if(principal.equals("zahngsan")){
            SimpleAuthenticationInfo simpleAuthenticationInfo;
            simpleAuthenticationInfo = new SimpleAuthenticationInfo("zhangsan","68609b8b64988c0f4def093eaa025e05", ByteSource.Util.bytes("abcd"),this.getName());
            return simpleAuthenticationInfo;

        }
        return null;
    }
}
