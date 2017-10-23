package com.yuan.common;

import com.yuan.common.exception.MessageCodes;
import com.yuan.common.exception.ValidationException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.FailedLoginException;
import java.util.Locale;

@RestController
@ControllerAdvice
public class GlobalControllerExceptionHandler {

    public static final Logger logger = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

    @Autowired
    private ApplicationContext applicationContext;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    @ResponseBody
    public Result handleBaseException(ValidationException e) {
        String desc = applicationContext.getMessage(e.getCode(), e.getArgs(), e.getMsg(), Locale.getDefault());
        if (StringUtils.isEmpty(desc)) {
            logger.info("can not find desc of code:" + e.getCode());
            desc = e.getCode();
        }
        return new Result(e.getCode(), desc);
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AuthorizationException.class)
    @ResponseBody
    public Result handleAuthorizationException(Throwable e) {
        String code = MessageCodes.AUTH_PERMISSION;
        String message = applicationContext.getMessage(code, null, Locale.getDefault());
        return new Result(code, message);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthenticationException.class)
    @ResponseBody
    public Result handleAuthenticationException(Throwable e) {
        String code = MessageCodes.AUTH_TOKEN;
        String message = applicationContext.getMessage(code, null, Locale.getDefault());
        return new Result(code, message);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(FailedLoginException.class)
    @ResponseBody
    public Result handleFailedLoginException(FailedLoginException e) {
        String code = MessageCodes.AUTH_ACCOUNT_PASSWORD_WRONG;
        String message = applicationContext.getMessage(code, null, Locale.getDefault());
        return new Result(code, message);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public Result handleBaseException(IllegalArgumentException e) {
        String code = MessageCodes.REQUEST_ARGUMENT;
        String message = e.getMessage();
        if (StringUtils.isEmpty(message)) {
            message = "请求参数错误";
        }
        String desc = applicationContext.getMessage(message, null, null, Locale.getDefault());
        if (desc != null) {
            code = message;
            message = desc;
        }
        return new Result(code, message);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public Result handleBaseException(Throwable e) {
        logger.warn("", e);
        return new Result(MessageCodes.INTERNAL_SERVER_ERROR, "服务器内部错误");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        if (StringUtils.isEmpty(message)) {
            message = "请求数据格式错误";
        }
        return new Result(MessageCodes.REQUEST_ARGUMENT, message);
    }
}
