package com.yuan.controllers;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;

/**
 * @author joryun ON 2017/10/22.
 */
public class UserHelper {

    public static Integer getUserId() {
        final Subject subject = SecurityUtils.getSubject();
        final Object userId = subject.getPrincipal();
        if (userId == null) {
            throw new AuthenticationException();
        }
        return Integer.parseInt(userId.toString());
    }

}
