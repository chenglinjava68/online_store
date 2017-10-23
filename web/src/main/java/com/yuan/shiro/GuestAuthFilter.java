package com.yuan.shiro;

import com.yuan.common.exception.MessageCodes;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.HttpMethod;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author joryun ON 2017/10/22.
 */
public class GuestAuthFilter extends UserFilter {
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        String token = getToken(request);
        if (StringUtils.isEmpty(token)) {
            final boolean canAccess = Objects.equals(((HttpServletRequest) request).getMethod(), HttpMethod.GET.name());
            if (!canAccess) {
                printUnauthorized(MessageCodes.AUTH_TOKEN, WebUtils.toHttp(response));
                return false;
            }
        } else {
            boolean loginSuccess = login(new Token(token));
            if (!loginSuccess) {
                printUnauthorized(MessageCodes.AUTH_TOKEN, WebUtils.toHttp(response));
                return false;
            }
        }
        return true;
    }
}
