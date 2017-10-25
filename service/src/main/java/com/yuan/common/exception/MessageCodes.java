package com.yuan.common.exception;

/**
 * @author joryun ON 2017/10/22.
 */
public class MessageCodes {

    public final static String AUTH_TOKEN = "auth.token.wrong";//token错误或已过期,请重新登录
    public final static String AUTH_TOKEN_EMPTY = "auth.token.empty";//token为空
    public final static String AUTH_USERNAME_DUPLICATE = "auth.username.duplicate";//用户名重复
    public final static String AUTH_ACCOUNT_PASSWORD_WRONG = "auth.account.password.wrong";//账号或密码错误
    public final static String AUTH_PERMISSION = "auth.permission";//权限不足

    public final static String AUTH_PICCAPTCHA_WRONG = "auth.captcha.wrong";//验证码错误
    public final static String AUTH_PICCAPTCHA_LOST = "auth.captcha.lost";//验证码已失效

    public final static String AUTH_PHOCAPTCHA_WRONG = "auth.phone.captcha.wrong";//验证码错误
    public final static String AUTH_PHOCAPTCHA_SEND_FAIL = "auth.phone.captcha.send.fail";//验证码发送失败
    public final static String AUTH_PHOCAPTCHA_LOST = "auth.phone.captcha.lost";//验证码已失效

    public final static String REQUEST_ARGUMENT = "request.argument";//请求参数错误或者参数为空
    public final static String INTERNAL_SERVER_ERROR = "server.internal";//服务器错误

    public final static String EXCEL_ERROR = "excel.error";//Excel文件错误

    //微信相关
    public final static String WECHAT_AUTHORIZE_FAILE = "wechat.auth.fail";//微信授权失败

    //用户相关
    public final static String USER_IS_NOT_EXIT = "user.is.not.exit";//用户不存在
    public final static String USER_IS_NOT_FINISH_INFORMATION = "user.is.not.finish.information";//未完成资料认证
    public final static String MOBILE_PHONE_HAS_BIND = "mobile.phone.has.bind";//手机号已被绑定
    public final static String USER_ADDRESS_IS_NOT_EXIST = "user.address.is.not.exist";//用户地址不存在

    //管理员相关
    public final static String MANAGER_IS_EXIT = "manager.is.exit";//管理员已存在
    public final static String MOBILEPHONE_HAS_BEEN_REGISTE = "mobilephone.is.registed";//手机号已被注册
    public final static String STR_MOBILEPHONE_HAS_BEEN_REGISTE = "手机号码已被注册";
    public final static String MANAGER_IS_NOT_EXIST = "manager.is.not.exit";//管理员不存在
    public final static String MANAGER_IS_NOT_DELETE = "manager_is_not_delete";//该账户为超级管理员，不可删除

}
