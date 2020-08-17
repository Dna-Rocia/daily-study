package com.daily.shiro.auth;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * Created by idea
 * description :
 *
 * @author Loyaill
 * @version 2.0.0
 * CreateDate 2018-05-07-16:46
 * @since 1.8JDK
 */
public class CredentialsMatcher extends SimpleCredentialsMatcher {


    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String password = new String(usernamePasswordToken.getPassword());
//		System.out.println("+++++++++++ 密码:" +password);
        String dbPassword = (String) info.getCredentials();
//		System.out.println("----------- db密码："+dbPassword);
        return this.equals(password, dbPassword);
    }
}
