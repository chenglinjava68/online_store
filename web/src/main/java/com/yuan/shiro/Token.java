package com.yuan.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author joryun ON 2017/10/22.
 */
public class Token implements AuthenticationToken {

    private String token;

    public Token(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public Object getPrincipal() {
        return getToken();
    }

    public Object getCredentials() {
        return getToken();
    }
}
