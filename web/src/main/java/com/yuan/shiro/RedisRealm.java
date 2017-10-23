package com.yuan.shiro;

import com.yuan.service.RedisKeyPrefix;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * @author joryun ON 2017/10/22.
 */
@Component
public class RedisRealm extends AuthorizingRealm {

    @Resource(name = "stringRedisTemplate")
    private ValueOperations<String, String> redis;

    public String getName() {
        return "redisRealm";
    }

    private Logger logger = LoggerFactory.getLogger(RedisRealm.class);

    public boolean supports(AuthenticationToken token) {
        return token != null && Token.class.isAssignableFrom(token.getClass());
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        final Token Token = (Token) authenticationToken;
        final String tokenString = Token.getToken();
        final String userId = redis.get(RedisKeyPrefix.buildTokenToUserId(tokenString));
        logger.info("--------------------userId：{} ; Token：{} ; tokenString：{}", userId, Token, tokenString);
        if (!StringUtils.isEmpty(userId)) {
            return new SimpleAuthenticationInfo(userId, tokenString, getName());
        }
        return null;
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

}
