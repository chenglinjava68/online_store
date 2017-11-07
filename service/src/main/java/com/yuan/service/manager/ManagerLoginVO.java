package com.yuan.service.manager;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author joryun ON 2017/10/22.
 */
@Data
public class ManagerLoginVO {

    @ApiModelProperty("账号")
    private String account;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("验证码")
    private String captcha;

    @ApiModelProperty("验证码的uuid")
    private String uuid;

}
