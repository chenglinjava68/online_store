package com.yuan.shiro;

import com.alibaba.fastjson.JSON;
import com.yuan.common.Result;
import com.yuan.common.exception.MessageCodes;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author joryun ON 2017/10/22.
 */
public class ManagerFilter extends AuthenticationFilter {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(ManagerFilter.class);

    @Override
    protected boolean preHandle(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (request.getMethod().equals(RequestMethod.OPTIONS.name())) {
            response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
            response.addHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
            response.addHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
            response.addHeader("Access-Control-Max-Age","18000");
            response.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        String token = getToken(request);
        if (StringUtils.isEmpty(token)) {
            printUnauthorized(MessageCodes.AUTH_TOKEN_EMPTY, WebUtils.toHttp(response));
            return false;
        }
        boolean loginSuccess = login(new Token(token));
        if (!loginSuccess) {
            printUnauthorized(MessageCodes.AUTH_TOKEN, WebUtils.toHttp(response));
        }
        return loginSuccess;
    }

    private String getToken(ServletRequest request) {
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        String token = null;
        if (!StringUtils.isEmpty(authorizationHeader) && authorizationHeader.startsWith("token")) {
            token = getToken(authorizationHeader);
        }
        return token;
    }

    private void printUnauthorized(String messageCode, HttpServletResponse response) {
        final Result result = new Result(messageCode, HttpStatus.UNAUTHORIZED.getReasonPhrase());
        String content = JSON.toJSONString(result);
        response.setContentType("application/json");
        response.setContentLength(content.length());
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        try {
            final PrintWriter writer = response.getWriter();
            writer.write(content);
        } catch (IOException e) {
            logger.warn("", e);
        }
    }

    private boolean login(Token token) {
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            return true;
        } catch (AuthenticationException e) {
            return false;
        }
    }

    private String getToken(String authorizationHeader) {
        if (authorizationHeader == null) {
            return null;
        }
        String[] authTokens = authorizationHeader.split(" ");
        if (authTokens.length < 2) {
            return null;
        }
        return authTokens[1];
    }
}
