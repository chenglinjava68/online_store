package com.yuan.shiro;

import com.yuan.models.manager.Manager;
import com.yuan.service.RedisKeyPrefix;
import com.yuan.service.manager.ManagerService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
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
public class ManagerRealm extends AuthorizingRealm {

    private Logger logger = LoggerFactory.getLogger(ManagerRealm.class);

    @Resource(name = "stringRedisTemplate")
    private ValueOperations<String, String> redis;

    @Resource
    private ManagerService managerService;

    public String getName() {
        return "redisRealm";
    }

    public boolean supports(AuthenticationToken token) {
        return token != null && Token.class.isAssignableFrom(token.getClass());
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws
            AuthenticationException {
        final Token Token = (Token) authenticationToken;
        final String tokenString = Token.getToken();
        final String managerId = redis.get(RedisKeyPrefix.buildTokenToManagerId(tokenString));
        if (!StringUtils.isEmpty(managerId)) {
            return new SimpleAuthenticationInfo(managerId, tokenString, getName());
        }
        return null;
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        final String managerId = (String) principals.getPrimaryPrincipal();
        if (!StringUtils.isEmpty(managerId)) {
            Manager manager = managerService.getManager(Integer.valueOf(managerId));
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            logger.info("--------------------name：{} ; account：{} ; role：{}", manager.getManagerName(),
                    manager.getAccount(), manager.getManagerRole());
            info.addRole(manager.getManagerRole() + "");
            return info;
        } else {
            return null;
        }
    }


}
