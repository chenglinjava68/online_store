package com.yuan.service.manager;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author joryun ON 2017/10/22.
 */
public class ManagerLoginVO {

    @ApiModelProperty("账号")
    private String account;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("验证码")
    private String captcha;

    @ApiModelProperty("验证码的uuid")
    private String uuid;

    public ManagerLoginVO() {
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
